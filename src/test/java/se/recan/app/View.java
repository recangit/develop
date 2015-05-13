package se.recan.app;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.recan.utils.DateUtil;

/**
 *
 * @date 2014-maj-09
 * @author Anders Recksén (recan)
 */
public class View {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private static final int SLEEP = 0;
    
    public void refresh() {
        Driver.getDriver().navigate().refresh();
    }

    public String getTitle() {
        return Driver.getDriver().getTitle().toLowerCase();
    }

    public static WebElement getParent(WebElement element) {
        return element.findElement(By.xpath(".."));
    }

    public static WebElement waitForElement(WebElement elementToWaitFor) {
        try {
            LOGGER.debug("waitForElement: " + elementToWaitFor);
            return waitForElement(elementToWaitFor, null);
        } catch (Exception e) {
            LOGGER.error("Hittar inte WebElement: " + elementToWaitFor);
            captureScreenshot();
            throw new NoSuchElementException("Hittar inte WebElement: " + elementToWaitFor);
        }
    }

    public static WebElement waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
        if (waitTimeInSeconds == null) {
            waitTimeInSeconds = 10;
        }
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }
    
    public static WebElement waitForElement(By elementToWaitFor) {
        try {
            LOGGER.debug("waitForElement: " + elementToWaitFor);
            return waitForElement(elementToWaitFor, null);
        } catch (Exception e) {
            LOGGER.error("Hittar inte WebElement: " + elementToWaitFor);
            captureScreenshot();
            throw new NoSuchElementException("Hittar inte WebElement: " + elementToWaitFor);
        }
    }

    public static WebElement waitForElement(By elementToWaitFor, Integer waitTimeInSeconds) {
        if (waitTimeInSeconds == null) {
            waitTimeInSeconds = 10;
        }
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInSeconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor));
    }

    // Använd denna istället för att kontrollera om ett Element finns med NoSuchElementException
    public boolean isElementPresent(By locator) {
        return Driver.getDriver().findElements(locator).size() > 0;
    }

    
    public void sleep() {
        sleep(SLEEP);
    }
    
    public void sleep(long l) {
        LOGGER.debug("##### Sleep(" + l + ") #####");
        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }

    public static void captureScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenShot" + DateUtil.dateToString(System.currentTimeMillis(), "-HH:mm") + ".png");
            LOGGER.error("Taking screenshot... " + destFile);
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            System.err.println("Error taking screenshot: " + e);
        }
    }
}
