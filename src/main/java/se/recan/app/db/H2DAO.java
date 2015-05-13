package se.recan.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import static org.h2.engine.Constants.UTF8;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import se.recan.app.person.Person;

/**
 *
 * @date 2014-jun-12
 * @author Anders Recksén (recan)
 */
public class H2DAO {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void insert(Person person) throws SQLException {
        Connection connection = getDBConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO PERSON (NAME, LAST_NAME, USER_NAME, PASSWORD, PASSWORD2, SOCIAL_NUMB, AGE, GENDER) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setString(3, person.getUserName());
            statement.setString(4, person.getPassword());
            statement.setString(5, person.getPassword2());
            statement.setString(6, person.getSocialNumb());
            statement.setInt(7, person.getAge());
            statement.setInt(8, person.getGender());

            statement.executeUpdate();

            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Person findPersonByFirstName(String name) throws SQLException {
        Connection connection = getDBConnection();

        Person person = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PERSON WHERE NAME=?");

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                person = new Person(
                        resultSet.getInt("ID"), 
                        resultSet.getString("NAME"), 
                        resultSet.getString("LAST_NAME"), 
                        resultSet.getString("USER_NAME"), 
                        resultSet.getString("PASSWORD"), 
                        resultSet.getString("PASSWORD2"), 
                        resultSet.getString("SOCIAL_NUMB"), 
                        resultSet.getInt("AGE"), 
                        resultSet.getInt("GENDER"));
            }
            resultSet.close();
            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return person;
    }

    public static void cleanPersonTable() throws SQLException {
        Connection connection = getDBConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM PERSON");

            statement.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void createSchema() throws Exception {
        RunScript.execute(JDBC_URL, USER, PASSWORD, "src/test/resources/schema.sql", UTF8, false);
    }

    private static Connection getDBConnection() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        return dataSource.getConnection();
    }
}
