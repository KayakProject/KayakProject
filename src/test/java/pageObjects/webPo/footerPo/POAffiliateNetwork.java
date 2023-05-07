package pageObjects.webPo.footerPo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.elementsUtilities.WebUtilities;

import java.time.Duration;

public class POAffiliateNetwork {

    WebDriver driver;
    WebUtilities utils;

    public POAffiliateNetwork(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }

    By linkAffiliates = By.linkText("Affiliates");
    By btnJoinNow = By.cssSelector(".joinheader .joinheader_link-login");
    By accountType = By.cssSelector(".options-item");
    By btnContinue = By.xpath("//button[contains(text(),'Continue')]");
    By inputEmailRegistration = By.name("account-email");
    By errorMsgRegistration = By.cssSelector(".fieldgroup_message");
    By inputPasswordRegistration = By.id("account-password");
    By errorMsgRegistrationExtra = By.xpath("//span[contains(text(),'extra')]");
    By dropdownCountryRegistration = By.cssSelector(".drop-down-selector");
    By countryResidence = By.xpath("//div[contains(text(),' Albania ')]");
    //By countryResidence = By.cssSelector(".item");
    By btnTermsOfUse = By.cssSelector(".formlabel");
    By titleContactInfo = By.cssSelector(".page-title  h1");
    By inputContactEmail = By.id("contact-email");
    By btnAcceptCookie = By.cssSelector(".button-acceptconsent a");

    public void clickLinkAffiliates() throws InterruptedException {
        Thread.sleep(2000);
        utils.waitForElement(linkAffiliates).click();
    }

    public void clickBtnJoinNow() throws InterruptedException {
//        try {
//            driver.switchTo().alert();
//            driver.findElement(btnAcceptCookie).click();
//        } catch (UnhandledAlertException f) {
//            try {
//                Alert alert = driver.switchTo().alert();
//                String alertText = alert.getText();
//                System.out.println("Alert data: " + alertText);
//                alert.accept();
//            } catch (NoAlertPresentException e) {
//                e.printStackTrace();
//            }
//        }
//        Thread.sleep(4000);
//        driver.switchTo().alert();
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

    public void sendKeysRegistrationPassword(String pswd){
        utils.waitForElement(inputPasswordRegistration).sendKeys(pswd);
    }

    public String getTxtErrorPswdCharacters() throws InterruptedException {
        Thread.sleep(2000);
        return utils.waitForElement(errorMsgRegistrationExtra).getText();
    }

    public void selectCountryRegistration() throws InterruptedException {


        driver.findElement(By.cssSelector("dropdowngroup_select")).click();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // wait.until(ExpectedConditions.invisibilityOf())
        //WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(dropdownCountryRegistration));
        //element1.click();
//        utils.waitForElement(dropdownCountryRegistration).click();
//        Thread.sleep(5000);

//        WebElement option=driver.findElement(countryResidence);
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].scrollIntoView();", option);
//        option.click();

//        WebElement element2 = driver.findElement(countryResidence);
//        //WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(countryResidence));
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].click();", element2);

        //Actions keyDown = new Actions(driver);
        //keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
    }

    public void clickTermsUse(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnTermsOfUse));
        element.click();
        //driver.findElements(btnTermsOfUse).get(0).click();
    }

    public String getTxtContactInfo(){
        return utils.waitForElement(titleContactInfo).getText();
    }

    public String getTxtContactEmail(){
        return  utils.waitForElement(inputContactEmail).getText();
    }

}
