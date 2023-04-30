package pageObjects.webPo.footerPo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.elementsUtilities.WebUtilities;

public class POFooterCompany {

    WebDriver driver;
    WebUtilities utils;

    public POFooterCompany(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }


    By careerLink = By.linkText("Careers");
    By viewPositionBtn = By.cssSelector(".YqS5 .Iqt3-button-content");
    By careerTitle = By.cssSelector(".YqS5-cta-title");
    By positionsSelect = By.cssSelector(".wIIH-fake-select");
    By allPositionsTxt = By.xpath("//h2[contains(text(),'Open positions')]");
    By noPositionsTxt = By.xpath("//p[contains(text(),'Oh No!')]");
    By arrowLeftCarousel = By.xpath("//div[@aria-label='Back']");
    By arrowRightCarousel = By.xpath("//div[@aria-label='Forward']");
    By carouselWellness = By.cssSelector(".QC6J-card-wellness .QC6J-card-content-title");
    By carouselFlexible = By.cssSelector(".QC6J-card-flexible .QC6J-card-content-title");
    By frameVideo = By.xpath("//iframe[@src='https://www.kayak.com/video/Inside_KAYAK_720p.mp4']");
    By sourceVideo = By.tagName("source");
    By mobileLink = By.linkText("Mobile");
    By phoneSelect = By.tagName("select");
    By phoneNumberInput = By.id("phone-number");
    By btnSendLinkPhone = By.cssSelector(".c0y_Q-form-input .Button-No-Standard-Style");
    By txtBtnSendLinkPhone = By.cssSelector(".Iqt3-button-content");
    By appDownloadBtn = By.cssSelector(".c8suN-download-button-inside-container");
    By carRentalsLink = By.cssSelector(".HKWm");

    public void clickCareerLink() throws InterruptedException {
        Thread.sleep(1000);
        utils.waitForElement(careerLink).click();
    }

    public String getTextCareerTitle() {
        utils.scrollToElementWeb(driver, utils.waitForElement(viewPositionBtn));
        return utils.waitForElement(careerTitle).getText();
    }

    public void clickViewPositionBtn(){
        utils.waitForElement(viewPositionBtn).click();
    }

    public void selectLocation(String locationName){
        WebElement element = driver.findElements(positionsSelect).get(0);
        utils.selectElementWeb(element, locationName);
    }

    public String getTextOpenPositions(){
        return utils.waitForElement(allPositionsTxt).getText();
    }

    public void selectDepartment(String departmentName){
        WebElement element = driver.findElements(positionsSelect).get(1);
        utils.selectElementWeb(element, departmentName);
    }

    public String getTextNoPositions() throws InterruptedException {
        Thread.sleep(1000);
        return utils.waitForElement(noPositionsTxt).getText();
    }

    public void clickRightArrowCarousel(){
        utils.waitForElement(arrowRightCarousel).click();
    }

    public boolean isDisplayedflexibleCarousel() {
        return utils.waitForElement(carouselFlexible).isDisplayed();
    }

    public boolean isDisplayedWellnessCarousel(){
        return utils.waitForElement(carouselWellness).isDisplayed();
    }

    public void clickLeftArrowCarousel(){
        utils.waitForElement(arrowLeftCarousel).click();
    }

    public String getVideoType(){
        driver.switchTo().frame(utils.waitForElement(frameVideo));
        return driver.findElement(sourceVideo).getAttribute("type");
    }

    public void clickMobileLink() throws InterruptedException {
        Thread.sleep(2000);
        utils.waitForElement(mobileLink).click();
    }

    public void selectPhoneCode(String phoneCode) {
        WebElement element = driver.findElement(phoneSelect);
        utils.selectElementWeb(element, phoneCode);
    }

    public void sendKeysPhoneNumber(String phoneNumber){
        utils.waitForElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void clickSendAppPhoneBtn(){
        utils.waitForElement(btnSendLinkPhone).click();
    }

    public boolean isPhoneLinkSent(){
        return !(utils.waitForElement(txtBtnSendLinkPhone).getText().equals("Send app link"));
    }

    public void clickDownloadAppIphone(){
        driver.findElements(appDownloadBtn).get(0).click();
    }

    public void clickDownloadAppAndroid(){
        driver.findElements(appDownloadBtn).get(1).click();
    }

    public String getTitlePage(){
        return driver.getTitle();
    }

    public String getTextLinkFooter(){
        return driver.findElements(carRentalsLink).get(0).getText();
    }
}
