package se.recan.app.junit.rules;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @date 2014-jun-10
 * @author Anders Recksén (recan)
 */
public class TemporaryFolderTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        File file = folder.newFile("myfile.txt");
        File dir = folder.newFolder("subfolder");

        Assert.assertTrue(file.exists());
        Assert.assertTrue(dir.exists());
    }
}
