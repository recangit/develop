package se.recan.app.junit;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import se.recan.app.SlowTests;
import se.recan.app.FastTests;

/**
 *
 * @date 2014-jun-18
 * @author Anders Recksén (recan)
 */
public class CategoryTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    // Nedanstående annotering kan också sättas på klassnivå.
    @Category(FastTests.class)
    @Test
    public void a() {
        LOGGER.debug("");
    }

    @Category(SlowTests.class)
    @Test
    public void b() {
        LOGGER.debug("");
    }
}
