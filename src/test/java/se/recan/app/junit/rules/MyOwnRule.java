package se.recan.app.junit.rules;

import org.apache.log4j.Logger;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 * @date 2014-jun-09
 * @author Anders Recksén (recan)
 */
public class MyOwnRule implements TestRule {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private final String name;

    public MyOwnRule(String name) {
        this.name = name;
    }

    /**
     * Modifies the method-running Statement to implement this test-running
     * rule.
     *
     * @param statement - The Statement to be modified
     * @param description - A Description of the test implemented in base
     * @return A new statement, which may be the same as base, a wrapper around
     * base, or a completely new Statement
     */
    @Override
    public Statement apply(final Statement statement, final Description description) {
        LOGGER.debug(name);

        /**
         * Represents one or more actions to be taken at runtime in the course of running a JUnit test suite. 
         */
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                LOGGER.debug(statement + " " + description.getDisplayName());
                statement.evaluate();
            }
        };
    }
}
