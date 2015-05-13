package se.recan.app.ws;

import org.apache.log4j.Logger;

/**
 *
 * 2014-sep-25
 * @author Anders Recksén (recan)
 */
public class CarPOJO {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    
    private double time = 0;
    private double speed = 0;
    private double distance = 0;
    
    private static CarPOJO instance = null;
    
    public static CarPOJO getInstance() {
        if(instance == null) {
            instance = new CarPOJO();
        }
        
        return instance;
    }

    /**
     * 
     * @param time in minutes
     */
    public void setTime(double time) {
        this.time = time;
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    /**
     * 
     * @return time in minutes
     */
    public double getTime() {
        return (distance/speed) * 60;
    }
    
    public double getSpeed() {
        double hours = time/60;
        return (distance/hours);
    }
    
    public double getDistance() {
        double hours = time/60;
        return (speed * hours);
    }
}
