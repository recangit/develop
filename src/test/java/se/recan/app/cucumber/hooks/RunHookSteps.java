package se.recan.app.cucumber.hooks;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * 2014-aug-21
 * @author Anders Recksén (recan)
 */
@RunWith(Cucumber.class)
@Cucumber.Options( 
        glue = "se.recan.app.cucumber.hooks",
        features = "features/hooks.feature",
        format = {"pretty", "html:target/Cucumber"}
)

public class RunHookSteps {
}
