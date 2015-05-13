package se.recan.app.mbt.login;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.java.test.TestBuilder;
import org.junit.Test;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.junit.Assert;

/**
 *
 * 2014-okt-20
 * @author Anders Recksén (recan)
 */
public class LoginTest extends ExecutionContext implements Login {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    public final static Path MODEL_PATH = Paths.get("src/test/resources/mbt/Login.graphml");
    
    @Override
    public void a_Login() {
        LOGGER.debug("Inloggning, valid [" + this.getAttribute("valid") + "] och check [" + this.getAttribute("check") + "]");
    }
    
    @Override
    public void s_Login() throws Exception {
        LOGGER.debug("");
    }
    
    @Override
    public void a_Verify() {
        LOGGER.debug("Registrera antal iterationer:" + this.getAttribute("iter"));
    }

    @Override
    public void s_Verify() throws Exception {
        double iterationer = (Double)this.getAttribute("iter");
        Assert.assertTrue("Antal iterationer är lägre än fyra", iterationer < 4);
    }
    
    @Override
    public void a_Start() {
        LOGGER.debug("Får endast gå in i denna metod om valid är true [" + this.getAttribute("valid") + "]");
        
        Assert.assertTrue("Parameter valid är true", (Boolean)this.getAttribute("valid"));   
    }

    @Override
    public void s_Start() {
        LOGGER.debug("");
    }
    
    @Override
    public void a_Stop() {
        LOGGER.debug("");
    }
    
    @Override
    public void s_Stop() {
        LOGGER.debug("");
    }

    @Override
    public void a_Exit() {
        LOGGER.debug("Får endast gå in i denna metod om antal iterartioner är fyra [" + this.getAttribute("iter") + "]");
        
        double iterationer = (Double)this.getAttribute("iter");
        Assert.assertTrue(iterationer == 4);
    }
    
    @Override
    public void s_Exit() {
        LOGGER.debug("");
    }

     /**
     * Smoke test
     * Använder A* algoritmen.
     * Går raka vägen från a_Login till s_Exit.
     */
//    @Test
    public void runSmokeTest() {
        try {
            
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new LoginTest())
            .setPathGenerator(new AStarPath(new ReachedVertex("s_Exit")))
            .setStart("a_Login")
            .execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funktionell test
     * Startar på a_Login och passerar det antal edges som angets i procent.
     */
//    @Test
    public void runFunctionalTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new LoginTest())
            .setPathGenerator(new RandomPath(new EdgeCoverage(100)))
            .setStart("a_Login")
            .execute();
    }

    /**
     * Stabilitet
     * Kör modellen så länge som angets i tid.
     */
    @Test
    public void runStabilityTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new LoginTest())
            .setPathGenerator(new RandomPath(new TimeDuration(10, TimeUnit.SECONDS)))
            .setStart("a_Login")
            .execute();
    }
}
