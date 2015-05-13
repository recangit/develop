package se.recan.app.person;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Anders Recksén (recan)
 */
public class PersonPO {

    private final WebDriver driver;
    
    public PersonPO(WebDriver driver) { this.driver = driver; }
    
    public void setAttribute() {
        WebElement form = driver.findElement(By.id("person"));
        JavascriptExecutor js = (JavascriptExecutor) driver;   
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", form, "display: none;");
        sleep(2);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", form, "display: display;");
        sleep(2);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", form, "border: 2px solid red;");
        sleep(2);
        js.executeScript("javascript:alert('Selenium använder javascript')");
        sleep(2);
    }
    
    public void set(String name, String value) {
        try {
            
        driver.findElement(By.name(name)).clear();
        driver.findElement(By.name(name)).click();
        driver.findElement(By.name(name)).sendKeys(value);
//        driver.findElement(By.name(name)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void click(int index) {
        By by = By.cssSelector("input[type='radio']");
        driver.findElements(by).get(index).click();
    }
    
    public void submit() {
        driver.findElement(By.cssSelector("input[type='submit']")).submit();
    }
    
    public String getMessage() {
        return driver.findElement(By.cssSelector("td[class='message']")).getText();
    }
    
    public void sleep(long l) {
        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }
}