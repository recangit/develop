package se.recan.app.sikuli;

import java.awt.Rectangle;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;

/**
 * @date 2014-jul-03
 * @author Anders Recksén (recan)
 */
public class CanvasTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void testCanvas() throws Exception {
        LOGGER.debug("");
        
        // Create a screen region object that corresponds to the default monitor in full screen 
        ScreenRegion croppedScreen = new DesktopScreenRegion();
        
        croppedScreen.setBounds(new Rectangle(200, 200, 200, 200));
        // Construct a Canvas object of the type DesktopCanvas
        Canvas canvas = new DesktopCanvas();
        // Add a box around a screen region 'r'
        canvas.addBox(croppedScreen);
        // Add a label on the screen region r
        canvas.addLabel(croppedScreen, "Hola!!!");
        // Display the canvas for 3 seconds
        canvas.display(3);
        
    }
}
