package pageObjects.footerPo;

import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;
import utilities.elementsUtilities.WebUtilities;

import java.io.File;

public class POFeedbackInaccuracy{

    WebDriver driver;
    WebUtilities utils;
    SoftAssert softAssert;

    public POFeedbackInaccuracy(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
        softAssert = new SoftAssert();
    }

    By feedbackLink = By.linkText("Give Feedback");
    By txtTitleFeedbackPage = By.cssSelector(".DQ6j-header");
    By inputMailFeedBack = By.cssSelector(".TL8C .k_my-input");
    By msgWrongEmailFeedback = By.cssSelector(".cAWq-message");
    By inputName = By.xpath("//input[@placeholder='Enter your name']");
    By topicFeedbackSelect = By.id("topics");
    By radioBtn = By.cssSelector(".H3mQ");
    By messageTextArea = By.id("message");
    By btnSendFeedback = By.xpath("//button[@type='submit']");
    By txtSelectedTopic = By.cssSelector(".wIIH-mod-alignment-left");
    By currencyInput = By.id("price");
    By userCurrencyFooter = By.cssSelector(".chXn-trigger-content");
    By linkGuarantees = By.linkText("price and availability are not guaranteed");
    By screenshotInput = By.id("screenshot");
    By helpLink = By.xpath("//a[contains(text(),'Help/FAQ')]");



    public void clickFeedbackLink() throws InterruptedException {
        Thread.sleep(4000);
        utils.waitForElement(feedbackLink).click();
    }

    public void clickHelpLink(){
        utils.waitForElement(helpLink).click();
    }

    public String getTxtFeedbackPage(){
        return utils.waitForElement(txtTitleFeedbackPage).getText();
    }

    public void sendKeysInputMailFeedback(String email){
        driver.findElements(inputMailFeedBack).get(0).sendKeys(email);
    }

    public String getTxtWrongEmailFeedback() {
        return utils.waitForElement(msgWrongEmailFeedback).getText();
    }

    public void selectTopicFeedback(String topic){
        WebElement element = driver.findElement(topicFeedbackSelect);
        utils.selectElementWeb(element, topic);
    }

    public String getColorFeedbackBtn(){
        return utils.waitForElement(btnSendFeedback).getCssValue("background");
    }

    public void sendKeysInputName(String name){
        utils.waitForElement(inputName).sendKeys(name);
    }

    public String getTxtSelectedTopic(){
        return utils.waitForElement(txtSelectedTopic).getText();
    }

    public void clickRadioBtn(int radioPosition){
        driver.findElements(radioBtn).get(radioPosition).click();
    }

    public boolean isRadioSelected(int radioPosition) throws InterruptedException {
        Thread.sleep(1000);
        return driver.findElements(radioBtn).get(radioPosition).isEnabled();
    }

    public String getValueCurrencyInput() throws InterruptedException {
        try{
            return utils.waitForElement(currencyInput).getText();
        }
        catch(NullPointerException | NoSuchElementException e){
            //e.printStackTrace();
            return "no input found";
        }
    }

    public void sendKeysCurrency(String amount){
        utils.waitForElement(currencyInput).sendKeys(amount);
    }

    public String getTxtValueCurrencyFooter(){
        return utils.waitForElement(userCurrencyFooter).getText();
    }

    public void sendKeysMessageArea(String msg){
        utils.waitForElement(messageTextArea).sendKeys(msg);
    }

    public void clickLinkGuarantee(){
        utils.waitForElement(linkGuarantees).click();
    }

    public String getTxtTitlePageGuarantees() {
        return driver.getTitle();
    }

    public void uploadScreenshot(String path){
        File file = new File(path);
        utils.waitForElement(screenshotInput).sendKeys(file.getAbsolutePath());
    }

    public String getTextFileUploaded() {
        return utils.waitForElement(screenshotInput).getAttribute("value");
    }

    public void clickFeedbackBtn(){
        utils.waitForElement(btnSendFeedback);
    }
}