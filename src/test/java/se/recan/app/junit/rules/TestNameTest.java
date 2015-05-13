package se.recan.app.junit.rules;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * Denna känner till namnet på sin egen metod.
 * Vet inte när detta kan komma till användning.
 * @date 2014-jun-10
 * @author Anders Recksén (recan)
 */
public class TestNameTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Rule
    public TestName name = new TestName();

    @Test
    public void testA() {
        Assert.assertEquals("testA", name.getMethodName());
    }

    @Test
    public void testB() {
        Assert.assertEquals("testB", name.getMethodName());
        LOGGER.debug(name.getMethodName());
    }
}
