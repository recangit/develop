package se.recan.app.junit.mock;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.recan.app.person.Person;
import se.recan.app.person.Persons;

/**
 * @date 2014-maj-14
 * @author Anders Recksén (recan)
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    private static final Logger LOGGER = Logger.getLogger("MockTest");

    @Mock
    Persons persons;
    
    @Test
    public void testA() {
        LOGGER.debug("");
        
        Person person = new Person();
        person.setFirstName("Kajsa");

        when(persons.getPerson(0)).thenReturn(person);
        when(persons.getSize()).thenReturn(12);
        
        String expected = persons.getPerson(0).getFirstName();
        String actual = person.getFirstName();
        
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("Antal att få tillbaka", 12, persons.getSize());
    }
    
    @Test
    public void testB() {
        LOGGER.debug("");
        
        Person person = new Person();
        person.setFirstName("Annie");
        
        when(persons.getPerson(10)).thenReturn(person);
        when(persons.getSize()).thenReturn(20);
        
        String expected = persons.getPerson(10).getFirstName();
        String actual = person.getFirstName();
        
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("Antal att få tillbaka", 20, persons.getSize());
    }
}