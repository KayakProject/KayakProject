package pageObjects.webPo.footerPo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.elementsUtilities.WebUtilities;

public class POFooterContact {

    WebDriver driver;
    WebUtilities utils;

    public POFooterContact(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }


    //All
    By helpLink = By.linkText("Help/FAQ");

    public void clickHelpLink() throws InterruptedException {
        Thread.sleep(2000);
        utils.waitForElement(helpLink).click();
    }



    //****************************************** FAQ Scenario *************************************************
    By inputFAQ = By.cssSelector(".k_my-input");
    By carFAQTxt = By.cssSelector(".NmSR-title");

    public void sendKeysSearchBoxFAQ(String keyword){
        utils.waitForElement(inputFAQ).sendKeys(keyword);
    }

    public String getTxtCarRentalFAQ(){
        return driver.findElements(carFAQTxt).get(1).getText();
    }



    //****************************************** Booking Receipt Scenario *************************************************
    By bookingLink = By.linkText("Find My Bookings");

    //Confirmation check
    By bookingInput = By.cssSelector(".k_my-input");
    By btnFindBookingReference = By.cssSelector(".c08De-form .Iqt3");
    By txtErrorEmptyInput = By.cssSelector(".cAWq-message");
    By noBookingErrorMsg = By.cssSelector(".J9a2-primary-message");
    By bookingTypeBtn = By.cssSelector(".M8yV");


    public void clickBookingLink(){
        utils.waitForElement(bookingLink).click();
    }

    public void clickFindBookingBtn() {
        utils.waitForElement(btnFindBookingReference).click();
    }

    public void sendKeysReferenceNumber(String referenceNb){
        driver.findElements(bookingInput).get(0).sendKeys(referenceNb);
    }

    public String getTxtErrorEmptyInput() throws InterruptedException {
        Thread.sleep(1000);
        return utils.waitForElement(txtErrorEmptyInput).getText();
    }

    public void sendKeysTravellerName(String name){
        driver.findElements(bookingInput).get(1).sendKeys(name);
    }

    public String getTxtNoBooking() throws InterruptedException {
        Thread.sleep(2000);
        return utils.waitForElement(noBookingErrorMsg).getText();
    }


    //Credit card
    By inputCreditCard = By.cssSelector(".dDDp-last-four .k_my-input");
    By inputPhoneTraveller = By.cssSelector(".dDDp-phone-number .k_my-input");
    By btnFindBookingsCredit = By.cssSelector(".dDDp .Iqt3");
    By noBookingErrorMsgCredit = By.cssSelector(".dDDp .J9a2-primary-message");

    public void clickCreditCardBooking(){
        driver.findElements(bookingTypeBtn).get(1).click();
    }

    public void sendKeysCreditCardInput(String creditCard){
        utils.waitForElement(inputCreditCard).sendKeys(creditCard);
    }

    public void clickFindBookingCreditBtn(){
        utils.waitForElement(btnFindBookingsCredit).click();
    }

    public void clickTravellerPhoneInput(){
        utils.waitForElement(inputPhoneTraveller).click();
    }

    public String getTxtErrorCreditCard(){
        return utils.waitForElement(txtErrorEmptyInput).getText();
    }

    public void sendKeysTravellerPhone(String phone){
        utils.waitForElement(inputPhoneTraveller).sendKeys(phone);
    }

    public String getTxtInvalidCredit() throws InterruptedException {
        Thread.sleep(5000);
        return driver.findElement(noBookingErrorMsgCredit).getText();
    }


    //Email
    By emailInputBooking = By.cssSelector(".vMb0-email .k_my-input");
    By bookingMailBtn = By.cssSelector(".vMb0-form .Iqt3");
    By errorMsgInvalidEmailBooking = By.cssSelector(".vMb0-error-primary-message");
    By msgValidEmailBooking = By.cssSelector(".CEjJ-title");
    By okBtnAlertBox = By.cssSelector(".CEjJ-button-row .Iqt3");

    public void clickEmailBooking(){
        driver.findElements(bookingTypeBtn).get(2).click();
    }

    public void sendKeysEmailInput(String email){
        utils.waitForElement(emailInputBooking).sendKeys(email);
    }

    public void clickBtnBookingMail(){
        utils.waitForElement(bookingMailBtn).click();
    }

    public String getTxtMsgValidEmailBooking() throws InterruptedException {
        Thread.sleep(5000);
        return utils.waitForElement(msgValidEmailBooking).getText();
    }

    public void clickCloseBtnAlert(){
        utils.waitForElement(okBtnAlertBox).click();
    }

    public String getTxtMsgInvalidEmailBooking() throws InterruptedException {
        Thread.sleep(5000);
        return utils.waitForElement(errorMsgInvalidEmailBooking).getText();
    }




    //****************************************** Price Inaccuracy Feedback Scenario *************************************************
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


    public void clickFeedbackLink(){
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


    //****************************************** Booking Receipt Scenario *************************************************
    By linkAffiliates = By.linkText("Affiliates");
    By btnJoinNow = By.cssSelector(".joinbanner .button_link");
    By accountType = By.cssSelector(".options-item");
    By btnContinue = By.xpath("//button[contains(text(),'Continue')]");
    By inputEmailRegistration = By.name("account-email");
    By errorMsgRegistration = By.cssSelector(".fieldgroup_message");

    public void clickLinkAffiliates() throws InterruptedException {
        Thread.sleep(2000);
        utils.waitForElement(linkAffiliates).click();
    }

    public void clickBtnJoinNow(){
        driver.findElements(btnJoinNow).get(0).click();
    }

    public void clickPersonalAccount(){
        driver.findElements(accountType).get(0).click();
    }

    public void clickContinueBtn(){
        utils.waitForElement(btnContinue).click();
    }

    public void sendKeysRegistrationInputEmail(String email){
        utils.waitForElement(inputEmailRegistration).sendKeys(email);
    }

    public String getTxtErrorMsgRegistration(int errorMsg){
        return driver.findElements(errorMsgRegistration).get(errorMsg).getText();
    }

}
