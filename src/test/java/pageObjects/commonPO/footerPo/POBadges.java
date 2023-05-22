package pageObjects.commonPO.footerPo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.elementsUtilities.WebUtilities;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class POBadges {

    WebDriver driver;
    WebUtilities utils;

    public POBadges(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }


    By badgesLinkFooter = By.linkText("Badges & Certificates");
    By txtBadgesTitlePage = By.cssSelector(".QYIX h1");
    By inputSearchTopRated = By.xpath("//div[@aria-haspopup='listbox']");
    By btnSearch = By.xpath("//div[@class='S5Hx']//button[@role='button']");
    By titleElement = By.cssSelector(".TZPy-badge-results h3");
    By optionCustomization = By.cssSelector(".wIIH-fake-select");
    By btnDownload = By.cssSelector(".ZtQ8-download-button button");
    By btnBadges = By.cssSelector(".ZtQ8-button-wrapper button");
    By txtEmbedCode = By.cssSelector(".ZtQ8-embed-wrapper input");
    By btnCertificate = By.xpath("//button[contains(text(),'Certificate')]");
    By btnDownloadCertificate = By.cssSelector(".ZtQ8-button-wrapper a");
    By btnSocialTemplate = By.xpath("//button[contains(text(),'Social template')]");
    By certificateImg = By.id("certificate");
    By inputHotel = By.cssSelector(".k_my-input");
    By txtInputHotel = By.cssSelector(".lNCO-inner");
    By invalidHotelAwarded = By.xpath("//span[contains(text(),'The Franklin Hotel')]");
    By validHotelAwarded = By.xpath("//span[contains(text(),'Four Seasons Resort Whistler')]");
    By txtMessageNotAwarded = By.cssSelector(".TZPy-badge-results h2");
    By defaultSelectedValue = By.cssSelector(".wIIH-handle span");
    By imgBadge = By.cssSelector(".ZtQ8-image-preview");
    By selectedBtn = By.cssSelector(".ZtQ8-active");
    By textAreaSocialTemplate = By.cssSelector(".ZtQ8-social-template-copy");

    public void clickBadgesLinkFooter() throws InterruptedException {
        Thread.sleep(4000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,document.body.scrollHeight)");
        utils.waitForElement(badgesLinkFooter).click();
        Thread.sleep(2000);
    }

    public String getTxtBadgesTitlePage() throws InterruptedException {
        Thread.sleep(4000);
        return utils.waitForElement(txtBadgesTitlePage).getText();
    }

    public void sendKeysInputSearchTopRated(String name) throws InterruptedException {
        utils.waitForElement(inputSearchTopRated).click();
        Thread.sleep(2000);
        utils.waitForElement(inputHotel).sendKeys(name);
        Thread.sleep(1000);
    }

    public void sendKeysInvalidHotel() {
        driver.findElement(invalidHotelAwarded).click();
    }

    public void sendKeysValidHotel() {
        driver.findElement(validHotelAwarded).click();
    }

    public void clickBtnSearch(){
        driver.findElement(btnSearch).click();
    }

    public String getTxtInputHotel() {
        return utils.waitForElement(txtInputHotel).getText();
    }

    public String getTxtHotelAwardedTitle() throws InterruptedException {
        Thread.sleep(4000);
        return utils.waitForElement(titleElement).getText();
    }

    public String getTxtHotelNotAwarded() throws InterruptedException {
        Thread.sleep(3000);
        return utils.waitForElement(txtMessageNotAwarded).getText();
    }

    public void selectCustomization(int dropdown, String value) throws InterruptedException {
        WebElement element = driver.findElements(optionCustomization).get(dropdown);
        Thread.sleep(3000);
        utils.selectElementWeb(element, value);
    }

    public String getTxtSelectedCustom(int dropdown) {
        return driver.findElements(defaultSelectedValue).get(dropdown).getText();
    }

    public void clickBtnDownload(){
        utils.waitForElement(btnDownload).click();
    }

    public void copyBtnEmbedCode(){
        driver.findElements(btnBadges).get(0).click();
    }

    public String getTxtClipboard() throws IOException, UnsupportedFlavorException {
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        return data;
    }

    public String getTxtEmbedCode(){
        return utils.waitForElement(txtEmbedCode).getAttribute("value");
    }

    public boolean getTxtHeightBadge(String height){
        return utils.waitForElement(imgBadge).getAttribute("height").equals(height);
    }

    public void clickBtnCertificate(){
        utils.waitForElement(btnCertificate).click();
    }

    public String getTxtSelectedBtn(){
        return utils.waitForElement(selectedBtn).getText();
    }

    public String getCssCertificateBackground(){
        return utils.waitForElement(certificateImg).getCssValue("background");
    }

    public void clickDownloadCertificate(){
        utils.waitForElement(btnDownloadCertificate).click();
    }

    public void clickBtnSocialTemplate(){
        utils.waitForElement(btnSocialTemplate).click();
    }

    public void clickBtnDownloadSocial(){
        driver.findElements(btnBadges).get(2).click();
    }

    public void clickBtnCopyTemplate(){
        driver.findElements(btnBadges).get(2).click();
    }

    public boolean isDownloaded(String fileName) {
        return utils.verifyDownloadedFile(fileName);
    }

    public String getTxtSocialTemplate(){
        return utils.waitForElement(textAreaSocialTemplate).getText();
    }
}


