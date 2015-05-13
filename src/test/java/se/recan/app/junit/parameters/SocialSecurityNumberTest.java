package se.recan.app.junit.parameters;

import java.util.Arrays;
import java.util.Collection;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import se.recan.utils.ValidateUtil;

/**
 *
 * @author Anders Recksén (recan)
 */
@RunWith(Parameterized.class)
public class SocialSecurityNumberTest {

    private static final Logger LOGGER = Logger.getLogger("SocialSecurityNumberTest");

    private final String socialNumb;
    private final boolean expected;

    public SocialSecurityNumberTest(String socialNumb, boolean expected) {
        this.socialNumb = socialNumb;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"19621002-4318", true},
            {"621002-4318", true},
            {"196210024318", true},
            {"6210024318", true},
            {"19621002 4318", true},
            {"621002 4318", true},
            {"19621002:4318", true},
            {"621002:4318", true}
        });
    }

    @Test
    public void testValidateSocialSecurityNumber() {
        Assert.assertEquals("Detta personnummer är skrivet i korrekt form", ValidateUtil.validateSocialSecurityNumber(socialNumb), expected);
    }
}
