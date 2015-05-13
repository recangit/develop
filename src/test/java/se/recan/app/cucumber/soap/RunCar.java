package se.recan.app.cucumber.soap;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author Anders Recksén (recan)
 */ 
@RunWith(Cucumber.class)
@Cucumber.Options( 
        glue = "se.recan.app.cucumber.soap",
        features = "features/car.feature",
        format = {"pretty", "html:target/Cucumber"}
)

public class RunCar {
}