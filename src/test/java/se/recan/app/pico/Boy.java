package se.recan.app.pico;


import org.apache.log4j.Logger;

/**
 * 2015-mar-04
 * @author Anders Recksén (recan, Prolore)
 */
public class Boy {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    public void kiss(Object kisser) {
        System.out.println("I was kissed by " + kisser);
    }
}
