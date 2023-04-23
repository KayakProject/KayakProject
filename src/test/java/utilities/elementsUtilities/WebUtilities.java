package utilities.elementsUtilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WebUtilities extends CommonUtilities {

    //****************************************** don't touch this section *************************************************
    WebDriver driver;
    int WAIT = 20;

    public WebUtilities(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


    //This method waits for the element to load and handles NoSuchElement Exception and StaleElementReference Exception
    public WebElement waitForElement(By locator){
        WebElement element = null;
        try {
            element = driver.findElement(locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
            element = wait.until(ExpectedConditions.visibilityOf(element));
            return element;

        } catch (NoSuchElementException e1) {
            System.out.println("The locator of the element is wrong or try with a Thread.sleep() before locating the element");

        } catch (StaleElementReferenceException e2) {
            for (int i = 0; i <= 2; i++) {
                waitForElement(locator);
                break;
            }
        }
        Assert.assertTrue(element != null, "One of your method called in the Test Class has an error, check the line");
        return element;
    }
    //****************************************** don't touch this section *************************************************
}
