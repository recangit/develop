package se.recan.app.sikuli;

import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.api.*;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.visual.Canvas;
import org.sikuli.api.visual.DesktopCanvas;
import se.recan.app.Driver;
import se.recan.app.View;

/**
 * @date 2014-jul-03
 * @author Anders Recksén (recan)
 */
public class SearchTest extends View {

    @Rule
    public final Driver rule = new Driver();

    private static final String BASE_URL = "https://www.google.se";
    private static final Logger LOGGER = Logger.getLogger("SikuliTest");

    @Test
    public void testSearchWithPict() throws Exception {
        LOGGER.debug("");

        Driver.loadPage(BASE_URL);
        
        // Detta för att Google inte ska beskära bilderna
        Driver.getDriver().manage().window().maximize();

        // Skriv söktext
        Driver.getDriver().findElement(By.name("q")).sendKeys("Annie Recksén");
        
        // Klicka på sök
        Driver.getDriver().findElement(By.id("gbqfb")).click();
        
        // Vänta på att länken Bilder syns, klicka på den
        View.waitForElement(By.linkText("Bilder")).click();

        // Vet inte
        ScreenRegion screen = new DesktopScreenRegion();
        
        // Sparad bild att använda för sökning
        Target searchPict = new ImageTarget(new File("src/test/resources/pictures/annie.png"));

        // Leta efter bild och klicka på den
        Mouse mouse = new DesktopMouse();
        mouse.click(screen.wait(searchPict, 5000).getCenter());

        // Sök efter denna knapp och klicka på den
        Target button = new ImageTarget(new File("src/test/resources/pictures/button.png"));
        mouse.click(screen.wait(button, 5000).getCenter());
        
        // Vänta på att sidan laddas (content är en klass jag använder)
        View.waitForElement(By.className("content"));
        Assert.assertEquals("http://www.recksen.se/2008.html", Driver.getDriver().getCurrentUrl());
        
        Driver.getDriver().navigate().back();
        
        searchPict = new ImageTarget(new File("src/test/resources/pictures/annie2.png"));
        mouse.click(screen.wait(searchPict, 5000).getCenter());
        mouse.click(screen.wait(button, 5000).getCenter());

        View.waitForElement(By.className("content"));
        Assert.assertEquals("http://www.recksen.se/2010.html", Driver.getDriver().getCurrentUrl());
        
        Driver.getDriver().navigate().back();
        
        searchPict = new ImageTarget(new File("src/test/resources/pictures/kajsa.png"));
        mouse.click(screen.wait(searchPict, 5000).getCenter());
        mouse.click(screen.wait(button, 5000).getCenter());
        View.waitForElement(By.className("content"));
        sleep(1);
        
        WebElement e = View.waitForElement(By.cssSelector("img[alt='Gitarr']"));
        
        new Actions(Driver.getDriver()).moveToElement(e).perform();
        Assert.assertEquals("http://www.recksen.se/2010.html", Driver.getDriver().getCurrentUrl());
        
        URL imageURL = new URL("http://recksen.se/images/IMG_1281.jpg");
        searchPict = new ImageTarget(imageURL);
        ScreenRegion croppedScreen = screen.wait(searchPict, 5000);

        // Construct a Canvas object of the type DesktopCanvas
        Canvas canvas = new DesktopCanvas();
        // Add a box around a screen region 'r'
        canvas.addBox(croppedScreen);
        // Add a label on the screen region r
        canvas.addLabel(croppedScreen, "We found Kajsa!!!");
        // Display the canvas for 3 seconds
        canvas.display(3);
        
        // Detta bara för att ta en bild av resultatet
        ImageIO.write(screen.capture(), "png", new File("saved.png"));
    }
}
