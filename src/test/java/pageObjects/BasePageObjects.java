package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.elementsUtilities.WebUtilities;

public class BasePageObjects {

    //****************************************** don't touch this section *************************************************
    WebDriver driver;
    WebUtilities utils;

    public BasePageObjects(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }
    //****************************************** don't touch this section *************************************************

    //Locators for sign in
    By signInBtn = By.cssSelector(".MvE2-nav-item-account a");
    By continueWithEmailTxt = By.cssSelector(".continueWithEmail .Iqt3-button-content");
    By continueWithEmailBtn = By.cssSelector(".dDYU-content .Iqt3");
    By inputUsernameConnexion = By.cssSelector(".dDYU-content .email-input");
    By continueUsernameConnexionBtn = By.cssSelector(".dDYU-content .Iqt3-button-content");
}
