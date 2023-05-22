package pageObjects.commonPO.footerPo;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utilities.elementsUtilities.WebUtilities;

public class POBookingReceipt {

    WebDriver driver;
    WebUtilities utils;

    public POBookingReceipt(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }


    By bookingLink = By.linkText("Find My Bookings");
    By bookingTitle = By.cssSelector(".VGw8-title");

    public void clickBookingLink() throws InterruptedException {
        Thread.sleep(3000);
        utils.waitForElement(bookingLink).click();
    }

    public String getTitleBookingPage(){
        return utils.waitForElement(bookingTitle).getText();
    }

    //Confirmation check
    By bookingInput = By.cssSelector(".k_my-input");
    By btnFindBookingReference = By.cssSelector(".c08De-form .Iqt3");
    By txtError = By.cssSelector(".cAWq-message");
    By noBookingErrorMsg = By.cssSelector(".J9a2-primary-message");
    By bookingTypeBtn = By.cssSelector(".M8yV");


    public void clickFindBookingBtn() {
        utils.waitForElement(btnFindBookingReference).click();
    }

    public void sendKeysReferenceNumber(String referenceNb){
        driver.findElements(bookingInput).get(0).sendKeys(referenceNb);
    }

    public void clearReferenceNumber(){
        WebElement elem = driver.findElements(bookingInput).get(0);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
    }

    public String getTxtError() throws InterruptedException {
        Thread.sleep(2000);
        return utils.waitForElement(txtError).getText();
    }

    public void sendKeysTravellerName(String name){
        driver.findElements(bookingInput).get(1).sendKeys(name);
    }

    public void clearTravellerName(){
        WebElement elem = driver.findElements(bookingInput).get(1);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
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

    public boolean isCreditSectionSelected(){
        return driver.findElements(bookingTypeBtn).get(1).getAttribute("aria-selected").equals("true");
    }

    public void sendKeysCreditCardInput(String creditCard){
        utils.waitForElement(inputCreditCard).sendKeys(creditCard);
    }

    public void clearCreditCard(){
        WebElement elem = utils.waitForElement(inputCreditCard);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
    }

    public void clickFindBookingCreditBtn(){
        utils.waitForElement(btnFindBookingsCredit).click();
    }

    public void clickTravellerPhoneInput(){
        utils.waitForElement(inputPhoneTraveller).click();
    }

    public void sendKeysTravellerPhone(String phone){
        utils.waitForElement(inputPhoneTraveller).sendKeys(phone);
    }

    public void clearTravellerPhone(){
        WebElement elem = utils.waitForElement(inputPhoneTraveller);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
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

    public void clearEmailInput(){
        WebElement elem = utils.waitForElement(emailInputBooking);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
    }

    public void clickBtnBookingMail(){
        utils.waitForElement(bookingMailBtn).click();
    }

    public String getTxtMsgValidEmailBooking() throws InterruptedException {
        try{
            Thread.sleep(5000);
            return utils.waitForElement(msgValidEmailBooking).getText();
        }catch(NullPointerException e){
            return null;
        }
    }

    public void clickCloseBtnAlert(){
        utils.waitForElement(okBtnAlertBox).click();
    }

    public String getTxtMsgInvalidEmailBooking() throws InterruptedException {
        Thread.sleep(5000);
        return utils.waitForElement(errorMsgInvalidEmailBooking).getText();
    }
}
