package se.recan.app.db.h2;

import org.apache.log4j.Logger;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.recan.app.db.H2DAO;
import se.recan.app.person.Person;

/**
 *
 * @date 2014-jun-03
 * @author Anders Recksén (recan)
 */
public class H2DatabaseTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void findsAndReadsExistingPersonByFirstName() throws Exception {

        Person anders = H2DAO.findPersonByFirstName("Anders");

        assertThat(anders.getFirstName(), is("Anders"));
        assertThat(anders.getLastName(), is("Recksén"));
        assertThat(anders.getGender(), is(1));
    }

    @Test
    public void returnsNullWhenPersonCannotBeFoundByFirstName() throws Exception {

        Person person = H2DAO.findPersonByFirstName("iDoNotExist");

        assertThat(person, is(nullValue()));
    }
    
    @BeforeClass
    public static void createSchema() throws Exception {
        H2DAO.createSchema();
    }

    @Before
    public void insertRows() throws Exception {
        H2DAO.cleanPersonTable();
        H2DAO.insert(new Person(0, "Anders", "Recksén", "recan", "malla", "malla", "6210024318", 51, 1));
        H2DAO.insert(new Person(1, "Kajsa", "Recksén", "karra", "bucken", "bucken", "0403139462", 10, 0));
        H2DAO.insert(new Person(2, "Annie", "Recksén", "pannie", "kaffe", "kaffe", "0703287664", 7, 0));
    }
}
