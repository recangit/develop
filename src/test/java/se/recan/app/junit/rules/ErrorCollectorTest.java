package se.recan.app.junit.rules;

import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * http://www.marcphilipp.de/blog/2011/12/22/junit-rules/
 * 
 * ErrorCollector exekverar samtliga tester, oavsett de går igenom eller inte, och visar resultatet efteråt.
 * testA kommer efter att ha konstaterat att 2 inte är lika med 3 avsluta testen.
 * testB testar även om A är lika med B.
 * @date 2014-maj-30
 * @author Anders Recksén (recan)
 */
public class ErrorCollectorTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void testA() {
        Assert.assertEquals(2, 3);
        Assert.assertEquals("A", "B");
    }

    @Test
    public void testB() {
        collector.checkThat(1 + 1, is(3));
        collector.checkThat("A", CoreMatchers.equalTo("B"));
    }
}
