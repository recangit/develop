package se.recan.app.cucumber;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import se.recan.app.Driver;
import se.recan.app.View;

/**
 *
 * @date 2014-maj-30
 * @author Anders Recksén (recan)
 */
public class PersonView extends View {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    
    @FindBy(css="input[name='firstName']")  WebElement firstName;
    @FindBy(css="input[name='lastName']")   WebElement lastName;
    @FindBy(css="input[name='socialNumb']") WebElement socialNumb;
    @FindBy(css="input[name='userName']")   WebElement userName;
    @FindBy(css="input[name='password']")   WebElement password;
    @FindBy(css="input[name='password2']")  WebElement password2;
    @FindBy(css="input[type='submit']")     WebElement submit;
    @FindBy(css="input[type='reset']")      WebElement reset;
    @FindBy(css="input[type='radio']") List<WebElement> gender;
    @FindBy(id="message")                   WebElement message;
    
    public void setFirstName(String value) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();   
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", firstName, "border: 2px solid red;");
        firstName.clear();
        firstName.sendKeys(value);
    }
    
    public void setLastName(String value) {
        lastName.clear();
        lastName.sendKeys(value);
    }
    
    public void setSocialNumb(String value) {
        socialNumb.clear();
        socialNumb.sendKeys(value);
    }
    
    public void setUserName(String value) {
        userName.clear();
        userName.sendKeys(value);
    }
    
    public void setPassword(String value) {
        password.clear();
        password.sendKeys(value);
    }
    
    public void setPassword2(String value) {
        password2.clear();
        password2.sendKeys(value);
    }
    
    public void setFemale() {
        gender.get(0).click();
    }
    
    public void setMale() {
        gender.get(1).click();
    }

    public void save() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();   
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", submit, "border: 2px solid red;");
        submit.click();
    }
    
    public void reset() {
        reset.click();
    }
    
    public String getMessage() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();   
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", message, "border: 2px solid red;");
        return message.getText();
    }
}
