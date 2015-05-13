package se.recan.app.ws;

import org.apache.log4j.Logger;
import javax.jws.WebService;

/**
 *
 * 2014-sep-26
 * @author Anders Recksén (recan)
 */

@WebService(endpointInterface = "se.recan.app.ws.CarServiceInterface")
public class CarServiceImpl implements CarServiceInterface {

    private static final Logger LOGGER = Logger.getLogger("Trace");
    
    @Override
    public void setTime(double time) {
        LOGGER.trace("");
        
        CarPOJO car = CarPOJO.getInstance();
        car.setTime(time);
    }

    @Override
    public double getTime() {
        LOGGER.trace("");
        
        CarPOJO car = CarPOJO.getInstance();
        return car.getTime();
    }

    @Override
    public void setSpeed(double speed) {
        LOGGER.trace("");
        
        CarPOJO car = CarPOJO.getInstance();
        car.setSpeed(speed);
    }

    @Override
    public double getSpeed() {
        LOGGER.trace("");
        
        CarPOJO car = CarPOJO.getInstance();
        return car.getSpeed();
    }

    @Override
    public void setDistance(double distance) {
        LOGGER.trace("");
        
        CarPOJO car = CarPOJO.getInstance();
        car.setDistance(distance);
    }

    @Override
    public double getDistance() {
        LOGGER.trace("");
        
        CarPOJO car = CarPOJO.getInstance();
        return car.getDistance();
    }
    
    @Override
    public String toString() {
        LOGGER.trace("");
        return "Up and going!";
    }
}
