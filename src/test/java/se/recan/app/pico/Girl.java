package se.recan.app.pico;


import org.apache.log4j.Logger;

/**
 * 2015-mar-04
 * @author Anders Recksén (recan, Prolore)
 */
public class Girl {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    
    Boy boy;

    public Girl(Boy boy) {
        this.boy = boy;
    }

    public void kissSomeone() {
        boy.kiss(this);
    }

    public String toString() {
        return "TJENARE";
    }
}
