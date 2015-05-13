package se.recan.app.bestpractice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import static org.h2.engine.Constants.UTF8;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;

/**
 *
 * @date 2014-jun-16
 * @author Anders Recksén (recan)
 */
public class BestPracticeDAO {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    // För att testa
    public static void junk(String s) throws SQLException {
        LOGGER.debug("");
        Connection conn = getDBConnection();
        int id;
        try {

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Gengres WHERE gengre=?");
            stmt.setString(1, s);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
                LOGGER.debug(id + " Finns i DB");
            } else {
                stmt = conn.prepareStatement("INSERT INTO Gengres(gengre) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, s);
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                    LOGGER.debug(id + " Fanns INTE i DB");
                }
            }

            stmt = conn.prepareStatement("SELECT * FROM Gengres");
            rs = stmt.executeQuery();
            while (rs.next()) {
                LOGGER.debug(rs.getInt("id") + ":" + rs.getString("gengre"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (NullPointerException npe) {
            }
        }
    }
    
    /**
     * Transaction
     * 
     * MERGE INTO kontrollerar om värdet i gengres existerar i databasen.
     * Om inte så läggs det till.
     * MERGE INTO används inom flera databaser men syntaxen ser lite olika ut.
     * Nedanstående är H2 specifik.
     * Motsvarigheten i MySQL är INSERT .. ON DUPLICATE KEY UPDATE.
     * @param to
     * @throws java.sql.SQLException
     */
    public static void populate(BestPracticeTO to) throws SQLException {
        LOGGER.debug("Transaction");
        Connection conn = getDBConnection();

        // Normalt sker commit när Statement.executeUpdate()
        // Här vill vi att allt går igenom innan vi gör denna commit.
        // I annat fall görs en rollback.
        conn.setAutoCommit(false);
        
        try {
            int id1 = -1;
            int id2 = -1;

            PreparedStatement stmt = conn.prepareStatement("MERGE INTO Gengres(gengre) KEY(gengre) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, to.getGengresGengre());
            stmt.executeUpdate();

            // Metoden getGeneratedKeys() returnerar Statement.RETURN_GENERATED_KEYS
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id1 = rs.getInt(1);
            } else {
                stmt = conn.prepareStatement("SELECT id FROM Gengres WHERE gengre=?");
                stmt.setString(1, to.getGengresGengre());
                // Lägg märke till att denna ResultSet inte är densamma som ovanstående
                rs = stmt.executeQuery();
                if (rs.next()) {
                    id1 = rs.getInt(1);
                }
            }

            stmt = conn.prepareStatement("MERGE INTO Artists(artist) KEY(artist) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, to.getArtistsArtist());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id2 = rs.getInt(1);
            } else {
                stmt = conn.prepareStatement("SELECT id FROM Artists WHERE artist=?");
                stmt.setString(1, to.getArtistsArtist());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    id2 = rs.getInt(1);
                }
            }

            stmt = conn.prepareStatement("INSERT INTO Albums(artistId, gengreId, album, sleeve) "
                    + "VALUES (?, ?, ?, ?)");

            int i = 1;
            stmt.setInt(i++, id2);
            stmt.setInt(i++, id1);
            stmt.setString(i++, to.getAlbumsAlbum());
            stmt.setInt(i++, to.getAlbumsSleeve());

            stmt.executeUpdate();

            // Nu gör vi commit
            conn.commit();

            rs.close();
            stmt.close();
        } catch (SQLException sqle) {
            conn.rollback();
            sqle.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (NullPointerException npe) {
                // Annorlunda conn.close(). Kanske har med Transaktioner att göra.
            }
        }
    }

    public static void createSchema() throws Exception {
        RunScript.execute(JDBC_URL, USER, PASSWORD, "src/test/resources/bestpractice.sql", UTF8, false);
    }

    public static int count(String table) throws SQLException {
        LOGGER.debug("");
        int totalNumberOfHits = 0;

        Connection conn = getDBConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM " + table);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            totalNumberOfHits = rs.getInt(1);

            rs.close();
            stmt.close();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return totalNumberOfHits;
    }

    public static List<BestPracticeTO> getAll() throws SQLException {
        LOGGER.debug("");
        Connection conn = getDBConnection();
        List<BestPracticeTO> result = new ArrayList<>();
        try {
            String query = "SELECT Gengres.gengre, Gengres.id AS GID, Artists.artist, Artists.id AS AID, Albums.id, Albums.artistId, Albums.gengreId, Albums.sleeve, Albums.album FROM Gengres"
                    + " LEFT JOIN Albums ON Gengres.id=Albums.gengreId"
                    + " LEFT JOIN Artists ON Artists.id=Albums.artistId";
            
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BestPracticeTO to = new BestPracticeTO();
                to.setAlbumsId(rs.getInt("id"));
                to.setAlbumsArtistId(rs.getInt("artistId"));
                to.setAlbumsGengreId(rs.getInt("gengreId"));
                to.setAlbumsAlbum(rs.getString("album"));
                to.setAlbumsSleeve(rs.getInt("sleeve"));
                to.setArtistsId(rs.getInt("id"));
                to.setArtistsId(rs.getInt("AID"));
                to.setArtistsArtist(rs.getString("artist"));
                to.setGengresId(rs.getInt("id"));
                to.setGengresId(rs.getInt("GID"));
                to.setGengresGengre(rs.getString("gengre"));
                result.add(to);
            }

            rs.close();
            stmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle.getMessage(), sqle);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return (ArrayList) result;
    }
    
    public static List<BestPracticeTO> getGengres() throws SQLException {
        LOGGER.debug("");
        Connection conn = getDBConnection();
        List<BestPracticeTO> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM Gengres";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BestPracticeTO to = new BestPracticeTO();
                
                to.setGengresId(rs.getInt("id"));
                to.setGengresGengre(rs.getString("gengre"));
                result.add(to);
            }

            rs.close();
            stmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle.getMessage(), sqle);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return (ArrayList) result;
    }
    public static List<BestPracticeTO> getArtists() throws SQLException {
        LOGGER.debug("");
        Connection conn = getDBConnection();
        List<BestPracticeTO> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM Artists";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BestPracticeTO to = new BestPracticeTO();
                
                to.setArtistsId(rs.getInt("id"));
                to.setArtistsArtist(rs.getString("artist"));
                result.add(to);
            }

            rs.close();
            stmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle.getMessage(), sqle);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return (ArrayList) result;
    }
    public static List<BestPracticeTO> getAlbums() throws SQLException {
        LOGGER.debug("");
        Connection conn = getDBConnection();
        List<BestPracticeTO> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM Albums";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BestPracticeTO to = new BestPracticeTO();
                
                to.setAlbumsId(rs.getInt("id"));
                to.setAlbumsArtistId(rs.getInt("artistId"));
                to.setAlbumsGengreId(rs.getInt("gengreId"));
                to.setAlbumsAlbum(rs.getString("album"));
                to.setAlbumsSleeve(rs.getInt("sleeve"));
                result.add(to);
            }

            rs.close();
            stmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle.getMessage(), sqle);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return (ArrayList) result;
    }

    public static void cleanTable() throws SQLException {
        Connection connection = getDBConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Albums");
            statement.executeUpdate("DELETE FROM Artists");
            statement.executeUpdate("DELETE FROM Gengres");

            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    /*
    public static int getLastPost() throws Exception {

        Connection conn = getDBConnection();
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Logger WHERE created IN (SELECT MAX(created) FROM Logger)");
           
            if (rs.next()) {
                to.setId(rs.getInt("id"));
                to.setDescription(rs.getString("description"));
                to.setCreated(rs.getLong("created"));
            }
           
            rs.close();
            stmt.close();
       } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage(), sqle);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        
        return to;
    }
    
    
    public static void delete(int id) throws Exception {
        Connection conn = Resources.getDBConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Logger WHERE id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            stmt.close();
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage(), sqle);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    
     public static ManagerResult getColumns(ManagerSearch search) throws Exception {
        if(search.getDatabase().equals("") || search.getTable().equals("")) { return new ManagerResult(); }
        
        if(search.getUtilityStatement().equals("DESCRIBE")) {
            return getColumns("DESCRIBE " + search.getDatabase() + "." + search.getTable());
        }

        if(search.getColumns()==null) { return new ManagerResult(); }

        Connection conn = Resources.getDBConnection();
        
        ArrayList result = new ArrayList();

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    search.getCriteriaPart() +
                    search.getSortorderPart() +
                    " LIMIT " + search.getPageSize() + 
                    " OFFSET " + (search.getPageNumber() - 1) * search.getPageSize());

            String query = stmt.toString();

            LogUtil.sql(stmt);

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();

            ArrayList<String> columnName = new ArrayList<String>();
            for(int i = 1; i <= metadata.getColumnCount(); i++) {
                columnName.add(metadata.getColumnName(i));
            }
            result.add(columnName);

            while (rs.next()) {
		columnName = new ArrayList<String>();
		for (int i = 1; i <= metadata.getColumnCount(); i++) {
		    columnName.add(rs.getString(i));
		}
		result.add(columnName);
	    }

            rs.close();
            
            stmt = conn.prepareStatement("SELECT count(*) " + search.getCountPart());
            rs = stmt.executeQuery();
            rs.next();
            int totalNumberOfHits = rs.getInt(1);

            rs.close();
            stmt.close();
            
            ManagerResult searcResult = new ManagerResult();
            searcResult.setQuery(query);
            searcResult.setColumnContent(result);
            searcResult.setTotalNumberOfHits(totalNumberOfHits);
            searcResult.setPageSize(search.getPageSize());
            searcResult.setCurrentPageNumber(search.getPageNumber());
            searcResult.setNumberOfPages(totalNumberOfHits % search.getPageSize() == 0 ? totalNumberOfHits / search.getPageSize() : totalNumberOfHits / search.getPageSize() + 1);

            return searcResult;
            
        } catch (SQLException sqle) {
            throw new Exception(sqle.getMessage(), sqle);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    */
    
    public static List<String> getDatabases() throws SQLException {
        Connection conn = getDBConnection();
        
        List<String> result = new ArrayList<String>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW databases");

            while (rs.next()) { result.add(rs.getString(1));}

            rs.close();
            stmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }
    
    public static List<String> getTables() throws SQLException {
        Connection conn = getDBConnection();
        
        List<String> result = new ArrayList<String>();

        try {
            Statement stmt = conn.createStatement();
//            stmt.executeUpdate("USE " + search.getDatabase());
//
//            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES");

            while (rs.next()) { result.add(rs.getString(1)); }

            rs.close();
            stmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }
    
    public static String getMetaData() throws SQLException {
        Connection conn = getDBConnection();

        List<String> tables = getTables();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        StringBuilder buffer = new StringBuilder();
        try {
            ResultSetMetaData metadata;
            for (String table : tables) {
                buffer.append("\n");
                buffer.append(table);
                buffer.append("\n");
                stmt = conn.prepareStatement("SELECT * FROM " + table + " LIMIT 0");

                rs = stmt.executeQuery();
                metadata = rs.getMetaData();

                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    buffer.append(metadata.getColumnName(i));
                    buffer.append(" ");
                }
                buffer.append("\n");
                buffer.append("\n");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
            if (conn != null) {
                conn.close();
            }
        }
        return buffer.toString();
    }
    
    public static ArrayList getColumns(String query) throws SQLException {
        Connection conn = getDBConnection();
        
        ArrayList result = new ArrayList();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData metadata = rs.getMetaData();

            ArrayList<String> columnName = new ArrayList<String>();
            for(int i = 1; i <= metadata.getColumnCount(); i++) {
                columnName.add(metadata.getColumnName(i));
            }
            result.add(columnName);

            int totalNumberOfHits=1;
            while (rs.next()) {
		columnName = new ArrayList<String>();
		for (int i = 1; i <= metadata.getColumnCount(); i++) {
		    columnName.add(rs.getString(i));
		}
		result.add(columnName);
                totalNumberOfHits++;
	    }

            rs.close();
            stmt.close();
            
//            ManagerResult searcResult = new ManagerResult();
//            searcResult.setQuery(query);
//            searcResult.setColumnContent(result);
//            searcResult.setTotalNumberOfHits(totalNumberOfHits);

            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    private static Connection getDBConnection() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        return dataSource.getConnection();
    }
}
