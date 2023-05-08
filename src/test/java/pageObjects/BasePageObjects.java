package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utilities.elementsUtilities.WebUtilities;

public class BasePageObjects {

    WebDriver driver;
    WebUtilities utils;

    public BasePageObjects(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }

    //Locators for sign in
    By signInBtn = By.cssSelector(".MvE2-nav-item-account a");
    By continueWithEmailTxt = By.cssSelector(".continueWithEmail .Iqt3-button-content");
    By continueWithEmailBtn = By.cssSelector(".dDYU-content .Iqt3");
    By inputUsernameConnexion = By.cssSelector(".dDYU-content .email-input");
    By continueUsernameConnexionBtn = By.cssSelector(".dDYU-content .Iqt3-button-content");


    //PO Footer
    By helpLink = By.linkText("Help/FAQ");
    public void clickHelpLink() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,document.body.scrollHeight)");
        driver.findElement(helpLink).click();
    }
}

