package se.recan.app.junit.rules;

import org.apache.log4j.Logger;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.recan.app.person.Person;
import se.recan.app.person.Persons;

/**
 * @date 2014-maj-18
 * @author Anders Recksén (recan)
 */
public class ExpectedExceptionTest {

    private static final Logger LOGGER = Logger.getLogger("ExceptionRulesTest");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private final int[] threeNumbers;

    public ExpectedExceptionTest() {
        this.threeNumbers = new int[]{1, 2, 3};
    }
    
    // Exception som slängs från klassen Persons
    @Test
    public void testExpectedException1() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(containsString("Listan måste innehålla någon post"));

        new Persons().getSize();
    }
    
    // Exception som slängs från klassen Person
    @Test
    public void testExpectedException2() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(containsString("HEN är inte implementerat än"));
        
        Person person = new Person();
        person.setGender(3);
    }

    @Test
    public void testIndexOutOfBounds() {
        exception.expect(ArrayIndexOutOfBoundsException.class);
        exception.expectMessage("3");
       
        threeNumbers[3] = 4;
    }
}
