package se.recan.app.ddt;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import se.recan.app.Driver;
import se.recan.app.View;
import se.recan.app.person.Person;

/**
 *
 * @date 2014-maj-08
 * @author Anders Recksén (recan)
 */
public class PersonPOJO extends View {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    
    private static final String BASE_URL = "http://localhost:8080/SimpleWebApp/person";
//    private static final String BROWSER = System.getProperty("browser", "firefox");

    @FindBy(id = "message") WebElement message;
    @FindBy(css="input[name='firstName']") WebElement firstName;
    @FindBy(css="input[name='lastName']") WebElement lastName;
    @FindBy(css="input[name='socialNumb']") WebElement socialNumb;
    @FindBy(css="input[name='userName']") WebElement userName;
    @FindBy(css="input[name='password']") WebElement password;
    @FindBy(css="input[name='password2']") WebElement password2;
    @FindBy(id="save") WebElement save;
    @FindBy(id="delete") WebElement delete;
    @FindBy(css="input[type='radio']") List<WebElement> radio;
    @FindBy(id="personlista") static WebElement personlista;
    
    public PersonPOJO() {
        LOGGER.trace("-----");
        PageFactory.initElements(Driver.getDriver(), this);
    }
    
    public void init(String value) {
        LOGGER.trace("-----");

        Driver.loadPage(BASE_URL);
        Assert.assertEquals(value, message.getText());
    }

    public void setFirstName(String value) {
        LOGGER.trace("-----");
        firstName.clear();
        firstName.sendKeys(value);
    }

    public void setLastName(String value) {
        LOGGER.trace("-----");
        lastName.clear();
        lastName.sendKeys(value);
    }

    public void setSocialSecurityNumb(String value) {
        LOGGER.trace("-----");
        socialNumb.clear();
        socialNumb.sendKeys(value);
    }

    public void setGender(String value) {
    	LOGGER.trace("-----");
        int position = Integer.parseInt(value);
        radio.get(position).click();
    }
    
    public void setGender(int value) {
    	LOGGER.trace("-----");
        radio.get(value).click();
    }

    public void setUserName(String value) {
        LOGGER.trace("-----");
        userName.clear();
        userName.sendKeys(value);
    }

    public void setPassword(String value) {
        LOGGER.trace("-----");
        password.clear();
        password.sendKeys(value);
    }
    
    public void setPassword2(String value) {
        LOGGER.trace("-----");
        password2.clear();
        password2.sendKeys(value);
    }

    public void save(String value) {
        LOGGER.trace("-----");
        save.click();
        Assert.assertEquals(value, message.getText());
    }
    
    public void delete(String value) {
        LOGGER.trace("-----");
        try {           
            delete.click();
        } catch (NoSuchElementException e) {
            LOGGER.error("FEL", e);
            
        }
        Assert.assertEquals(value, message.getText());
    }
    
    public static List<Person> getResult() {
        LOGGER.trace("-----");
        
        List<Person> result = new ArrayList<Person>();
        
        List<WebElement> trs = personlista.findElements(By.tagName("tr"));
        for(WebElement tr: trs) {
            List<WebElement> td = tr.findElements(By.tagName("td"));
            result.add(new Person(td.get(0).getText()));
        }
            
        return result;
    }
}
