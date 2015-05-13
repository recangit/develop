package se.recan.app.cucumber.hooks;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.sv.Givet;
import cucumber.annotation.sv.Så;
import org.apache.log4j.Logger;
import org.junit.Assert;

/**
 *
 * 2014-aug-21
 *
 * @author Anders Recksén (recan)
 */
public class HookStep {

    private static final Logger LOGGER = Logger.getLogger("Trace");

    @Givet("^att applikationen förberetts$")
    public void init() {
        LOGGER.trace("Exekveras före VARJE Scenario, definierat i Bakgrund");
    }

    @Givet("^jag heter Anders$")
    public void name() {
        LOGGER.trace("Exekveras före VARJE Scenario, definierat i Bakgrund");
    }

    @Så("^är vi redo att exekvera scenarios$")
    public void scenarios() {
        LOGGER.trace("Exekveras före VARJE Scenario, definierat i Bakgrund");
    }

    @Så("^metod1 exekveras$")
    public void exekvera1() {
        LOGGER.trace("");
    }

    @Så("^ska jag vara i validera1$")
    public void validera1() {
        LOGGER.trace("");
        Assert.assertTrue(true);
    }

    @Så("^metod2 exekveras$")
    public void exekvera2() {
        LOGGER.trace("");
    }

    @Så("^ska jag vara i validera2$")
    public void validera2() {
        LOGGER.trace("");
    }

    @Så("^metod3 exekveras$")
    public void exekvera3() {
        LOGGER.trace("");
    }

    @Så("^ska jag vara i validera3$")
    public void validera3() {
        LOGGER.trace("");
    }

    @Before
    public void before() {
        LOGGER.trace("Exekveras före VARJE Scenario");
    }

    @After
    public void after() {
        LOGGER.trace("Exekveras efter VARJE Scenario");
    }

    @Before("@once")
    public void beforeHook() {
        LOGGER.trace("Exekveras före Scenario med hooks @once @twice");
    }

    @After({"@once, @twice"})
    public void afterHook() {
        LOGGER.trace("Exekveras efter Scenario med hooks @once");
    }
    
    @Before("@init")
    public void initApp() {
        LOGGER.trace("Exekveras endast EN gång FÖRE alla andra metoder");
    }
    
    @After("@clean")
    public void cleanApp() {
        LOGGER.trace("Exekveras endast EN gång EFTER alla andra metoder");
    }
}
