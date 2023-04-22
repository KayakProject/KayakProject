package utilities.elementsUtilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileUtilities extends CommonUtilities {

    //****************************************** don't touch this section *************************************************
    AppiumDriver appiumDriver;
    int WAIT = 10;

    public MobileUtilities(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }


    public WebElement waitForElement(WebElement element) {
        WebElement elem = element;
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(WAIT));
            elem = wait.until(ExpectedConditions.visibilityOf(element));

        } catch (NoSuchElementException e1) {
            System.out.println("The locator of the element is wrong or try with a Thread.sleep() before locating the element");

        } catch (StaleElementReferenceException e2) {
            for (int i = 0; i <= 2; i++) {
                waitForElement(element);
                break;
            }
        }
        return elem;
    }
}
//****************************************** don't touch this section *************************************************

