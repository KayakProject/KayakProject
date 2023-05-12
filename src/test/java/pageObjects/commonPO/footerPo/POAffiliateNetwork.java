package pageObjects.commonPO.footerPo;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    By btnJoinNow = By.cssSelector(".joinbanner_intro .button_link");
    By accountType = By.cssSelector(".options-item");
    By btnContinue = By.xpath("//button[contains(text(),'Continue')]");
    By inputEmailRegistration = By.name("account-email");
    By errorMsgRegistration = By.cssSelector(".fieldgroup_message");
    By inputPasswordRegistration = By.id("account-password");
    By errorMsgRegistrationExtra = By.xpath("//span[contains(text(),'extra')]");
    By btnTermsOfUse = By.id("account-terms");
    By titleContactInfo = By.cssSelector(".page-title  h1");
    By inputContactEmail = By.cssSelector(".fieldgroup_message");
    By firstNameInput = By.id("contact-details-first-name");
    By middleNameInput = By.id("contact-middle-name");
    By lastNameInput = By.id("contact-details-last-name");
    By governmentButton = By.cssSelector(".options-item");
    By errorMsg = By.cssSelector(".fieldgroup_message");
    By websiteURLInput = By.id("website-url");
    By sourceSEOCheck = By.id("traffic-source-1");
    By revenueSelect = By.cssSelector(".dropdowngroup_select");
    By revenueValue = By.xpath("//div[contains(text(),' USD $0 - $100 ')]");
    By businessOtherCheck = By.id("business-description-9");
    By btnSubmit = By.xpath("//button[contains(text(),'Submit')]");

    public void clickLinkAffiliates() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,document.body.scrollHeight)");
        utils.waitForElement(linkAffiliates).click();
    }

    public void clickBtnJoinNow() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(btnJoinNow).click();
    }

    public void clickPersonalAccount(){
        driver.findElements(accountType).get(0).click();
    }

    public void clickContinueBtn() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,300)");
        js.executeScript("window.scroll(0,document.body.scrollHeight)");
        Thread.sleep(4000);
        utils.waitForElement(btnContinue).click();
    }

    public void sendKeysRegistrationInputEmail(String email){
        utils.waitForElement(inputEmailRegistration).sendKeys(email);
    }

    public void clearRegistrationInputEmail(){
        WebElement elem = utils.waitForElement(inputEmailRegistration);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
    }

    public String getTxtErrorMsgRegistration(int errorMsg){
        return driver.findElements(errorMsgRegistration).get(errorMsg).getText();
    }

    public void sendKeysRegistrationPassword(String pswd){
        utils.waitForElement(inputPasswordRegistration).sendKeys(pswd);
    }

    public void clearRegistrationPassword(){
        WebElement elem = utils.waitForElement(inputPasswordRegistration);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
    }

    public String getTxtErrorPswdCharacters() throws InterruptedException {
        Thread.sleep(2000);
        return utils.waitForElement(errorMsgRegistrationExtra).getText();
    }

    public void selectCountryRegistration() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,200)");

        driver.findElement(By.cssSelector(".dropdowngroup_select")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[contains(text(),' Afghanistan ')]")).click();
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
        System.out.println(utils.waitForElement(inputContactEmail).getAttribute("data-v-170d3e84"));
        return utils.waitForElement(inputContactEmail).getAttribute("data-v-170d3e84");
    }

    public void sendKeysFirstName(String firstName){
        utils.waitForElement(firstNameInput).sendKeys(firstName);
    }

    public String getTxtError(){
        return utils.waitForElement(errorMsg).getText();
    }

    public void clearFirstName(){
        WebElement elem = utils.waitForElement(firstNameInput);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
    }

    public void sendKeysMiddleName(String middleName){
        utils.waitForElement(middleNameInput).sendKeys(middleName);
    }

    public void sendKeysLastName(String lastName){
        utils.waitForElement(lastNameInput).sendKeys(lastName);
    }

    public void clearLastName() throws InterruptedException {
        WebElement elem = utils.waitForElement(lastNameInput);
        new Actions(driver).click(elem).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys("").perform();
        Thread.sleep(4000);
    }

    public void clickNoGovernment(){
        driver.findElements(governmentButton).get(1).click();
    }

    public void sendKeysWebsite(String url){
        utils.waitForElement(websiteURLInput).sendKeys(url);
    }

    public void checkSourceSEO(){
        utils.waitForElement(sourceSEOCheck).click();
    }

    public void selectRevenue(){
        utils.waitForElement(revenueSelect).click();
        utils.waitForElement(revenueValue).click();
    }

    public void checkBusinessOther(){
        utils.waitForElement(businessOtherCheck).click();
    }

    public void clickSubmitBtn(){
        utils.waitForElement(btnSubmit);
    }


}
