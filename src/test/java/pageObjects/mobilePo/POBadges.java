package pageObjects.mobilePo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.webTests.BaseTestWeb;
import utilities.elementsUtilities.MobileUtilities;
import utilities.elementsUtilities.WebUtilities;

public class POBadges extends BaseMobilePageObjects{

    MobileUtilities utils;
    WebUtilities utilsWeb;
    AppiumDriver driver;


    public POBadges(AppiumDriver driver) {
        super(driver);
        utils = new MobileUtilities(driver);
    }

    public POBadges(WebDriver driver) {
        super(driver);
        utilsWeb = new WebUtilities(driver);
    }

    AppiumBy aboutLinkMenu = (AppiumBy) AppiumBy.accessibilityId("About");
    By navMenuBtn = By.cssSelector(".nav .o-reset-button");


    public void clickNavMenuBtn(){
        utilsWeb.waitForElement(navMenuBtn).click();
    }

    public void clickAboutLinkMenu(){
        utils.waitForElement(aboutLinkMenu).click();
    }
}
