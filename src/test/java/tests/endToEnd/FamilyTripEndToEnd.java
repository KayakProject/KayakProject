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


public class FamilyTripEndToEnd {

    public static WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void invokeFirefox() throws InterruptedException, AWTException {
        System.setProperty("webdriver.gecko.driver", "/Users/philippgorishniy/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://ca.kayak.com/");
        driver.get("https://www.ca.kayak.com/trips");
        driver.manage().window().maximize();

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
    }

    @Test
    public void endToEndFamilyTrip() throws AWTException, InterruptedException {

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
        jsExecutor3.executeScript("arguments[0].value='Test Trip End to end'", TripName);

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

        Actions open = new Actions(driver);
        WebElement openTrip = driver
                .findElement(By.xpath("//*[@id=\\\"r9-trips-43-P-upcoming_trips\\\"]/div[1]/div/article/div/a"));
        open.moveToElement(openTrip).click();

        Actions add = new Actions(driver);
        WebElement addBtn = driver.findElement(By.xpath("//*[@id=\"r9-trips-149-P-Itinerary\"]/div[2]/div[1]/button"));
        add.moveToElement(addBtn).click();

        Actions addFlight = new Actions(driver);
        WebElement addFlightBtn = driver.findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div[1]/div[1]/button"));
        addFlight.moveToElement(addFlightBtn).click();

        Actions adults = new Actions(driver);
        WebElement adultsBtn = driver.findElement(By.xpath("//span[contains(text(), '1 adult')]"));
        adults.moveToElement(adultsBtn).click();

        Actions addAdults = new Actions(driver);
        WebElement addAdultsBtn = driver
                .findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div[1]/div[1]/div/button[2]"));
        addAdults.moveToElement(addAdultsBtn).click();
        Thread.sleep(3000);
        addAdults.moveToElement(addAdultsBtn).click();
        Thread.sleep(3000);
        addAdults.moveToElement(addAdultsBtn).click();
        Thread.sleep(3000);
        addAdults.moveToElement(addAdultsBtn).click();
        Thread.sleep(3000);

        Select classSelect = new Select(driver.findElement(By.xpath("//select[@aria-label='Cabin Class']")));
        classSelect.selectByVisibleText("Business");
        Thread.sleep(3000);

        Actions addBags = new Actions(driver);
        WebElement addBagsBtn = driver.findElement(By
                .xpath("/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div/div/div/div[1]/div[1]/div[4]/div"));
        addBags.moveToElement(addBagsBtn).click();

        Actions addChecked = new Actions(driver);
        WebElement addCheckedBtn = driver
                .findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]/div/button[2]"));
        addChecked.moveToElement(addCheckedBtn).click();
        Thread.sleep(3000);
        addChecked.moveToElement(addCheckedBtn).click();

        Actions depart = new Actions(driver);
        WebElement departBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div/div/div/div[1]/div[2]/div/div[4]/div/div/div/div[1]/span/span[3]/span[2]"));
        depart.moveToElement(departBtn).click();
        Thread.sleep(3000);
        depart.moveToElement(departBtn).click();
        Thread.sleep(3000);
        depart.moveToElement(departBtn).click();

        Actions rtrn = new Actions(driver);
        WebElement returnBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div/div/div/div[1]/div[2]/div/div[4]/div/div/div/div[3]/span/span[3]/span[2]"));
        rtrn.moveToElement(returnBtn).click();
        Thread.sleep(3000);
        rtrn.moveToElement(returnBtn).click();
        Thread.sleep(3000);
        rtrn.moveToElement(returnBtn).click();
        Thread.sleep(3000);
        rtrn.moveToElement(returnBtn).click();
        Thread.sleep(3000);
        rtrn.moveToElement(returnBtn).click();

        Actions searchFlight = new Actions(driver);
        WebElement searchFlightBtn = driver
                .findElement(By.xpath("//button[@aria-label='Search']//div[@class='Iqt3-button-content']"));
        searchFlight.moveToElement(searchFlightBtn).click();

        Actions closeF = new Actions(driver);
        WebElement closeFBtn = driver.findElement(By.xpath("//div[@aria-label='Close']"));
        closeF.moveToElement(closeFBtn).click();

        add.moveToElement(addBtn).click();

        Actions addStay = new Actions(driver);
        WebElement addStayBtn = driver.findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div[1]/div[2]/button"));
        addStay.moveToElement(addStayBtn).click();

        WebElement stayLocation = driver
                .findElement(By.xpath("//input[@placeholder='Enter a city, hotel, airport, address or landmark']"));
        stayLocation.clear();

        JavascriptExecutor jsExecutor4 = (JavascriptExecutor) driver;
        jsExecutor4.executeScript("arguments[0].value='Central Park Zoo'", stayLocation);
        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);

        Actions from = new Actions(driver);
        WebElement fromBtn = driver.findElement(By.xpath(
                "//span[@aria-label='Start date calendar input']//span[@aria-label='Increment date by one day']//span//*[name()='svg']"));
        from.moveToElement(fromBtn).click();

        Actions to = new Actions(driver);
        WebElement toBtn = driver.findElement(By.xpath(
                "//span[@aria-label='End date calendar input']//span[@aria-label='Increment date by one day']//span//*[name()='svg']"));
        to.moveToElement(toBtn).doubleClick();

        Actions roomGuests = new Actions(driver);
        WebElement roomGuestsBtn = driver.findElement(By.xpath("//span[contains(text(), '1 room, 2 guests')]"));
        roomGuests.moveToElement(roomGuestsBtn).click();

        Actions guests = new Actions(driver);
        WebElement guestsBtn = driver
                .findElement(By.xpath("//*[@id=\"popover\"]/div/div/div/div/div/div[2]/div/button[2]"));
        guests.moveToElement(guestsBtn).click();
        Thread.sleep(3000);
        guests.moveToElement(guestsBtn).click();
        Robot robot1 = new Robot();
        robot1.keyPress(KeyEvent.VK_ESCAPE);
        robot1.keyRelease(KeyEvent.VK_ESCAPE);

        Actions searchStays = new Actions(driver);
        WebElement searchStaysBtn = driver.findElement(By.xpath("//button[@title='Search']"));
        searchStays.moveToElement(searchStaysBtn).click();

        Actions closeS = new Actions(driver);
        WebElement closeSBtn = driver.findElement(By.xpath("//div[@aria-label='Close']"));
        closeS.moveToElement(closeSBtn).click();
    }

    @AfterTest
    public void quitFF() {
        driver.quit();
    }
}
