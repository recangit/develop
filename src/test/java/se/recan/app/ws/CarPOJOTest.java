package se.recan.app.ws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import se.recan.app.ws.CarPOJO;

/**
 *
 * 2014-sep-26
 *
 * @author Anders Recksén (recan)
 */
public class CarPOJOTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void carTest() {
        CarPOJO car = new CarPOJO();
        
        car.setTime(80);
        car.setDistance(160);
        car.setSpeed(120);
        Assert.assertTrue("Körd sträcka", car.getDistance()==160);
        Assert.assertTrue("Tid", car.getTime()==80);
        Assert.assertTrue("Fart", car.getSpeed()==120);
    }
    
    @Test
    public void valuesTest() {
        CarPOJO car = new CarPOJO();
        
        car.setTime(180);
//        car.setDistance(150);
        car.setSpeed(110);
        LOGGER.debug("Körd sträcka:" + car.getDistance());
//        LOGGER.debug("Tid:" + car.getTime());
//        LOGGER.debug("Fart:" + car.getSpeed());
    }

}
