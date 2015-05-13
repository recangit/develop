package se.recan.app.cucumber;

import cucumber.annotation.sv.Givet;
import cucumber.annotation.sv.När;
import cucumber.annotation.sv.Så;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import se.recan.app.Driver;

/**
 *
 * @author Anders Recksén (recan)
 */
public class PersonSteps {

    private static final PersonView person = PageFactory.initElements(Driver.getDriver(), PersonView.class);
    private static final int SLEEP = 0;
    private static final Logger LOGGER = Logger.getLogger("PersonSteps");

    @Givet("^att jag navigerat till personsidan$")
    public void init() {
        LOGGER.debug("");
        
        Driver.loadPage("http://localhost:8080/SimpleWebApp/person");

    }

    @När("^jag klickar på Spara$")
    public void save() {
        LOGGER.debug("");

        person.save();
        person.sleep(SLEEP);
    }

    @Så("^ska meddelandet vara '(.+)'$")
    public void message(String expected) {
//        LOGGER.debug();

        String actual = person.getMessage();
        Assert.assertEquals("Meddelande", expected, actual);
        person.sleep(SLEEP);
    }

    @När("^jag fyller i fältet förnamn med '(.+)'$")
    public void firstName(String firstname) {
        LOGGER.debug(firstname);

        person.setFirstName(firstname);
        person.sleep(SLEEP);
    }

    @När("^jag fyller i fältet efternamn med '(.+)'$")
    public void lastName(String lastname) {
        LOGGER.debug(lastname);

        person.setLastName(lastname);
        person.sleep(SLEEP);
    }

    @När("^jag fyller i fältet personnummer med '(.+)'$")
    public void socialSecurityNumb(String numb) {
        LOGGER.debug(numb);
        
        person.setSocialNumb(numb);
        person.sleep(SLEEP);
    }

    @När("^jag klickar på Man$")
    public void male() {
        LOGGER.debug("");

        person.setMale();
        person.sleep(SLEEP);
    }

    @När("^jag klickar på Kvinna$")
    public void female() {
        LOGGER.debug("");
        
        person.setFemale();
        person.sleep(SLEEP);
    }

    @När("^jag fyller i fältet användarnamn med '(.+)'$")
    public void userName(String username) {
        LOGGER.debug(username);
        
        person.setUserName(username);
        person.sleep(SLEEP);
    }

    @När("^jag fyller i fältet lösenord med '(.+)'$")
    public void password(String password) {
        LOGGER.debug(password);

        person.setPassword(password);
        person.sleep(SLEEP);
    }

    @När("^jag fyller i fältet lösenord igen med '(.+)'$")
    public void password2(String password2) {
        LOGGER.debug(password2);

        person.setPassword2(password2);
        person.sleep(SLEEP);
    }
}
