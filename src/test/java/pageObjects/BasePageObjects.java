package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;
import utilities.elementsUtilities.WebUtilities;

public class BasePageObjects {

    WebDriver driver;
    WebUtilities utils;
    AppiumDriver appiumDriver;

    public BasePageObjects(WebDriver driver){
        this.driver = BaseTest.driver;
        utils = new WebUtilities(driver);
    }

    public BasePageObjects(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(4000);
        js.executeScript("window.scroll(0,document.body.scrollHeight)");
        utils.waitForElement(helpLink).click();
    }

    By txtTitleHelpPage = By.cssSelector(".MaDN-faq-wrapper h2");
    public String getTxtHelpPage() {
        return utils.waitForElement(txtTitleHelpPage).getText();
    }
}

