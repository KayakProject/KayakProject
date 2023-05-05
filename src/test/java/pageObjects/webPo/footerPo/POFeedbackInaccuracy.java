package pageObjects.webPo.footerPo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.elementsUtilities.WebUtilities;

public class POFeedbackInaccuracy {

    WebDriver driver;
    WebUtilities utils;

    public POFeedbackInaccuracy(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }

    By feedbackLink = By.linkText("Give Feedback");
    By inputMailFeedBack = By.cssSelector(".TL8C .k_my-input");
    By msgWrongEmailFeedback = By.cssSelector(".cAWq-message");
    By topicFeedbackSelect = By.id("topics");
    By btnContactUsBuyAd = By.xpath("//a[@href='/partners-contact']");
    By titlePagePartners = By.xpath("//h2[contains(text(),'Partners')]");
    By radioBtn = By.cssSelector(".H3mQ");
    By inputFeedbackName = By.id("name");
    By btnSendFeedback = By.xpath("//button[@type='submit']");
    By txtSelectedTopic = By.cssSelector(".wIIH-mod-alignment-left");
    By currencyInput = By.xpath("//input[@value='C$ ']");
    By userCurrencyFooter = By.cssSelector(".chXn-trigger-content .chXn-trigger-icon");
    By screenshotInput = By.id("screenshot");


    public void clickFeedbackLink() throws InterruptedException {
        Thread.sleep(4000);
        utils.waitForElement(feedbackLink).click();
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

    public String getTxtSelectedTopic(){
        return utils.waitForElement(txtSelectedTopic).getText();
    }

    public void clickContactBtnUsBuyAd(){
        driver.findElements(btnContactUsBuyAd).get(0).click();
    }

    public String getTxtTitlePartnerPage(){
        return utils.waitForElement(titlePagePartners).getText();
    }

    public void clickRadioBtn(int radioPosition){
        driver.findElements(radioBtn).get(radioPosition).click();
    }

    public boolean isRadioSelected(int radioPosition) throws InterruptedException {
        Thread.sleep(1000);
        return driver.findElements(radioBtn).get(radioPosition).isEnabled();
    }

    public String getValueCurrencyInput(){
        return utils.waitForElement(currencyInput).getAttribute("value");
    }

    public String getTxtValueCurrencyFooter(){
        return utils.waitForElement(userCurrencyFooter).getText();
    }

    public void sendKeysInputNameFeedback(String name){
        utils.waitForElement(inputFeedbackName).sendKeys(name);
    }

    public String getCssBackgroundSendFeedbackBtn(){
        return utils.waitForElement(btnSendFeedback).getCssValue("background");
    }

    //MODIFIER LE PATH DU FICHIER !!!!!
    public void uploadScreenshot(){
        utils.waitForElement(screenshotInput).sendKeys("/Users/charlinelavigne/Desktop/screenshot.png");
    }

    public String getTextFileUploaded(){
        return utils.waitForElement(screenshotInput).getAttribute("value");
    }



}
