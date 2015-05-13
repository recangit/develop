package se.recan.app.ddt;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.ClassRule;
import org.junit.Test;
import se.recan.app.Driver;
import se.recan.utils.EntityUtil;
import se.recan.app.utils.Excel;

/**
 *
 * Data Driven Test.
 * Denna läser in en xmlfil som innehåller namn på metoder att exekvera och argument till denna metod.
 * 
 * EntityUtil tar emot resultatet av ovanstående som en lista. En post i listan består av strängen metodnamn:argument.
 * EntityUtil tar klass att använda som argument,  parsar strängen och anropar korrekt metod med angett argument.
 * 
 * Testen ligger i PersonPOJO (metod save).
 * @date 2014-maj-08
 * @author Anders Recksén (recan)
 */
public class DDTTest {

    @ClassRule
    public static Driver classRule = new Driver();
    
    private static final Logger LOGGER = Logger.getLogger("Logger");
    
    @Test
    public void testDDT() {
        try {
            List<String> list = Excel.readExcel("src/test/resources/persondata.xlsx");
    
            EntityUtil entity = EntityUtil.getInstance();
            entity.processCorrectName(list, "se.recan.app.ddt.PersonPOJO");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
