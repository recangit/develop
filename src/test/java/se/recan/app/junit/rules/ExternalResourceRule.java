package se.recan.app.junit.rules;

import org.apache.log4j.Logger;
import org.junit.rules.ExternalResource;

/**
 *
 * @date 2014-jun-10
 * @author Anders Recksén (recan)
 */
public class ExternalResourceRule extends ExternalResource {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private final String name;

    public ExternalResourceRule(String name) {
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
}
