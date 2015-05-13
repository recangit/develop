package se.recan.app.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 2014-sep-26
 *
 * @author Anders Recksén (recan)
 */
public class CarServiceTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void testWsdl() throws MalformedURLException {
        URL wsdlUrl = new URL("http://localhost:8080/SimpleWebApp/car?wsdl");
        QName qname = new QName("http://ws.app.recan.se/", "CarServiceImplService");
        Service service = Service.create(wsdlUrl, qname);
        CarServiceInterface car = service.getPort(CarServiceInterface.class);

//        LOGGER.debug(car.toString());
    }

    @Test
    public void testStub() {
        CarServiceImplService service = new CarServiceImplService();
        CarServiceInterface car = service.getCarServiceImplPort();

        car.setTime(80);
        car.setDistance(160);
        car.setSpeed(120);
        Assert.assertTrue("Körd sträcka", car.getDistance()==160);
        Assert.assertTrue("Tid", car.getTime()==80);
        Assert.assertTrue("Fart", car.getSpeed()==120);
    }

}
