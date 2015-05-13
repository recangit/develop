package se.recan.app;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Om browsern skall öppnas och stängas endast en gång per testklass:
 * @ClassRule
 *  public static final Driver rule = new Driver();
 * 
 * Om browsern skall öppnas och stängas för varje test i klassen:
 * @Rule
 * public final Driver rule = new Driver();
 * 
 * Om Driver används utan någon av ovanstående Rules måste den stängas efter att test körts (close()).
 * 
 * @author Anders Recksén (recan)
 */
public class Driver extends ExternalResource {

    public static enum Browser {

        CHROME, EXPLORER, FIREFOX, FIREFOX_MH, HTMLUNIT, OPERA
    }

    private static WebDriver driver;

    private static final Logger LOGGER = Logger.getLogger("Logger");

    public Driver() {
        LOGGER.debug("");
    }

    public synchronized static WebDriver getDriver() {

        return getDriver(Browser.valueOf(ResourceUtil.BROWSER.toUpperCase()));
    }
    
    public synchronized static WebDriver getDriver(Browser browser) {

        if (driver == null) {
            try {
                DesiredCapabilities capabilities;
                
                switch (browser) {
                    case CHROME:
                        if (System.getProperty("os.name").contains("Windows")) {
                            System.setProperty("webdriver.chrome.driver", "C:" + File.separator + "drivers" + File.separator + "chromedriver.exe");
                        } else {
                            System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/drivers/chromedriver");
                        }
                        driver = new ChromeDriver();
                        break;
                    case EXPLORER:
                        System.setProperty("webdriver.ie.driver", "C:" + File.separator + "drivers" + File.separator + "IEDriverServer.exe");
                        capabilities = DesiredCapabilities.internetExplorer();
                        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                        driver = new InternetExplorerDriver(capabilities);
                        break;
                    case FIREFOX:
                        driver = new FirefoxDriver();
                        break;
                    case FIREFOX_MH:
                        FirefoxProfile profile = new FirefoxProfile();
                        File modifyHeaders = new File(System.getProperty("basedir")
                                + "/src/test/resources/modify_headers-0.7.1.2b-fx.xpi");

                        profile.setEnableNativeEvents(true);
                        try {
                            profile.addExtension(modifyHeaders);
                        } catch (IOException e) {
                            LOGGER.error(e);
                        }
                        profile.setPreference("modifyheaders.headers.count", 2);
                        profile.setPreference("modifyheaders.headers.action0", "Add");
                        profile.setPreference("modifyheaders.headers.name0", "Prognos");
                        profile.setPreference("modifyheaders.headers.value0", "PrognosAdmin");
                        profile.setPreference("modifyheaders.headers.enabled0", true);
                        profile.setPreference("modifyheaders.headers.action1", "Add");
                        profile.setPreference("modifyheaders.headers.name1", "PISA_ID");
                        profile.setPreference("modifyheaders.headers.value1", "recan");
                        profile.setPreference("modifyheaders.headers.enabled1", true);
                        profile.setPreference("modifyheaders.config.active", true);
                        profile.setPreference("modifyheaders.config.alwaysOn", true);

                        capabilities = new DesiredCapabilities();
                        capabilities.setBrowserName("firefox");
                        capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
                        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                        driver = new FirefoxDriver(capabilities);
                        break;
                    case HTMLUNIT:
                        driver = new HtmlUnitDriver();
                        break;
                }

                LOGGER.debug("Create new Driver " + driver);
            } catch (WebDriverException e) {
                e.printStackTrace();
                LOGGER.error("WebDriverException: " + e);
            }
        }

        return driver;
    }

    public static void loadPage(String url) {
        LOGGER.debug("Load page [" + url + "]");

        getDriver().get(url);
    }

    public static void loadPage(Browser browser, String url) {
        getDriver(browser);

        LOGGER.debug("Load page [" + url + "]");

        getDriver(browser).get(url);
    }
    
    public static void close() {
        LOGGER.debug("Close Driver " + driver);
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    @Override
    protected void before() {
        loadPage(ResourceUtil.URL);
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
    }

    @Override
    protected void after() {
        LOGGER.debug("Close Driver " + driver);
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
