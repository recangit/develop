package se.recan.app.graphic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import se.recan.app.View;

/**
 *
 * @author anders
 */
public class GraphicView extends View {

    @FindBy(css="input[name='x']")
    private WebElement x;
    
    public void setX() {
        x.clear();
        x.sendKeys("199");
    }
}
