package tests.endToEnd;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;


public class WishlistAndTripEndToEnd {

    public static WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void invokeFirefox() {
        System.setProperty("webdriver.gecko.driver", "/Users/philippgorishniy/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://ca.kayak.com/");
        driver.get("https://www.ca.kayak.com/explore");
        driver.manage().window().maximize();
    }

    @Test
    public void endToEndWishlistAndTrip() throws InterruptedException, AWTException {

        WebElement orgn = driver.findElement(By.xpath("//input[@placeholder='From?']"));
        JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
        jsExecutor1.executeScript("arguments[0].value='YUL'", orgn);

        WebElement destTxt = driver.findElement(By.xpath("//input[@placeholder='To?']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='MIA'", destTxt);
        Thread.sleep(3000);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Actions saveWishList = new Actions(driver);
        WebElement saveWishListBtn = driver.findElement(By.xpath("//span[contains(text(), 'Save to Wishlist')]"));
        saveWishList.moveToElement(saveWishListBtn).click();
        Thread.sleep(3000);

        driver.get("https://www.ca.kayak.com/trips");

        Actions startPlanning = new Actions(driver);
        WebElement startPlanningBtn = driver.findElement(By.xpath("//div[contains(text(), 'Start planning')]"));
        startPlanning.moveToElement(startPlanningBtn).click();
        Thread.sleep(3000);

        WebElement tripName = driver.findElement(By.xpath("//input[@aria-label='Trip name']"));
        JavascriptExecutor jsExecutor2 = (JavascriptExecutor) driver;
        jsExecutor2.executeScript("arguments[0].value='30'", tripName);

        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);

        Actions startDate = new Actions(driver);
        WebElement startDateBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div/div/form/div[1]/div[2]/div/div[1]/div/div/div/div/div/div/span/span[3]/span[2]"));
        startDate.moveToElement(startDateBtn).click();
        Thread.sleep(3000);
        startDate.moveToElement(startDateBtn).click();
        Thread.sleep(3000);

        Actions endCal = new Actions(driver);
        WebElement endCalBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div/div/form/div[1]/div[2]/div/div[2]/div/div[1]/div/div/div"));
        endCal.moveToElement(endCalBtn).click();

        Actions end = new Actions(driver);
        WebElement endDate = driver
                .findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/div[25]"));
        end.moveToElement(endDate).click();

        Actions saveTrip = new Actions(driver);
        WebElement saveTripBtn = driver.findElement(By.xpath("//div[contains(text(), 'Create Trip')]"));
        saveTrip.moveToElement(saveTripBtn).click();
    }

    @AfterTest
    public void quitFF() {
        driver.quit();
    }
}