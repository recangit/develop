package se.recan.app.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * 2014-sep-26
 * @author Anders Recksén (recan)
 */

@WebService
@SOAPBinding(style = Style.RPC)
public interface CarServiceInterface {
    
    @WebMethod
    void setTime(double time);
    
    @WebMethod
    double getTime();
    
    @WebMethod
    void setSpeed(double speed);
    
    @WebMethod
    double getSpeed();
    
    @WebMethod
    void setDistance(double distance);
    
    @WebMethod
    double getDistance();
    
    @WebMethod
    @Override
    String toString();
}
