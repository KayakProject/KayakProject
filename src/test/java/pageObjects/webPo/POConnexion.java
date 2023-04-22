package pageObjects.webPo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;


public class POConnexion {

    //****************************************** don't touch this section *************************************************
    WebDriver driver;
    CommonUtilities utils;

    public POConnexion(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }
    //****************************************** don't touch this section *************************************************

    //Create locators bellow this section
    By signInBtn = By.cssSelector(".MvE2-nav-item-account a");
    By continueWithEmailTxt = By.cssSelector(".continueWithEmail .Iqt3-button-content");
    By continueWithEmailBtn = By.cssSelector(".dDYU-content .Iqt3");
    By inputUsernameConnexion = By.cssSelector(".dDYU-content .email-input");
    By continueUsernameConnexionBtn = By.cssSelector(".dDYU-content .Iqt3-button-content");
    By errorUsernameTxt = By.cssSelector(".error-message__text");


    //Create methods to interact with the element bellow this section. Add "po" before the method's name to simplify debug in case of errors
    public void clickSignInBtn(){
        utils.waitForElement(signInBtn).click();
    }

    public String getTextContinueWithEmail() throws InterruptedException {
        Thread.sleep(2000);
        return utils.waitForElement(continueWithEmailTxt).getText();
    }

    public void clickContinueWithEmailBtn(){
        utils.waitForElement(continueWithEmailBtn).click();
    }

    public void sendKeysUsernameMail(String username){
        utils.waitForElement(inputUsernameConnexion).sendKeys(username);
    }

    public void clickUsernameConnexionBtn(){
        utils.waitForElement(continueUsernameConnexionBtn).click();
    }

    public String getTextErrorUsername() throws InterruptedException {
        Thread.sleep(2000);
        return utils.waitForElement(errorUsernameTxt).getText();
    }
}
