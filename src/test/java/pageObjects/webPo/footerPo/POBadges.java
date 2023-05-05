package pageObjects.webPo.footerPo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.elementsUtilities.WebUtilities;

import java.time.Duration;

public class POBadges {

    WebDriver driver;
    WebUtilities utils;

    public POBadges(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }


    By badgesLinkFooter = By.linkText("Badges & Certificates");
    By inputSearchTopRated = By.xpath("//div[@aria-haspopup='listbox']");
    By btnSearch = By.xpath("//div[@class='S5Hx']//button[@role='button']");
    By titleElement = By.cssSelector(".ZtQ8-award h4");
    By optionCustomization = By.cssSelector(".wIIH-fake-select");
    By btnDownload = By.cssSelector(".ZtQ8-download-button button");
    By btnBadges = By.cssSelector(".ZtQ8-button-wrapper button");
    By txtEmbedCode = By.cssSelector(".ZtQ8-embed-wrapper input");
    By btnCertificate = By.xpath("//button[contains(text(),'Certificate')]");
    By btndownloadCertificate = By.cssSelector(".ZtQ8-button-wrapper a");
    By btnSocialTemplate = By.xpath("//button[contains(text(),'Social template')]");
    By certificateImg = By.id("certificate");

    public void clickBadgesLinkFooter() throws InterruptedException {
        Thread.sleep(3000);
        utils.waitForElement(badgesLinkFooter).click();
        Thread.sleep(3000);
    }

    public void sendKeysInputSearchTopRated() throws InterruptedException {
        driver.findElement(inputSearchTopRated).click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".k_my-input"))));
        input.click();

        driver.findElement(By.cssSelector(".k_my-input")).sendKeys("Four Seasons");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[contains(text(),'Four Seasons Resort Whistler')]")).click();
        Thread.sleep(3000);
    }

    public void clickBtnSearch(){
        driver.findElement(btnSearch).click();
    }

    public String getTextElementSearched() throws InterruptedException {
        Thread.sleep(3000);
        return utils.waitForElement(titleElement).getText();
    }

    public void selectCustomization(int dropdown, String value) throws InterruptedException {
        WebElement element = driver.findElements(optionCustomization).get(dropdown);
        Thread.sleep(3000);
        utils.selectElementWeb(element, value);
    }

    public void clickBtnDownload(){
        utils.waitForElement(btnDownload).click();
    }

    public void copyBtnEmbedCode(){
        driver.findElements(btnBadges).get(0).click();
    }

    public String getTxtEmbedCode(){
        return utils.waitForElement(txtEmbedCode).getAttribute("value");
    }

    public void clickBtnCertificate(){
        utils.waitForElement(btnCertificate).click();
    }

    public String getCssCertificateBackground(){
        System.out.println(utils.waitForElement(certificateImg).getCssValue("background"));
        return utils.waitForElement(certificateImg).getCssValue("background");
    }

    public String getCssCertificateSize(){
        System.out.println( utils.waitForElement(certificateImg).getCssValue("height"));
        return utils.waitForElement(certificateImg).getCssValue("height");
    }

    public void clickDownloadCertificate(){
        utils.waitForElement(btndownloadCertificate).click();
    }

    public void clickBtnSocialTemplate(){
        utils.waitForElement(btnSocialTemplate).click();
    }

    public void clickBtnDownloadSocial(){
        driver.findElements(btnBadges).get(2).click();
    }

    public void clickBtnCopyTemplate(){
        driver.findElements(btnBadges).get(3).click();
    }

}
