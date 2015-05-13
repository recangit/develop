package se.recan.app.junit;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Två olika sätt att bestämma vilken ordning testmetoderna skall exekveras i.
 * Slumpartad och i bokstavsordning.
 * @date 2014-maj-18
 * @author Anders Recksén (recan)
 */

@FixMethodOrder(MethodSorters.JVM)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExecutionOrderTest {

    private static final Logger LOGGER = Logger.getLogger("ExecutionOrder");
    
    @Test
    public void testA() { LOGGER.debug(""); }
    
    @Test
    public void testB() { LOGGER.debug(""); }
    
    @Test
    public void testC() { LOGGER.debug(""); }
       
    @Test
    public void testD() { LOGGER.debug(""); }
}