package se.recan.app.selenium;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import se.recan.app.Driver;

/**
 * @date 2014-okt-14
 * @author Anders Recksén (recan)
 */
public class ActionTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void testAction() {
        LOGGER.debug("");

//        Driver.loadPage("http://localhost:8080/SimpleWebApp/");
//        Actions action = new Actions(Driver.getDriver());
//        action.click();
        
//        DefaultSelenium ds=new DefaultSelenium("http://localhost:8080/SimpleWebApp/",4444,"*firefox","http://www.gmail.com");
//          ds.start();
//          ds.windowMaximize();
//          ds.open("/");
//          ds.type("Email", "<username>");
//          ds.type("Passwd", "<pwd>");
//          ds.click("signIn");  
        
        
        // You may use any WebDriver implementation. Firefox is used here as an example
WebDriver driver = new FirefoxDriver();

// A "base url", used by selenium to resolve relative URLs
String baseUrl = "http://www.google.com";

// Create the Selenium implementation
Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);

// Perform actions with selenium
selenium.open("http://localhost:8080/SimpleWebApp/person");
//selenium.type("name=q", "cheese");
//selenium.click("name=btnG");
String[] links = selenium.getAllFields();
for(String s: links) {
    LOGGER.debug(s);
}
driver.close();
driver=null;
//        LOGGER.debug(selenium.getBodyText());
// And get the underlying WebDriver implementation back. This will refer to the
// same WebDriver instance as the "driver" variable above.
//WebDriver driverInstance = ((WebDriverBackedSelenium) selenium).getUnderlyingWebDriver();
    }

}