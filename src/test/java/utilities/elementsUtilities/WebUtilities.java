package utilities.elementsUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebUtilities extends CommonUtilities {

    //****************************************** don't touch this section *************************************************
    WebDriver driver;
    int WAIT = 10;

    public WebUtilities(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


    public WebElement waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        return wait.until(ExpectedConditions.visibilityOf(element));

    }
    //****************************************** don't touch this section *************************************************
}
