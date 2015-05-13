package se.recan.app.cucumber;

import cucumber.junit.Cucumber;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import se.recan.app.Driver;

/**
 *
 * @author Anders Recksén (recan)
 */ 
@RunWith(Cucumber.class)
@Cucumber.Options( 
        glue = "se.recan.app.cucumber",
        features = "features/person.feature",
        format = {"pretty", "html:target/Cucumber"}
)

public class RunPerson {
    @ClassRule
    public static final Driver classRule = new Driver();
}