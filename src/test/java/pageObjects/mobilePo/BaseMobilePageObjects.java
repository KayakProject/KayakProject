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

    AppiumBy googleSearchBox = (AppiumBy) AppiumBy.id("com.android.chrome:id/search_box_text");
    AppiumBy urlBar = (AppiumBy) AppiumBy.id("com.android.chrome:id/url_bar");
    AppiumBy btnAcceptGoogle = (AppiumBy) AppiumBy.id("com.android.chrome:id/terms_accept");
    AppiumBy btnRefuseSync = (AppiumBy) AppiumBy.id("com.android.chrome:id/negative_button");
    AppiumBy websiteLink = (AppiumBy) AppiumBy.id("com.android.chrome:id/line_1");
    AppiumBy btnNotif = (AppiumBy) AppiumBy.id("com.android.chrome:id/negative_button");
    By navMenuBtn = By.xpath("//button[@aria-label='Menu']");
    By aboutLinkMenu = By.xpath("//a[@data-test-menu-nav-id='ABOUT']");
    By helpLinkMenu = By.xpath("//a[@data-test-menu-nav-id='FAQ']");
    By txtTitleHelpPage = By.cssSelector(".MaDN-faq-wrapper h2");
    AppiumBy btnCancelBtnBadges = (AppiumBy) AppiumBy.id("com.android.chrome:id/negative_button");

    public void clickGoogleSearchBox() throws InterruptedException {
        Thread.sleep(4000);
        utils.waitForElement(btnAcceptGoogle).click();
        Thread.sleep(2000);
        utils.waitForElement(btnRefuseSync).click();
        Thread.sleep(4000);
        utils.waitForElement(btnNotif).click();
        Thread.sleep(5000);
    }

    public void searchKayakUrl() throws InterruptedException {
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
        }
        ((AndroidDriver) driver).context("WEBVIEW_chrome");
    }

    public void clickNavMenu() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(navMenuBtn).click();
    }

    public void clickAboutLink() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(aboutLinkMenu).click();
    }

    public void clickHelpLink(){
        driver.findElement(helpLinkMenu).click();
    }

    public String getTxtHelpPage() {
        return utils.waitForElement(txtTitleHelpPage).getText();
    }

    public void clickCancelDownload(){
        utils.waitForElement(btnCancelBtnBadges).click();
    }
}
