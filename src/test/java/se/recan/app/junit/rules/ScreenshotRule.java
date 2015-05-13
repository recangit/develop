package se.recan.app.junit.rules;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import se.recan.app.Driver;

/**
 *
 * 2014-nov-11
 * @author Anders Recksén (recan)
 */
public class ScreenshotRule extends ExternalResource {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private final String name;

    public ScreenshotRule(String name) {
        this.name = name;
    }
    
    @Override
    protected void before() {
        LOGGER.debug(name);
    }

    @Override
    protected void after() {
        LOGGER.debug(name);
    }
    
    public static void captureScreenshot(String methodName) {
        try {
            File scrFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File(methodName + ".png");
//            File destFile = new File("screenShot" + dateToString(System.currentTimeMillis(), "-HH:mm:s") + ".png");
            LOGGER.error("Taking screenshot... " + destFile);
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            System.err.println("Error taking screenshot: " + e);
            LOGGER.error("Error taking  screenshot: " + e);
        }
    }
}
