package se.recan.app.db.dbunit;

import java.io.File;
import org.apache.log4j.Logger;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import static org.h2.engine.Constants.UTF8;
import org.h2.tools.RunScript;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.recan.app.db.H2DAO;
import se.recan.app.person.Person;

/**
 * Test av dbUnit.
 * Egentligen förstår jag inte vad denna gör eller vilken funktion det fyller.
 * @date 2014-jun-03
 * @author Anders Recksén (recan)
 */
public class XmlDatabaseTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @Test
    public void findsAndReadsExistingPersonByFirstName() throws Exception {
        Person person = H2DAO.findPersonByFirstName("Kajsa");
        LOGGER.debug(person);
        assertThat(person.getFirstName(), is("Kajsa"));
        assertThat(person.getLastName(), is("Recksén"));
        assertThat(person.getPassword(), is("bucken"));
        assertThat(person.getUserName(), is("karra"));
        assertThat(person.getSocialNumb(), is("20040313-9462"));
    }

    @Test
    public void returnsNullWhenPersonCannotBeFoundByFirstName() throws Exception {
        Person person = H2DAO.findPersonByFirstName("iDoNotExist");

        assertThat(person, is(nullValue()));
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    @BeforeClass
    public static void createSchema() throws Exception {
        RunScript.execute(JDBC_URL, USER, PASSWORD, "src/test/resources/schema.sql", UTF8, false);
    }
}
