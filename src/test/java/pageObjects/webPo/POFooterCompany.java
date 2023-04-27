package pageObjects.webPo;

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
}
