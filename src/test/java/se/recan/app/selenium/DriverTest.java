package se.recan.app.selenium;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import se.recan.app.Driver;
import se.recan.app.LogDriver;

/**
 * PROXY?
 * http://docs.seleniumhq.org/docs/04_webdriver_advanced.jsp#browser-startup-manipulation
 * Denna test hanterar Seleniums interna loggning.
 *
 * @date 2014-maj-26
 * @author Anders Recksén (recan)
 */
public class DriverTest {

//    @ClassRule
//    public static Driver classRule = new Driver();
    private static final Logger LOGGER = Logger.getLogger("Logger");
    private static final String BASE_URL = "http://localhost:8080/SimpleWebApp/person";

    private static final Level LOG_LEVEL = Level.ALL;
    
//    @Test
    public void testDriver() {
        LOGGER.debug("");

        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable(LogType.BROWSER, LOG_LEVEL);
        prefs.enable(LogType.CLIENT, LOG_LEVEL);
        prefs.enable(LogType.DRIVER, LOG_LEVEL);
        prefs.enable(LogType.PERFORMANCE, LOG_LEVEL);
        prefs.enable(LogType.PROFILER, LOG_LEVEL);
        prefs.enable(LogType.SERVER, LOG_LEVEL);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, prefs);

        WebDriver driver = new FirefoxDriver(desiredCapabilities);

        Logs log = driver.manage().logs();
        LogEntries logEntries = log.get(LogType.DRIVER);
        
        driver.get(BASE_URL);
        for (LogEntry logEntry : logEntries) {
            LOGGER.debug(logEntry.getMessage());
        }
        driver.quit();
    }

//    @Test
    public void testDriverApi() {
        LogDriver.getDriver();
        LogDriver.loadPage(BASE_URL);
        LOGGER.debug(LogDriver.getLog());
        LogDriver.close();
    }
    
//    @Test
    public void testFirefoxProfile() {
        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile profile = allProfiles.getProfile("selenium-test");

        WebDriver driver = new FirefoxDriver(profile);

        driver.get(BASE_URL);

        driver.close();
    }
    
//    @Test
    public void testCreateHeader() {
        FirefoxProfile profile = new FirefoxProfile();
        File modifyHeaders = new File(System.getProperty("basedir") +
//                                                 "/src/test/resources/modify_headers-0.7.1.2b-fx.xpi");
                                                 "/src/test/resources/selenium-src-resources-modify_headers-0.7.1.2b-fx.xpi");
       System.out.println(modifyHeaders);
       System.out.println(modifyHeaders.canRead());
       profile.setEnableNativeEvents(true); 
       try {
         profile.addExtension(modifyHeaders); 
       } catch (Exception e) {
         e.printStackTrace(); 
       }
        profile.setPreference("modifyheaders.headers.count", 2);
        profile.setPreference("modifyheaders.headers.action0", "Add");
        profile.setPreference("modifyheaders.headers.name0", "Prognos");
        profile.setPreference("modifyheaders.headers.value0", "PrognosAdmin");
        profile.setPreference("modifyheaders.headers.enabled0", true);
        profile.setPreference("modifyheaders.headers.action1", "Add");
        profile.setPreference("modifyheaders.headers.name1", "PISA_ID");
        profile.setPreference("modifyheaders.headers.value1", "borcm");
        profile.setPreference("modifyheaders.headers.enabled1", true);
        profile.setPreference("modifyheaders.config.active", true);
        profile.setPreference("modifyheaders.config.alwaysOn", true);
       
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
 
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.get("http://www.arbetsformedlingen.se/");
    }
    
    public void sleep(long l) {
        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }
}
