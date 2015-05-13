package se.recan.app.junit.rules;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @date 2014-maj-14
 * @author Anders Recksén (recan)
 */
public class ExternalResourceTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @ClassRule
    public static final ExternalResourceRule classRule = new ExternalResourceRule("classRule");
    
    @Rule
    public ExternalResourceRule methodRule = new ExternalResourceRule("methodRule");
    
    @Test
    public void testA() {
        LOGGER.debug("I test A");
        Assert.assertTrue(true);
    }

    @Test
    public void testB() {
        LOGGER.debug("I test B");
        Assert.assertTrue(true);
    }
}
