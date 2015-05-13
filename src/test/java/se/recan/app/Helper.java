package se.recan.app;

import org.apache.log4j.Logger;

/**
 *
 * 2014-okt-16
 * @author Anders Recksén (recan)
 */
public class Helper {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    private static final int SLEEP = 0;
    
    public static void sleep() {
        sleep(SLEEP);
    }
    
    public static void sleep(long l) {
        LOGGER.debug("##### Sleep(" + l + ") #####");
        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }
}
