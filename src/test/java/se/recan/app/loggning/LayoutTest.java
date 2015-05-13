package se.recan.app.loggning;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Test;

/**
 * Det finns flera Appenders som styr var loggresultatet ska hamna, ex:
 * AppenderSkeleton
 * ConsoleAppender
 * DailyRollingFileAppender
 * FileAppender
 * JDBCAppender
 * 
 * En Appender använder sig av en Layout, ex:
 * DateLayout
 * HTMLLayout
 * PatternLayout
 * SimpleLayout
 * XMLLayout
 * 
 * Loggnivåer:
 * TRACE, DEBUG, INFO, WARN, ERROR, FATAL
 * 2013-jul-19
 *
 * @author Anders Recksén recan
 */
public class LayoutTest {

    private final Logger LOGGER = LogManager.getLogger(LayoutTest.class.getName());

    @Test
    public void testLayout() {
        try {
            PatternLayout pattern = new PatternLayout("%c{1}, %l, %L, %p, %r, %t, %x, %X, %m, %M %n");
            
            FileAppender console = new FileAppender(pattern, "TestLogFile.log");
            LOGGER.addAppender(console);
            LOGGER.setLevel(Level.ALL);
            LOGGER.fatal("-------------------------------");
            LOGGER.debug("Debu nivå");
            LOGGER.info("Info nivå");
            LOGGER.warn("Warn nivå");
            LOGGER.error("Error nivå");
            LOGGER.fatal("Fatal nivå");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}