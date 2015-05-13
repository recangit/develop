package se.recan.app.mbt;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.apache.log4j.Logger;
import se.recan.app.Driver;
import se.recan.app.Helper;
import se.recan.app.person.PersonPO;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.test.TestBuilder;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.generator.RandomPath;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.ClassRule;

public class PersonModel extends ExecutionContext implements Person {

    @ClassRule
    public static Driver classRule = new Driver();
    
    public final static Path MODEL_PATH = Paths.get("src/test/resources/mbt/person.graphml");
    private static final Logger LOGGER = Logger.getLogger("Logger");
    private static final Logger TRACE = Logger.getLogger("Trace");
    private static final int SLEEP = 1;

    private PersonPO po;
    
//    public PersonModel(File model, boolean efsm, PathGenerator generator, boolean weight) {
//        super(model, efsm, generator, weight);
//    }

    @Override
    public void a_FirstName() {
        TRACE.trace("");
        po.set("firstName", "Anders");
//        po.set("firstName", getMbt().getDataValue("firstName"));
        Helper.sleep();
    }

    @Override
    public void a_Gender() {
        TRACE.trace("");
        po.click(1);
//        po.click(Integer.parseInt(getMbt().getDataValue("gender")));
        Helper.sleep();
    }

    @Override
    public void a_Granska() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void a_InvalidFirstName() {
        TRACE.trace("");
        po.set("firstName", "A");
//        po.set("firstName", getMbt().getDataValue("invalidFirstName"));
        Helper.sleep();
    }

    @Override
    public void a_InvalidSocialNumb() {
        TRACE.trace("");
        po.set("socialNumb", "6897879");
//        po.set("socialNumb", getMbt().getDataValue("invalidSocialNumb"));
        Helper.sleep();
    }

    @Override
    public void a_LastName() {
        TRACE.trace("");
        po.set("lastName", "Recksèn");
//        po.set("lastName", getMbt().getDataValue("lastName"));
        Helper.sleep();
    }

    @Override
    public void a_PassWord() {
        TRACE.trace("");
        po.set("password", "malla");
//        po.set("password", getMbt().getDataValue("passWord"));
        Helper.sleep();
    }

    @Override
    public void a_PassWord2() {
        TRACE.trace("");
        po.set("password2", "malla");
//        po.set("password2", getMbt().getDataValue("passWord2"));
        Helper.sleep();
    }

    @Override
    public void a_SocialNumb() {
        TRACE.trace("");
        po.set("socialNumb", "621002-4318");
//        po.set("socialNumb", getMbt().getDataValue("socialNumb"));
        Helper.sleep();
    }

    @Override
    public void a_StartBrowser() {
        TRACE.trace("");
        po = new PersonPO(Driver.getDriver());
        Driver.loadPage("http://localhost:8080/SimpleWebApp/person");
        Helper.sleep();
    }

    @Override
    public void a_Submit() {
        TRACE.trace("");
        po.submit();
        Helper.sleep();
    }

    @Override
    public void a_UserName() {
        TRACE.trace("");
        po.set("userName", "recan");
//        po.set("userName", getMbt().getDataValue("userName"));
        Helper.sleep();
    }

    @Override
    public void s_ExitBrowser() {
        TRACE.trace("");
        
        Helper.sleep();
        Driver.close();
    }

    @Override
    public void s_FirstName() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_Gender() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_InvalidFirstName() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_LastName() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_PassWord() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_PassWord2() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_SocialSecurity() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_StartBrowser() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_Submit() {
        TRACE.trace("");
        
        boolean valid = false;//Boolean.parseBoolean(getMbt().getDataValue("valid"));
        if(valid) {
            Assert.assertEquals("Lägg till en post", po.getMessage());
        }
        Helper.sleep();
    }

    @Override
    public void s_UserName() {
        TRACE.trace("");

        Helper.sleep();
    }

    @Override
    public void s_invalidSocialSecurity() {
        TRACE.trace("");

        Helper.sleep();
    }
    
    /**
     * Smoke test
     * Använder A* algoritmen.
     * Går raka vägen från a_Init till s_Browse.
     */
    @Test
    public void runSmokeTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new PersonModel())
            .setPathGenerator(new AStarPath(new ReachedVertex("s_ExitBrowser")))
            .setStart("a_StartBrowser")
            .execute();
    }
    
    /**
     * Funktionell test
     * Startar på a_Init och passerar det antal edges som angets i procent.
     */
//    @Test
    public void runFunctionalTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new PersonModel())
            .setPathGenerator(new RandomPath(new EdgeCoverage(100)))
            .setStart("a_StartBrowser")
            .execute();
    }

    /**
     * Stabilitet
     * Kör modellen så länge som angets i tid.
     */
//    @Test
    public void runStabilityTest() {
        new TestBuilder()
            .setModel(MODEL_PATH)
            .setContext(new PersonModel())
            .setPathGenerator(new RandomPath(new TimeDuration(3, TimeUnit.SECONDS)))
            .setStart("a_StartBrowser")
            .execute();
    }
    
    @BeforeClass
    public static void init() {
        File log = new File("logs");
        if(!log.exists()) {
            log.mkdir();
        }
    }
}
