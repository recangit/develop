package se.recan.app.person;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.recan.utils.CacheUtil;

/**
 * När Person populeras via EntityUtil förutsätts att alla fält är satta.
 * Alltså, exempelvis parametern lastName skickas via requesten oavsett den är
 * tom eller ej. är den tom kommer valideringen att misslyckas. I dessa tester
 * är det däremot möjligt att testa varje fält för sig.
 *
 * @author Anders Recksén (recan)
 */
public class PersonValidateTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private Person person;

    @Test
    public void testSetFirstName() throws Exception {
        LOGGER.debug(person.getMessage());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.add"), person.getMessage());

        person.setFirstName("");
        Assert.assertFalse(person.isFirstName());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.empty"), person.getMessage());

        person.setFirstName("An");
        Assert.assertFalse(person.isFirstName());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.firstname"), person.getMessage());

        person.setFirstName("Anders");
        Assert.assertTrue(person.isFirstName());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.lastname"), person.getMessage());

        person.setLastName("Recksen");
        Assert.assertTrue(person.isLastName());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.socialnumb"), person.getMessage());

        person.setSocialNumb("621002-43");
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.socialnumb"), person.getMessage());

        person.setSocialNumb("621002-4318");
        Assert.assertTrue("Personnummer kan vara en sträng och innehålla bindestreck", person.isSocialNumb());

        person.setSocialNumb("621002:4318");
        Assert.assertTrue("Personnummer kan vara en sträng och innehålla kolon", person.isSocialNumb());

        person.setSocialNumb("6210024318");
        Assert.assertTrue("Personnummer kan vara en sammansatt sträng", person.isSocialNumb());

        person.setSocialNumb(6210024318l);
        Assert.assertTrue("Personnummer kan vara en long", person.isSocialNumb());

        person.setGender(0);
        Assert.assertFalse("Detta personnummer tillhör inte en kvinna", person.isGender());

        person.setGender(1);
        Assert.assertTrue("Detta personnummer tillhör en man", person.isGender());

        person.setUserName("reca");
        Assert.assertFalse("Värdet måste innehålla minst fem tecken", person.isUserName());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.username"), person.getMessage());

        person.setUserName("recan");
        Assert.assertTrue(person.isUserName());

        person.setPassword("abc");
        Assert.assertFalse("Värdet måste innehålla minst fem tecken", person.isPassword());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.password"), person.getMessage());

        person.setPassword("abcd-ef");
        Assert.assertTrue(person.isPassword());

        person.setPassword2("abcd-e");
        Assert.assertFalse("Värdena password och password2 måste vara identiska", person.isPassword2());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.password2"), person.getMessage());

        person.setPassword2("abcd-ef");
        Assert.assertTrue(person.isPassword2());
        Assert.assertEquals(CacheUtil.getProperty("se.recan.app.person.Person.ok"), person.getMessage());
    }

    @Before
    public void setUp() throws Exception {
        CacheUtil.initCache("SE");
        person = new Person();
    }
}
