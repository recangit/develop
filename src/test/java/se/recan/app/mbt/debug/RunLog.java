package se.recan.app.mbt.debug;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Eftersom Modellen överlagrar org.graphwalker.multipleModels.ModelAPI kräver konstruktorn
 * argument som egentligen inte behövs i detta sammanhanget. Tyvärr går det inte att skapa
 * en ny, enklare konstruktor.
 * 
 * Kom ihåg att importera den använda modellen (JAVA_MODEL). Eftersom den anges som
 * en sträng i konstruktorn till Class förstår Netbeans inte att den behövs.
 * 
 * @date 2014-jul-17
 * @author Anders Recksén (recan)
 */
public class RunLog {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void testRunLogFile() {
        LOGGER.debug("");
        
    }
}
