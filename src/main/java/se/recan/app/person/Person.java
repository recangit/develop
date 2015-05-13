package se.recan.app.person;

import java.io.File;
import org.apache.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Element;
import se.recan.utils.CacheUtil;
import se.recan.utils.ValidateUtil;
import se.recan.utils.XmlUtil;

/**
 * 2013-feb-08
 *
 * @author Anders Recksén (recan)
 */
public class Person {

    private static final Logger LOGGER = Logger.getLogger(Person.class);

    private int id = -1;
    private String firstName = "";
    private String lastName = "";
    private String userName = "";
    private String password = "";
    private String password2 = "";
    private String socialNumb = "";
    private int age = -1;
    private int gender = -1;
    private boolean empty = true;

    public Person() {
    }

    public Person(String firstName) {
        this.firstName = firstName;
        empty = false;
    }
    
    public Person(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
        empty = false;
    }
    
    public Person(int id, String firstName, String lastName, String userName, String password, String password2, String socialNumb, int age, int gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.password2 = password2;
        this.socialNumb = socialNumb;
        this.age = age;
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        empty = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isFirstName() {
        return ValidateUtil.validate(firstName, 3);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        empty = false;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isLastName() {
        return ValidateUtil.validate(lastName, 2);
    }

    public void setUserName(String userName) {
        this.userName = userName;
        empty = false;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isUserName() {
        return ValidateUtil.validate(userName, 5);
    }

    public void setPassword(String password) {
        this.password = password;
        empty = false;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPassword() {
        return ValidateUtil.validate(password, 5);
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
        empty = false;
    }

    public String getPassword2() {
        return password2;
    }

    public boolean isPassword2() {
        return ValidateUtil.validate(password2) && this.password.equals(password2);
    }

    public void setSocialNumb(String socialNumb) {
        this.socialNumb = socialNumb;
        empty = false;
    }

    public void setSocialNumb(long socialNumb) {
        LOGGER.debug("");
        try {
            LOGGER.debug("socialNumb:" + socialNumb);
            this.socialNumb = Long.toString(socialNumb);
            empty = false;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug(e);
        }
    }

    public String getSocialNumb() {
        return socialNumb;
    }

    public boolean isSocialNumb() {
        return ValidateUtil.validateSocialSecurityNumber(socialNumb);
    }

    public void setGender(int gender) {
        this.gender = gender;
        empty = false;
        if (gender > 2) {
            throw new IllegalArgumentException("HEN är inte implementerat än" + gender);
        }
    }

    public int getGender() {
        return gender;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }
    public boolean isGender() {
        return ValidateUtil.getGender(socialNumb) == gender;
    }

    public String getMessage() {
        if (empty) {
            return CacheUtil.getProperty("se.recan.app.person.Person.add");
        }

        if (firstName.isEmpty()
                && lastName.isEmpty()
                && userName.isEmpty()
                && password.isEmpty()
                && password2.isEmpty()
                && socialNumb.isEmpty()
                && gender == -1) {
            return CacheUtil.getProperty("se.recan.app.person.Person.empty");
        }

        if (!isFirstName()) {
            return CacheUtil.getProperty("se.recan.app.person.Person.firstname");
        }

        if (!isLastName()) {
            return CacheUtil.getProperty("se.recan.app.person.Person.lastname");
        }

        if (!isSocialNumb()) {
            return CacheUtil.getProperty("se.recan.app.person.Person.socialnumb");
        }

        if (!isGender()) {
            return gender == 0 ? CacheUtil.getProperty("se.recan.app.person.Person.male") : CacheUtil.getProperty("se.recan.app.person.Person.female");
        }

        if (!isUserName()) {
            return CacheUtil.getProperty("se.recan.app.person.Person.username");
        }

        if (!isPassword()) {
            return CacheUtil.getProperty("se.recan.app.person.Person.password");
        }

        if (!isPassword2()) {
            return CacheUtil.getProperty("se.recan.app.person.Person.password2");
        }

        return CacheUtil.getProperty("se.recan.app.person.Person.ok");
    }

    public Element toXml() {
        Element element = new Element("person");
        element.setAttribute(new Attribute("id", Integer.toString(this.id)));

        Element e = new Element("firstName");
        e.setAttribute(new Attribute("value", this.firstName));
        element.addContent(e);

        e = new Element("lastName");
        e.setAttribute(new Attribute("value", this.lastName));
        element.addContent(e);

        e = new Element("userName");
        e.setAttribute(new Attribute("value", this.userName));
        element.addContent(e);

        e = new Element("password");
        e.setAttribute(new Attribute("value", this.password));
        element.addContent(e);

        e = new Element("password2");
        e.setAttribute(new Attribute("value", this.password2));
        element.addContent(e);

        e = new Element("socialNumb");
        e.setAttribute(new Attribute("value", this.socialNumb));
        element.addContent(e);

        e = new Element("gender");
        e.setAttribute(new Attribute("value", this.gender == 0 ? "0" : "1"));
        element.addContent(e);

        return element;
    }

    public void save(File xmlFile) throws Exception {
        XmlUtil util = new XmlUtil(xmlFile);

        util.toFile(this.toXml(), "persons");
//        LOGGER.debug(xmlFile.getAbsolutePath());
//        LOGGER.debug("Antal poster: " + util.getNumberOfPosts(xmlFile));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("\n");
        builder.append(this.id);
        builder.append("\n");
        builder.append(this.firstName);
        builder.append("\n");
        builder.append(this.lastName);
        builder.append("\n");
        builder.append(this.userName);
        builder.append("\n");
        builder.append(this.password);
        builder.append("\n");
        builder.append(this.password2);
        builder.append("\n");
        builder.append(this.socialNumb);
        builder.append("\n");
        builder.append(this.gender);
        builder.append("\n");
        builder.append(this.age);
        builder.append("\n");

        return builder.toString();
    }
}
