package utilities.elementsUtilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileUtilities {

    AppiumDriver appiumDriver;
    int WAIT = 10;


    public MobileUtilities(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }


    public WebElement waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(WAIT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
