package se.recan.app;

import java.util.logging.Level;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @date 2014-maj-26
 * @author Anders Recksén (recan)
 */
public class LogDriver {

    private static final Level LOG_LEVEL = Level.ALL;

    private static FirefoxDriver driver;

    public static FirefoxDriver getDriver() {

        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable(LogType.BROWSER, LOG_LEVEL);
        prefs.enable(LogType.CLIENT, LOG_LEVEL);
        prefs.enable(LogType.DRIVER, LOG_LEVEL);
        prefs.enable(LogType.PERFORMANCE, LOG_LEVEL);
        prefs.enable(LogType.PROFILER, LOG_LEVEL);
        prefs.enable(LogType.SERVER, LOG_LEVEL);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, prefs);

        driver = new FirefoxDriver(desiredCapabilities);

        return driver;
    }

    public static void loadPage(String url) {
        driver.get(url);
    }

    public static void close() {
        driver.quit();
    }

    public static String getLog() {
        StringBuilder builder = new StringBuilder();

        Logs log = driver.manage().logs();

        LogEntries logEntries = log.get(LogType.BROWSER);
        builder.append("\nBROWSER");
        builder.append("\n");
        for (LogEntry logEntry : logEntries) {
            builder.append(logEntry.getMessage());
            builder.append("\n");
        }

        logEntries = log.get(LogType.CLIENT);
        builder.append("CLIENT");
        builder.append("\n");
        for (LogEntry logEntry : logEntries) {
            builder.append(logEntry.getMessage());
            builder.append("\n");
        }

        logEntries = log.get(LogType.DRIVER);
        builder.append("DRIVER");
        builder.append("\n");
        for (LogEntry logEntry : logEntries) {
            builder.append(logEntry.getMessage());
            builder.append("\n");
        }

        logEntries = log.get(LogType.PERFORMANCE);
        builder.append("PERFORMANCE");
        builder.append("\n");
        for (LogEntry logEntry : logEntries) {
            builder.append(logEntry.getMessage());
            builder.append("\n");
        }

        logEntries = log.get(LogType.PROFILER);
        builder.append("PROFILER");
        builder.append("\n");
        for (LogEntry logEntry : logEntries) {
            builder.append(logEntry.getMessage());
            builder.append("\n");
        }

        logEntries = log.get(LogType.SERVER);
        builder.append("SERVER");
        builder.append("\n");
        for (LogEntry logEntry : logEntries) {
            builder.append(logEntry.getMessage());
            builder.append("\n");
        }

        return builder.toString();
    }
}
