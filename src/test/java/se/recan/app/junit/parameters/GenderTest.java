package se.recan.app.junit.parameters;

import java.util.Arrays;
import java.util.Collection;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import se.recan.utils.ValidateUtil;

/**
 *
 * @author Anders Recksén (recan)
 */
@RunWith(Parameterized.class)
public class GenderTest {

    private static final Logger LOGGER = Logger.getLogger("GenderTest");

    private static final int MALE = 1;
    private static final int FEMALE = 0;

    private final String socialNumb;
    private final int expected;

    public GenderTest(String socialNumb, int expected) {
        this.socialNumb = socialNumb;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"19621002-4318", MALE},
            {"730401:6640", FEMALE},
            {"20040313:9462", FEMALE},
            {"200703287664", FEMALE}
        });
    }

    @Test
    public void testGender() {
        Assert.assertEquals("Personnummer tillhör man/kvinna", ValidateUtil.getGender(socialNumb), expected);
    }
}
