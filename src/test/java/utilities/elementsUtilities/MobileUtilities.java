package utilities.elementsUtilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MobileUtilities extends CommonUtilities {

    //****************************************** don't touch this section *************************************************
    AppiumDriver appiumDriver;
    int WAIT = 10;

    public MobileUtilities(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    //This method waits for the element to load and handles NoSuchElement Exception and StaleElementReference Exception
    public WebElement waitForElement(By locator) {
        WebElement element = null;
        try {
            element = appiumDriver.findElement(locator);
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(WAIT));
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


    //This method scrolls until an element having a specific text
    public WebElement scrollToElement(String textLocator){
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().textContains(\""+ textLocator +"\"))"));
    }
}
//****************************************** don't touch this section *************************************************

