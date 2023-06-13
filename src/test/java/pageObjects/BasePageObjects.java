package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;
import utilities.elementsUtilities.WebUtilities;

public class BasePageObjects {

    //This page includes the common locators for the footer tests

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


    //PO Footer
    By helpLink = By.xpath("//a[contains(text(),'Help/FAQ')]");
    public void clickHelpLink() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(4000);
        js.executeScript("window.scroll(0,document.body.scrollHeight)");
        Thread.sleep(4000);
        utils.waitForElement(helpLink).click();
    }

    By txtTitleHelpPage = By.cssSelector(".MaDN-faq-wrapper h2");
    public String getTxtHelpPage() {
        return utils.waitForElement(txtTitleHelpPage).getText();
    }
}

