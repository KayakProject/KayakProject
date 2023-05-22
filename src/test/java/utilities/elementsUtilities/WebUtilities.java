package utilities.elementsUtilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WebUtilities extends CommonUtilities {

    WebDriver driver;
    int WAIT = 15;

    public WebUtilities(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


    //This method waits for the element to load and handles NoSuchElement Exception and StaleElementReference Exception
    public WebElement waitForElement(By locator){
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
            element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            return element;

        } catch (NoSuchElementException e1) {
            try{
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));
                element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
                return element;

            }catch(NoSuchElementException e2){
                System.out.println("The locator of the element is wrong or try with a Thread.sleep() before locating the element");
            }

        } catch (StaleElementReferenceException e2) {
            for (int i = 0; i <= 2; i++) {
                waitForElement(locator);
                break;
            }
        }
        return null;
    }


    //This method scrolls the page until a web element. It can be used to find elements on a small screen (like a 13inches screen for example)
    public void scrollToElementWeb(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    //This method selects an element from a dropdown list
    public void selectElementWeb(WebElement element, String selectedText){
        Select elementSelected = new Select(element);
        elementSelected.selectByValue(selectedText);
    }
}
