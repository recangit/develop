package se.recan.app.cucumber.soap;

import org.apache.log4j.Logger;
//import com.eviware.soapui.tools.SoapUITestCaseRunner;
import cucumber.annotation.sv.Givet;
import cucumber.annotation.sv.Så;

/**
 *
 * 2014-sep-23
 *
 * @author Anders Recksén (recan)
 */
public class CarSteps {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    private static final String SOAP_FILE = "src/test/resources/soapui/Car-soapui-project.xml";
    private final String[] properties = new String[3];

    @Givet("^farten '(\\d+)'$")
    public void speed(int arg) {
        properties[0] = "speed=" + arg;
    }

    @Givet("^distansen '(\\d+)'$")
    public void distance(int arg) {
        properties[1] = "distance=" + arg;
    }

    @Givet("^tiden '(\\d+)'$")
    public void time(int arg) {
        properties[2] = "time=" + arg;
    }

//    @Så("^är tiden '(\\d+)'$")
//    public void validateTime(int arg) throws Exception {
//        properties[2] = "time=" + arg;
//
//        SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
//
//        runner.setProjectFile(SOAP_FILE);
//
//        runner.setProjectProperties(properties);
//
//        runner.run();
//    }
//
//    @Så("^är farten '(\\d+)'$")
//    public void validateSpeed(int arg) throws Exception {
//        properties[0] = "speed=" + arg;
//
//        SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
//
//        runner.setProjectFile(SOAP_FILE);
//
//        runner.setProjectProperties(properties);
//
//        runner.run();
//    }
//
//    @Så("^är distansen '(\\d+)'$")
//    public void validateDistance(int arg) throws Exception {
//        properties[1] = "distance=" + arg;
//
//        SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
//
//        runner.setProjectFile(SOAP_FILE);
//
//        runner.setProjectProperties(properties);
//
//        runner.run();
//    }
}
