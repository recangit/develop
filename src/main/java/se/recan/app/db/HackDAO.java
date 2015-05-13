package se.recan.app.db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * 2014-okt-24
 * @author Anders Recksén (recan)
 */
public class HackDAO {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    public static void insert(String string) throws SQLException {
        Connection connection = getDBConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Hack (Description) " +
                     "VALUES (?)");
            statement.setString(1, string);

            statement.executeUpdate();

            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public static void clean() throws SQLException {
        Connection connection = getDBConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Hack");

            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public static String find(String string) throws SQLException {
        Connection connection = getDBConnection();
        String result = "NADA";
       
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hack WHERE Description=?");

            statement.setString(1, string);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("Description");
            }
            resultSet.close();
            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    public static String findById(int id) throws SQLException {
        Connection connection = getDBConnection();
        String result = "NADA";
       
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hack WHERE id=?");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("Description");
            }
            resultSet.close();
            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    public static List<String> findAll() throws SQLException {
        Connection connection = getDBConnection();
        List<String> result = new ArrayList<String>();
       
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hack");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString("Description"));
            }
            resultSet.close();
            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
    public static void createSchema(String path) throws Exception {
//        LOGGER.debug(new File("/WEB-INF/classes/hack.sql").getAbsolutePath());
//        LOGGER.debug(new File("/WEB-INF/classes/hack.sql").getCanonicalPath());
        RunScript.execute(JDBC_URL, USER, PASSWORD, path, UTF8, false);
//        RunScript.execute(JDBC_URL, USER, PASSWORD, "src/main/resources/hack.sql", UTF8, false);
    }

    private static Connection getDBConnection() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        return dataSource.getConnection();
    }
}
