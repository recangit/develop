package se.recan.app.selenium;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import se.recan.app.Driver;

/**
 * 2013-nov-11
 * @author Anders Recksén (recan)
 */
public class DelayTest {

    @ClassRule
    public static Driver classRule = new Driver();
    private static final Logger LOGGER = Logger.getLogger("Logger");
    private static final DelayView dw = PageFactory.initElements(Driver.getDriver(), DelayView.class);

    @Test
    public void testDelay() throws Exception {
        Driver.getDriver().findElement(By.linkText("Testa ajax")).click();
     
//        sleep(3);
        
        String message = Driver.getDriver().findElement(By.cssSelector("p[class=message]")).getText();
        Assert.assertTrue(message.equals("Detta ajax meddelande visas med 2 sekunders fördröjning"));
    }
    
//    @Test
    public void testFactoryDelay() throws Exception {
        Driver.getDriver().findElement(By.linkText("Testa ajax")).click();
     
//        DelayView dw = new DelayView();
        
        dw.clickAjaxLinkLink();
        
        String message = dw.getMessage();
        Assert.assertTrue(message.equals("Detta ajax meddelande visas med 2 sekunders fördröjning"));
        
        dw.clickNada();
    }
    
    public void sleep(long l) {
        LOGGER.debug("##### Sleep(" + l + ") #####");
        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }

    @Before
    public void setUp() {
        LOGGER.debug("\n\nSTART");
        Driver.loadPage("http://localhost:8080/SimpleWebApp/controller?template=delay");
    }
}