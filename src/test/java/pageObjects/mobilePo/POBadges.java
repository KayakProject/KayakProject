package pageObjects.mobilePo;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import utilities.elementsUtilities.MobileUtilities;

import java.util.Set;

public class POBadges {

    MobileUtilities utils;
    AppiumDriver driver;


    public POBadges(AppiumDriver driver) {
        this.driver = driver;
        utils = new MobileUtilities(driver);
    }

    By navMenuBtn = By.xpath("//button[@aria-label='Menu']");
    By helpLinkMenu = By.xpath("//a[@data-test-menu-nav-id='FAQ']");
    By btnFindBooking = By.linkText("Find My Bookings");

    By bookingInput = By.cssSelector(".k_my-input");
    By btnFindBookingReference = By.cssSelector(".c08De-form .Iqt3");
    By txtError = By.cssSelector(".cAWq-message");
    By noBookingErrorMsg = By.cssSelector(".J9a2-primary-message");
    By bookingTypeBtn = By.cssSelector(".M8yV");


    public void clickNavMenuBtn() throws InterruptedException {
       // utils.waitForElement(navMenuBtn).click();
        Thread.sleep(5000);
        Set<String> contextHandles = ((AndroidDriver) driver).getContextHandles();
        for(String contextHandle : contextHandles){
            System.out.println(contextHandle);
        }
        ((AndroidDriver) driver).context("WEBVIEW_chrome");
        driver.findElement(navMenuBtn).click();
    }

    public void clickHelpLinkMenu(){
        //((AndroidDriver) driver).context("NATIVE_APP");
        driver.findElement(helpLinkMenu).click();
    }

    public void clickFindBookingsBtn(){
        driver.findElement(btnFindBooking).click();
    }

    public void clickFindBookingBtn() {
        driver.findElement(btnFindBookingReference).click();
    }

    public void sendKeysReferenceNumber(String referenceNb){
        driver.findElements(bookingInput).get(0).sendKeys(referenceNb);
    }

    public String getTxtError() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElement(txtError).getText();
    }


    public void clearReferenceNumber(){
        WebElement elem = driver.findElements(bookingInput).get(0);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
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
        return driver.findElement(noBookingErrorMsg).getText();
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
        driver.findElement(inputCreditCard).sendKeys(creditCard);
    }

    public void clearCreditCard(){
        WebElement elem = driver.findElement(inputCreditCard);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
    }

    public void clickFindBookingCreditBtn(){
        driver.findElement(btnFindBookingsCredit).click();
    }

    public void clickTravellerPhoneInput(){
        driver.findElement(inputPhoneTraveller).click();
    }

    public void sendKeysTravellerPhone(String phone){
        driver.findElement(inputPhoneTraveller).sendKeys(phone);
    }

    public void clearTravellerPhone(){
        WebElement elem = driver.findElement(inputPhoneTraveller);
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
        driver.findElement(emailInputBooking).sendKeys(email);
    }

    public void clearEmailInput(){
        WebElement elem = driver.findElement(emailInputBooking);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
    }

    public void clickBtnBookingMail(){
        driver.findElement(bookingMailBtn).click();
    }

    public String getTxtMsgValidEmailBooking() throws InterruptedException {
        Thread.sleep(5000);
        return driver.findElement(msgValidEmailBooking).getText();
    }

    public void clickCloseBtnAlert(){
        driver.findElement(okBtnAlertBox).click();
    }

    public String getTxtMsgInvalidEmailBooking() throws InterruptedException {
        Thread.sleep(5000);
        return driver.findElement(errorMsgInvalidEmailBooking).getText();
    }

    By aboutLinkMenu = By.xpath("//a[@data-test-menu-nav-id='FAQ']");
    By badgesLinkFooter = By.linkText("Badges & Certificates");

    public void clickAboutLinkMenu(){
        driver.findElement(aboutLinkMenu).click();
    }

    public void scrollDownPage() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,document.body.scrollHeight)");
        Thread.sleep(3000);
//        boolean canScrollMore;
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        do{
//            canScrollMore = (Boolean) js.executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
//        }while(canScrollMore);
//
//
//
//        String textLocator = "Badges & Certificates";
//        driver.findElement(AppiumBy.androidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true))" +
//                        ".scrollIntoView(new UiSelector().textContains(\""+ textLocator +"\"))"));
    }

    public void clickBadgesLinkFooter(){
        driver.findElement(badgesLinkFooter).click();
    }

}