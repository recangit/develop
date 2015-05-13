package se.recan.app.db.h2;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.recan.app.bestpractice.BestPracticeDAO;
import se.recan.app.bestpractice.BestPracticeTO;

/**
 *
 * @date 2014-jun-16
 * @author Anders Recksén (recan)
 */
public class BestPracticeTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void testMeta() throws SQLException {
        String meta = BestPracticeDAO.getMetaData();
        LOGGER.debug(meta);
    }
    
    @Test
    public void testTransaction() throws SQLException {
        LOGGER.debug("");
        
        Assert.assertEquals(4, BestPracticeDAO.count("Albums"));
        Assert.assertEquals(2, BestPracticeDAO.count("Artists"));
        Assert.assertEquals(2, BestPracticeDAO.count("Gengres"));
    }

    private void debug() throws SQLException {
        LOGGER.debug("");
        List<BestPracticeTO> list = BestPracticeDAO.getAll();
        for (BestPracticeTO to : list) {
            LOGGER.debug(to.toString());
        }
    }
    
    @Before
    public void before() throws Exception {
        BestPracticeDAO.cleanTable();
        BestPracticeDAO.populate(new BestPracticeTO("Scary Monsters", "David Bowie", 2, "rock"));
        BestPracticeDAO.populate(new BestPracticeTO("Pinups", "David Bowie", 2, "rock"));
        BestPracticeDAO.populate(new BestPracticeTO("Mule Variations", "Tom Waits", 2, "diverse"));
        BestPracticeDAO.populate(new BestPracticeTO("Rain Dogs", "Tom Waits", 2, "rock"));
    }

    @BeforeClass
    public static void createSchema() throws Exception {
        BestPracticeDAO.createSchema();
    }
}
