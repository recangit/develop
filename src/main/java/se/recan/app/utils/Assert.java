package se.recan.app.utils;

import org.apache.log4j.Logger;

/**
 *
 * @author Anders Recksén (recan)
 */
public class Assert extends org.junit.Assert {

    protected static final Logger logger = Logger.getLogger(Logger.class);
    private static final boolean DEBUG = false;

    public static void assertTrue(String message, boolean condition) {
        try {
            org.junit.Assert.assertTrue(message, condition);
            if(DEBUG) {
                logger.info(message + " " + condition);
            }
        } catch (AssertionError e) {
            logger.info("AssertionError: " +e.getMessage());
            throw new AssertionError();
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        try {
            org.junit.Assert.assertEquals(expected, actual);
            if(DEBUG) {
                logger.info(expected + " " + actual);
            }
        } catch (AssertionError e) {
            logger.info("AssertionError: " +e.getMessage());
            throw new AssertionError();
        }
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        try {
            org.junit.Assert.assertEquals(message, expected, actual);
            if(DEBUG) {
                logger.info(message + " " + expected + " " + actual);
            }
        } catch (AssertionError e) {
            logger.info("AssertionError: " +e.getMessage());
            throw new AssertionError();
        }
    }
}