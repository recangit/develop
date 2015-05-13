package se.recan.app.utils;

import org.apache.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * 
 * @author Anders Recksén (recan)
 */
public class AfLogRule extends TestWatcher {

    private final Logger LOGGER = Logger.getLogger("Logger");
    
    @Override
    protected void failed(Throwable e, Description description) {
        
        StackTraceElement[] stack = e.getStackTrace();

        if (!e.toString().contains("Assert")) {
            if (e.toString().contains("NoSuchElementException")) {
                LOGGER.error("org.openqa.selenium.NoSuchElementException");
            } else if (e.toString().contains("ElementNotVisibleException")) {
                LOGGER.error("org.openqa.selenium.ElementNotVisibleException");
            } else if (e.toString().contains("StaleElementReferenceException")) {
                LOGGER.error("org.openqa.selenium.StaleElementReferenceException");
            } else if (e.toString().contains("WebDriverException")) {
                LOGGER.error("org.openqa.selenium.WebDriverException");
            } else if (e.toString().contains("TimeoutException")) {
                LOGGER.error("org.openqa.selenium.TimeoutException");
            } else {
                LOGGER.error(e);
            }
        }
        
        for (int i = 0; i < stack.length; i++) {
            if (stack[i].toString().contains("se.af.ext")) {
                LOGGER.error(stack[i]);
            }
        }
    }
}