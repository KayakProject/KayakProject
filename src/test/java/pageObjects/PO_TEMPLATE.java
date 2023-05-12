package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.elementsUtilities.WebUtilities;

public class PO_TEMPLATE {
    WebDriver driver;
    WebUtilities utils;

    public PO_TEMPLATE(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }


    //Always use utils.waitForElement(LOCATOR) except if you need to find multiple elements use driver.findElements(LOCATOR)

    //Examples:
    By errorMsgRegistration = By.cssSelector(".fieldgroup_message");
    By inputEmailRegistration = By.name("account-email");

    public void sendKeysRegistrationInputEmail(String email){
        utils.waitForElement(inputEmailRegistration).sendKeys(email);
    }

    public String getTxtErrorMsgRegistration(int errorMsg){
        return driver.findElements(errorMsgRegistration).get(errorMsg).getText();
    }

}
