package se.recan.app.pico;

import org.picocontainer.MutablePicoContainer;
//import org.picocontainer.defaults.DefaultPicoContainer;
import org.apache.log4j.Logger;
import org.picocontainer.DefaultPicoContainer;

/**
 * 2015-mar-04
 *
 * @author Anders Recksén (recan, Prolore)
 */
public class SimplePicoContainer {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    public static void main(String[] args) {
        MutablePicoContainer pico = new DefaultPicoContainer();

        pico.addComponent(Boy.class);
        pico.addComponent(Girl.class);

        Girl girl = (Girl) pico.getComponent(Girl.class);
        girl.kissSomeone();

    }

}
