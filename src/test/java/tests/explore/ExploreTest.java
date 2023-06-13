package tests.explore;

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


public class ExploreTest {

    public static WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void invokeFirefox() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/Users/philippgorishniy/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.ca.kayak.com/explore/YUL-anywhere");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,10)");
    }

    @Test
    public void showMoreDest() throws InterruptedException {

        Actions act = new Actions(driver);
        WebElement elmnt = driver.findElement(
                By.xpath("/html/body/div[2]/div[1]/main/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[6]/button"));
        act.moveToElement(elmnt).click();

    }

    @Test
    public void sendKeysOrigin() throws InterruptedException {

        WebElement orgnTxt = driver.findElement(By.xpath("//input[@placeholder='From?']"));
        JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
        jsExecutor1.executeScript("arguments[0].value='YUL'", orgnTxt);
        Thread.sleep(3000);

    }

    @Test
    public void sendKeysDest() throws InterruptedException {

        WebElement destTxt = driver.findElement(By.xpath("//input[@placeholder='To?']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='NYC'", destTxt);
        Thread.sleep(3000);
    }

    @Test
    public void sendInfoDates() throws InterruptedException {

        Actions actDates = new Actions(driver);
        WebElement elmntDates = driver.findElement(
                By.xpath("/html/body/div[2]/div[1]/main/div[1]/div/div/div[2]/div/div[3]/div/div[1]/div/div"));
        actDates.moveToElement(elmntDates).click();
        Thread.sleep(3000);

        Actions rangeDates = new Actions(driver);
        WebElement rangeDatesBtn = driver.findElement(By.xpath("//label[@title='Dec. 2023']"));
        rangeDates.moveToElement(rangeDatesBtn).click();
        Thread.sleep(3000);

        Actions exactDates = new Actions(driver);
        WebElement exactDatesBtn = driver.findElement(By.xpath("//div[@title='Exact Dates']"));
        exactDates.moveToElement(exactDatesBtn).click();
        Thread.sleep(3000);

        Actions from = new Actions(driver);
        WebElement fromBtn = driver.findElement(By.xpath(
                "/html/body/div[6]/div/div[2]/div[2]/div/div[4]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[3]/div[5]/div[3]"));
        from.moveToElement(fromBtn).click();
        Thread.sleep(3000);

        Actions to = new Actions(driver);
        WebElement toBtn = driver.findElement(By.xpath(
                "/html/body/div[6]/div/div[2]/div[2]/div/div[4]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[3]/div[5]/div[6]"));
        to.moveToElement(toBtn).click();
        Thread.sleep(3000);

    }

    @Test
    public void upToOneStop() throws InterruptedException {

        Actions upToOneStop = new Actions(driver);
        WebElement upToOneStopBtn = driver.findElement(By.xpath("//span[contains(text(),'Up to one stop')]"));
        upToOneStop.moveToElement(upToOneStopBtn).click();
        Thread.sleep(3000);
    }

    @Test
    public void directOnly() throws InterruptedException {

        Actions directOnly = new Actions(driver);
        WebElement directOnlyBtn = driver.findElement(By.xpath("//span[contains(text(),'Direct only')]"));
        directOnly.moveToElement(directOnlyBtn).click();
        Thread.sleep(3000);

    }

    @Test
    public void familyTrip() throws InterruptedException {

        Actions familyTrip = new Actions(driver);
        WebElement familyTripBtn = driver.findElement(By.xpath("//span[contains(text(),'Family')]"));
        familyTrip.moveToElement(familyTripBtn).click();
        Thread.sleep(3000);

    }

    @Test
    public void searchExplore() throws InterruptedException, AWTException {

        Actions search = new Actions(driver);
        WebElement searchBtn = driver.findElement(By.xpath("//button[@aria-label='Submit search']"));
        search.moveToElement(searchBtn).click();
        Thread.sleep(3000);
    }

    @Test
    public void flightPrices() throws InterruptedException {

        Actions fp = new Actions(driver);
        WebElement fpBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div[1]/main/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div[2]/div[2]/div[1]/div[3]/a"));
        fp.moveToElement(fpBtn).click();
        Thread.sleep(3000);
    }

    @Test
    public void directFlightsTo() throws InterruptedException {

        Actions ft = new Actions(driver);
        WebElement ftBtn = driver.findElement(By.xpath("//button[contains(text(),'Go to direct flight schedule')]"));
        ft.moveToElement(ftBtn).click();
        Thread.sleep(3000);

    }

    @Test
    public void directFlightsFrom() throws InterruptedException {

        Actions ff = new Actions(driver);
        WebElement ffBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div[1]/main/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div[2]/div[2]/div[3]/div/button[2]"));
        ff.moveToElement(ffBtn).click();
        Thread.sleep(3000);

    }

    @Test
    public void briefIntro() throws InterruptedException {

        WebElement briefIntro1 = driver.findElement(By.xpath("//*[@id=\"c9mOT\"]/p[1]/text()"));
        WebElement briefIntro2 = driver.findElement(By.xpath("//*[@id=\"c9mOT\"]/p[2]/text()"));
        WebElement briefIntro3 = driver.findElement(By.xpath("//*[@id=\"c9mOT\"]/p[3]/text()"));

        System.out.println(briefIntro1.getText());
        System.out.println(briefIntro2.getText());
        System.out.println(briefIntro3.getText());

    }

    @Test
    public void priceCheck() {

        Actions scrollRight = new Actions(driver);
        WebElement scrollRightBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div[1]/main/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div[2]/div[3]/section/div/div/div[3]/div/div/div[4]/a[2]"));
        scrollRight.moveToElement(scrollRightBtn).click();

        Actions scrollLeft = new Actions(driver);
        WebElement scrollLeftBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div[1]/main/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div[2]/div[3]/section/div/div/div[3]/div/div/div[4]/a[1]"));
        scrollLeft.moveToElement(scrollLeftBtn).click();

    }

    @Test
    public void destPhotos() throws InterruptedException {

        Actions act2 = new Actions(driver);
        WebElement elmnt2 = driver.findElement(By.xpath("//div[contains(text(), 'See All')]"));
        act2.moveToElement(elmnt2).click();

    }

    @Test
    public void nextPhoto() {

        Actions act3 = new Actions(driver);
        WebElement elmnt3 = driver.findElement(By.xpath(
                "/html[1]/body[1]/div[12]/div[1]/div[1]/div[1]/button[1]/*[name()='svg'][1]/*[name()='use'][1]"));
        act3.moveToElement(elmnt3).click();

    }

    @Test
    public void prevPhoto() {

        Actions act4 = new Actions(driver);
        WebElement elmnt4 = driver.findElement(By.xpath("/html/body/div[13]/div/div[1]/div[2]/button"));
        act4.moveToElement(elmnt4).click();

    }

    @Test
    public void closePhotos() {

        Actions act5 = new Actions(driver);
        WebElement elmnt5 = driver.findElement(By.xpath("/html/body/div[13]/div/div[1]/div[1]/button"));
        act5.moveToElement(elmnt5).click();

    }

    @Test
    public void nextMustSee() {

        Actions act6 = new Actions(driver);
        WebElement elmnt6 = driver.findElement(By.xpath(
                "/html/body/div[2]/div[1]/main/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div[2]/div[3]/div[7]/div/div/div/div[2]/button[2]"));
        act6.moveToElement(elmnt6).click();

    }

    @Test
    public void prevMustSee() {

        Actions act7 = new Actions(driver);
        WebElement elmnt7 = driver.findElement(By.xpath(
                "/html/body/div[2]/div[1]/main/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div[2]/div[3]/div[7]/div/div/div/div[2]/button[1]"));
        act7.moveToElement(elmnt7).click();
    }

    @AfterTest
    public void quitFF() {
        driver.quit();
    }
}
