package se.recan.app.junit.rules;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * När denna timar ut ges ett Assertion error.
 * Vet inte när detta kan komma till användning.
 * @date 2014-jun-09
 * @author Anders Recksén (recan)
 */
public class TimeOutTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Rule
    public Timeout timeout = new Timeout(10); // Millisekunder

//    @Test
    public void firstTest() {
        while (true) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void secondTest() {
        Assert.assertTrue(true);
    }
}
