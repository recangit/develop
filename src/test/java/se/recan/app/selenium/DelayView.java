package se.recan.app.selenium;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import se.recan.app.Driver;

/**
 *
 * 2014-okt-27
 *
 * @author Anders Recksén (recan)
 */
public class DelayView {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @FindBy(partialLinkText = "Testa ajax")
    private WebElement ajaxLink;

    @FindBy(css = "p[class=message]")
    private WebElement mes;

    @FindBy(id = "finnsInte")
    private WebElement nada;

//    public DelayView() {
//        PageFactory.initElements(Driver.getDriver(), this);
//    }
    public void clickAjaxLinkLink() {
        ajaxLink.click();
    }

    public String getMessage() {
        return mes.getText();
    }

    public void clickNada() {
        nada.click();
    }
}
