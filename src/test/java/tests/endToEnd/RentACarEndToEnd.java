package tests.endToEnd;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;


public class RentACarEndToEnd {

    public static WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void invokeFirefox() {
        System.setProperty("webdriver.gecko.driver", "/Users/philippgorishniy/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://ca.kayak.com/");
        driver.get("https://www.ca.kayak.com/trips");
        driver.manage().window().maximize();
    }

    @Test
    public void endToEndRentACar() throws AWTException, InterruptedException {

        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/header/div/div/div/div[1]/div/div/button"))
                .click();

        Thread.sleep(3000);

        WebElement elmnt;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        elmnt = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@class='social-button Button-No-Standard-Style isGoogle']")));

        Actions actSignin = new Actions(driver);
        WebElement elmntSignin = driver
                .findElement(By.xpath("//button[@class='social-button Button-No-Standard-Style isGoogle']"));
        actSignin.moveToElement(elmntSignin).click();

        String MainWindow = driver.getWindowHandle();

        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();

            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

                driver.switchTo().window(ChildWindow);

                WebElement emailTxt = driver.findElement(By.xpath("//input[@id='identifierId']"));
                JavascriptExecutor jsExecutor1 = (JavascriptExecutor) driver;
                jsExecutor1.executeScript("arguments[0].value='mcitprojectapcv@gmail.com'", emailTxt);

                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                WebElement passwordTxt = driver.findElement(By.xpath("//input[@type='password']"));
                JavascriptExecutor jsExecutor2 = (JavascriptExecutor) driver;
                jsExecutor2.executeScript("arguments[0].value='Montreal123!'", passwordTxt);

                driver.close();
            }
        }

        driver.switchTo().window(MainWindow);

        Actions options = new Actions(driver);
        WebElement optionsLink = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div/div[1]/div/div/div/button"));
        options.moveToElement(optionsLink).click();

        Actions newTrip = new Actions(driver);
        WebElement createNew = driver.findElement(By.xpath("//div[normalize-space()='Create Trip']"));
        newTrip.moveToElement(createNew).click();

        WebElement Destination = driver.findElement(By.xpath("//input[@id='destination']"));
        JavascriptExecutor jsExecutor2 = (JavascriptExecutor) driver;
        jsExecutor2.executeScript("arguments[0].value='New York'", Destination);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement TripName = driver.findElement(By.xpath("//input[@id='trip-name']"));
        JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
        jsExecutor3.executeScript("arguments[0].value='Test Trip Rent A Car'", TripName);

        Actions start = new Actions(driver);
        WebElement startDate = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/form/div[2]/div[3]/div/div[1]/div/div/div/div/div/div/span/span[3]/span[2]"));
        start.moveToElement(startDate).click();
        start.moveToElement(startDate).click();
        start.moveToElement(startDate).click();
        start.moveToElement(startDate).click();

        Actions endCal = new Actions(driver);
        WebElement endCalBtn = driver.findElement(
                By.xpath("/html/body/div[2]/div/div[3]/div/div/div[2]/div/form/div[2]/div[3]/div/div[2]/div/div/div"));
        endCal.moveToElement(endCalBtn).click();

        Actions end = new Actions(driver);
        WebElement endDate = driver
                .findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/div[26]"));
        end.moveToElement(endDate).click();

        Actions onlyEmail = new Actions(driver);
        WebElement emailOnly = driver
                .findElement(By.xpath("//span[contains(text(), 'Only people you invite by email')]"));
        onlyEmail.moveToElement(emailOnly).click();

        Actions saveBtn = new Actions(driver);
        WebElement saveTripBtn = driver.findElement(By.xpath("//div[contains(text(), 'Save')]"));
        saveBtn.moveToElement(saveTripBtn).click();

        // add car

        Actions add = new Actions(driver);
        WebElement addBtn = driver.findElement(By.xpath("//*[@id=\"r9-trips-149-P-Itinerary\"]/div[2]/div[1]/button"));
        add.moveToElement(addBtn).click();

        Actions addCar = new Actions(driver);
        WebElement addCarBtn = driver.findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div[1]/div[3]/button"));
        addCar.moveToElement(addCarBtn).click();

        Select classSelect = new Select(driver.findElement(By.xpath(
                "//select[@aria-label='Choose if drop-off location is the same or different than pick-up location']")));
        classSelect.selectByVisibleText("Different drop-off");
        Thread.sleep(3000);

        WebElement pickupLocation = driver.findElement(By.xpath("//input[@placeholder='From?']"));
        pickupLocation.clear();
        JavascriptExecutor jsExecutor5 = (JavascriptExecutor) driver;
        jsExecutor5.executeScript("arguments[0].value='LGA'", pickupLocation);
        Robot robot1 = new Robot();
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.keyRelease(KeyEvent.VK_ENTER);

        WebElement dropoffLocation = driver.findElement(By.xpath("//input[@placeholder='From?']"));
        dropoffLocation.clear();
        JavascriptExecutor jsExecutor6 = (JavascriptExecutor) driver;
        jsExecutor6.executeScript("arguments[0].value='JFK'", dropoffLocation);
        Robot robot11 = new Robot();
        robot11.keyPress(KeyEvent.VK_ENTER);
        robot11.keyRelease(KeyEvent.VK_ENTER);

        Actions driverAge = new Actions(driver);
        WebElement driverAgeBtn = driver.findElement(By.xpath("//div[@aria-label='Choose driver age']"));
        driverAge.moveToElement(driverAgeBtn).click();

        WebElement ageInput = driver.findElement(By.xpath("//input[@placeholder='From?']"));
        ageInput.clear();
        JavascriptExecutor jsExecutor7 = (JavascriptExecutor) driver;
        jsExecutor7.executeScript("arguments[0].value='25'", ageInput);
        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);

        Actions startDate1 = new Actions(driver);
        WebElement startDateBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div[2]/div[1]/div[3]/div/div/div[1]/span[1]/span[2]"));
        startDate1.moveToElement(startDateBtn).click();

        Actions startDateSelect = new Actions(driver);
        WebElement startDateSelectBtn = driver
                .findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div/div/div[2]/div[2]/div[2]/div[9]"));
        startDateSelect.moveToElement(startDateSelectBtn).click();
        Robot robot3 = new Robot();
        robot3.keyPress(KeyEvent.VK_ENTER);
        robot3.keyRelease(KeyEvent.VK_ENTER);

        Actions endDate1 = new Actions(driver);
        WebElement endDateBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div[2]/div[1]/div[3]/div/div/div[3]/span[1]/span[2]"));
        endDate1.moveToElement(endDateBtn).click();

        Actions endDateSelect = new Actions(driver);
        WebElement endDateSelectBtn = driver
                .findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div/div/div[2]/div[2]/div[2]/div[16]"));
        endDateSelect.moveToElement(endDateSelectBtn).click();
        Robot robot4 = new Robot();
        robot4.keyPress(KeyEvent.VK_ENTER);
        robot4.keyRelease(KeyEvent.VK_ENTER);

        Actions startTime = new Actions(driver);
        WebElement startTimeBtn = driver.findElement(By.xpath(
                "//span[@aria-label='Start time input']//div[@class='JcOa-display'][normalize-space()='Midnight']"));
        startTime.moveToElement(startTimeBtn).click();

        Actions startTimeSelect = new Actions(driver);
        WebElement startTimeSelectBtn = driver.findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div/ul/li[2]"));
        startTimeSelect.moveToElement(startTimeSelectBtn).click();

        Actions endTime = new Actions(driver);
        WebElement endTimeBtn = driver.findElement(By.xpath(
                "//span[@aria-label='End time input']//div[@class='JcOa-display'][normalize-space()='2:00 am']"));
        endTime.moveToElement(endTimeBtn).click();

        Actions endTimeSelect = new Actions(driver);
        WebElement endTimeSelectBtn = driver.findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div/ul/li[6]"));
        endTimeSelect.moveToElement(endTimeSelectBtn).click();

        Actions searchC = new Actions(driver);
        WebElement searchCBtn = driver.findElement(By.xpath("//button[@title='Search cars']"));
        searchC.moveToElement(searchCBtn).click();

        Actions closeC = new Actions(driver);
        WebElement closeCBtn = driver.findElement(By.xpath("//div[@aria-label='Close']"));
        closeC.moveToElement(closeCBtn).click();

    }

    @AfterTest
    public void quitFF() {
        driver.quit();
    }
}
