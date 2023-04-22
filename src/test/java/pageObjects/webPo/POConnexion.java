package pageObjects.webPo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.time.Duration;

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
    By continueWithEmailBtn = By.cssSelector(".continueWithEmail .Iqt3-button-content");


    //Create methods to interact with the element bellow this section. Add "po" before the method's name to simplify debug in case of errors
    public void poClickSignInBtn(){
        utils.waitForElement(driver.findElement(signInBtn)).click();
    }

    public String poGetTextContinueWithEmail() throws InterruptedException {
        Thread.sleep(5000);
        return utils.waitForElement(driver.findElement(continueWithEmailBtn)).getText();
    }
}
