package se.recan.app.person;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import se.recan.app.Driver;

/**
 *
 * @author Anders Recksén (recan)
 */
public class PersonSelenium {

    @ClassRule
    public static final Driver classRule = new Driver();
    
    private static final Logger LOGGER = Logger.getLogger("Logger");
    private static final String BASE_URL = "http://localhost:8080/SimpleWebApp/person";
    private static final int SLEEP = 0;
    private PersonPO po;

    @Test
    public void testJavascript() {
        po.setAttribute();
    }
    
    @Test
    public void testForm() throws Exception {
        LOGGER.trace("---");

        po.submit();
        po.sleep(SLEEP);
//        Assert.assertEquals("Förnamn måste anges", po.getMessage());
        po.set("firstName", "Anders");

        po.submit();
        po.sleep(SLEEP);
//        Assert.assertEquals("Efternamn måste anges", po.getMessage());
        po.set("lastName", "Recksén");

        po.submit();
        po.sleep(SLEEP);

        po.set("socialNumb", "621002-430");
        po.submit();
        po.sleep(SLEEP);
//        Assert.assertEquals("Detta är inte ett korrekt personnummer", po.getMessage());
        po.set("socialNumb", "621002-4318");
        po.click(0);
        po.submit();
        po.sleep(SLEEP);

//        Assert.assertEquals("Du har angett kvinna men är man", po.getMessage());
        po.click(1);
        po.sleep(SLEEP);
        po.submit();
        po.sleep(SLEEP);

//        Assert.assertEquals("Användarnamn måste vara minst fem tecken", po.getMessage());
        po.set("userName", "recan");
        po.submit();
        po.sleep(SLEEP);

//        Assert.assertEquals("Lösenordet måste vara minst fem tecken långt", po.getMessage());
        po.set("password", "malla-2");
        po.submit();
        po.sleep(SLEEP);

        po.set("password2", "abcde");
        po.submit();
        po.sleep(SLEEP);
//        Assert.assertEquals("Lösenorden måste vara identiska", po.getMessage());
        po.set("password2", "malla-2");
        po.submit();
        po.sleep(SLEEP);

//        Assert.assertEquals("Validering OK", po.getMessage());
    }

    @Before
    public void setUp() {
        LOGGER.trace("---");
        try {
            Driver.loadPage(BASE_URL);
            po = new PersonPO(Driver.getDriver());
            
            LOGGER.debug("Set Driver and Browser");
            LOGGER.debug("Create PO");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
