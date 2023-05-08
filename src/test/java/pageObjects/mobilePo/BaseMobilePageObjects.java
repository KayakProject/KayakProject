package pageObjects.mobilePo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utilities.elementsUtilities.WebUtilities;

import java.util.Set;

public class BaseMobilePageObjects {
    AppiumDriver driver;
    WebUtilities utils;

    public BaseMobilePageObjects(AppiumDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }

    AppiumBy googleSearchBox = (AppiumBy) AppiumBy.id("com.android.chrome:id/search_box");
    AppiumBy urlBar = (AppiumBy) AppiumBy.id("com.android.chrome:id/url_bar");
    AppiumBy btnAcceptGoogle = (AppiumBy) AppiumBy.id("com.android.chrome:id/terms_accept");
    AppiumBy btnRefuseSync = (AppiumBy) AppiumBy.id("com.android.chrome:id/negative_button");
    AppiumBy websiteLink = (AppiumBy) AppiumBy.id("com.android.chrome:id/line_1");

    public void clickGoogleSearchBox() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(btnAcceptGoogle).click();
        utils.waitForElement(btnRefuseSync).click();
        Thread.sleep(5000);
        utils.waitForElement(googleSearchBox).click();
        Thread.sleep(3000);
        utils.waitForElement(urlBar).sendKeys("https://www.ca.kayak.com/");
        utils.waitForElement(websiteLink).click();
        Thread.sleep(5000);
    }

    public void switchContextHandle() throws InterruptedException {
        Thread.sleep(5000);
        Set<String> contextHandles = ((AndroidDriver) driver).getContextHandles();
        for(String contextHandle : contextHandles){
            System.out.println(contextHandle);
        }
        ((AndroidDriver) driver).context("WEBVIEW_chrome");
    }

    By navMenuBtn = By.xpath("//button[@aria-label='Menu']");
    public void clickNavMenu(){
        driver.findElement(navMenuBtn).click();
    }

    By aboutLinkMenu = By.xpath("//a[@data-test-menu-nav-id='ABOUT']");
    public void clickAboutLink(){
        driver.findElement(aboutLinkMenu).click();
    }

    By helpLinkMenu = By.xpath("//a[@data-test-menu-nav-id='FAQ']");
    public void clickHelpLink(){
        driver.findElement(helpLinkMenu).click();
    }


}
