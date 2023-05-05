package pageObjects.mobilePo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import utilities.elementsUtilities.WebUtilities;

public class BaseMobilePageObjects {
    WebDriver driver;
    WebUtilities utils;

    public BaseMobilePageObjects(AppiumDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }

    AppiumBy googleSearchBox = (AppiumBy) AppiumBy.id("com.android.chrome:id/search_box");
    AppiumBy urlBar = (AppiumBy) AppiumBy.id("com.android.chrome:id/url_bar");
    AppiumBy btnAcceptGoogle = (AppiumBy) AppiumBy.id("com.android.chrome:id/terms_accept");
    AppiumBy btnRefuseSync = (AppiumBy) AppiumBy.id("com.android.chrome:id/negative_button");
    AppiumBy kayakSearchResult = (AppiumBy) AppiumBy.className("android.widget.TextView");
    //AppiumBy btnAppChooseChrome = (AppiumBy) AppiumBy.xpath("//android.widget.LinearLayout[2]");
    AppiumBy websiteLink = (AppiumBy) AppiumBy.id("com.android.chrome:id/line_1");

    public void clickGoogleSearchBox() throws InterruptedException {
        Thread.sleep(2000);
        utils.waitForElement(btnAcceptGoogle).click();
        utils.waitForElement(btnRefuseSync).click();
        Thread.sleep(5000);
        utils.waitForElement(googleSearchBox).click();
        utils.waitForElement(urlBar).sendKeys("https://www.ca.kayak.com/");
        utils.waitForElement(websiteLink).click();
        Thread.sleep(10000);
        //utils.waitForElement(kayakSearchResult).click();
        //Thread.sleep(5000);

    }

    public String getUrl(){
        return utils.waitForElement(urlBar).getText();
    }
}
