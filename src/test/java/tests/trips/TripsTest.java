package tests.trips;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.awt.AWTException;
import java.awt.Robot;

import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.Iterator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.JavascriptExecutor;
import java.util.Set;

public class TripsTest {

    public static WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void invokeFirefox() {
        System.setProperty("webdriver.gecko.driver", "/Users/philippgorishniy/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.ca.kayak.com/trips");
        driver.manage().window().maximize();
    }

    @Test
    public void signIntoAcct() throws InterruptedException, AWTException {

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
    public void goToExplore() {

        Actions goExplore = new Actions(driver);
        WebElement exploreLink = driver.findElement(By.xpath("//div[@class='Iqt3-button-content']"));
        goExplore.moveToElement(exploreLink).click();

    }

    @Test
    public void createTrip() throws AWTException {

        WebElement element1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        element1 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div/div[1]/div/div/div/button")));

        Actions goOpt = new Actions(driver);
        WebElement optLink = driver
                .findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div/div[1]/div/div/div/button"));
        goOpt.moveToElement(optLink).click();

        WebElement element2;
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
        element2 = wait2.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div/div/div/div[1]/div/div/div/button")));

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
        jsExecutor3.executeScript("arguments[0].value='Test Trip'", TripName);

        Actions start = new Actions(driver);
        WebElement startDate = driver
                .findElement(By.xpath("//*[@id=\"start-date\"]/div/div/div/span/span[3]/span[2]/span/svg"));
        start.moveToElement(startDate).click();

        Actions endCal = new Actions(driver);
        WebElement endCalBtn = driver
                .findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div[2]/div/form/div[2]/div[3]/div/div[2]/div/div/div"));
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

    }

    @Test
    public void openTrip() {

        Actions open = new Actions(driver);
        WebElement openTrip = driver
                .findElement(By.xpath("//*[@id=\\\"r9-trips-43-P-upcoming_trips\\\"]/div[1]/div/article/div/a"));
        open.moveToElement(openTrip).click();

    }

    @Test
    public void shareBtn() throws InterruptedException {

        Actions share = new Actions(driver);
        WebElement shareTripBtn = driver.findElement(By.xpath("//div[contains(text(),'Share')]"));
        share.moveToElement(shareTripBtn).click();

    }

    @Test
    public void privacyBtn() throws InterruptedException {

        Actions privacy = new Actions(driver);
        WebElement privacyBtn = driver.findElement(By.xpath("//*[@id=\\\"public\\\"]"));
        privacy.moveToElement(privacyBtn).click();

    }

    @Test
    public void savePrivacyBtn() throws InterruptedException {

        Actions saveprivacy = new Actions(driver);
        WebElement saveprivacyBtn = driver
                .findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div[2]/div/div/div[4]/button[1]"));
        saveprivacy.moveToElement(saveprivacyBtn).click();

    }

    @Test
    public void closePrivacyBtn() throws InterruptedException {

        Actions closeprivacy = new Actions(driver);
        WebElement closeprivacyBtn = driver.findElement(By.xpath("//div[@aria-label='Close']"));
        closeprivacy.moveToElement(closeprivacyBtn).click();

    }

    @Test
    public void shareInpt() throws InterruptedException, AWTException {

        Actions share = new Actions(driver);
        WebElement shareTripBtn = driver.findElement(By.xpath("//div[contains(text(),'Share')]"));
        share.moveToElement(shareTripBtn).click();

        WebElement passwordTxtInvite = driver.findElement(By.xpath("//input[@id='chip-input']"));
        JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
        jsExecutor3.executeScript("arguments[0].value='abc@yahoo.com'", passwordTxtInvite);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Actions invite = new Actions(driver);
        WebElement inviteBtn = driver
                .findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div[2]/div/div/div[3]/div[2]/button"));
        invite.moveToElement(inviteBtn).click();

        Actions close = new Actions(driver);
        WebElement closeBtn = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div/div[1]"));
        close.moveToElement(closeBtn).click();

    }

    @Test
    public void addFlight() throws InterruptedException {

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

        Select classSelect = new Select(driver.findElement(By.xpath("//select[@aria-label='Cabin Class']")));
        classSelect.selectByVisibleText("Business");
        Thread.sleep(3000);

        Actions addBags = new Actions(driver);
        WebElement addBagsBtn = driver.findElement(By
                .xpath("/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div/div/div/div[1]/div[1]/div[4]/div"));
        addBags.moveToElement(addBagsBtn).click();

        Actions addCarryOn = new Actions(driver);
        WebElement addCarryOnBtn = driver
                .findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div[1]/div[1]/div/button[2]"));
        addCarryOn.moveToElement(addCarryOnBtn).click();

        Actions depart = new Actions(driver);
        WebElement departBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div/div/div/div[1]/div[2]/div/div[4]/div/div/div/div[1]/span/span[3]/span[2]"));
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

        Actions searchFlight = new Actions(driver);
        WebElement searchFlightBtn = driver
                .findElement(By.xpath("//button[@aria-label='Search']//div[@class='Iqt3-button-content']"));
        searchFlight.moveToElement(searchFlightBtn).click();

        Actions closeF = new Actions(driver);
        WebElement closeFBtn = driver.findElement(By.xpath("//div[@aria-label='Close']"));
        closeF.moveToElement(closeFBtn).click();

    }

    @Test
    public void addStay() throws AWTException, InterruptedException {

        Actions add = new Actions(driver);
        WebElement addBtn = driver.findElement(By.xpath("//*[@id=\"r9-trips-149-P-Itinerary\"]/div[2]/div[1]/button"));
        add.moveToElement(addBtn).click();

        Actions addStay = new Actions(driver);
        WebElement addStayBtn = driver.findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div[1]/div[2]/button"));
        addStay.moveToElement(addStayBtn).click();

        WebElement stayLocation = driver
                .findElement(By.xpath("//input[@placeholder='Enter a city, hotel, airport, address or landmark']"));
        stayLocation.clear();

        JavascriptExecutor jsExecutor4 = (JavascriptExecutor) driver;
        jsExecutor4.executeScript("arguments[0].value='Empire State Building'", stayLocation);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Actions from = new Actions(driver);
        WebElement fromBtn = driver.findElement(By.xpath(
                "//span[@aria-label='Start date calendar input']//span[@aria-label='Increment date by one day']//span//*[name()='svg']"));
        from.moveToElement(fromBtn).click();

        Actions to = new Actions(driver);
        WebElement toBtn = driver.findElement(By.xpath(
                "//span[@aria-label='End date calendar input']//span[@aria-label='Increment date by one day']//span//*[name()='svg']"));
        to.moveToElement(toBtn).click();

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

    @Test
    public void addCar() throws AWTException, InterruptedException {

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
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement dropoffLocation = driver.findElement(By.xpath("//input[@placeholder='From?']"));
        dropoffLocation.clear();
        JavascriptExecutor jsExecutor6 = (JavascriptExecutor) driver;
        jsExecutor6.executeScript("arguments[0].value='JFK'", dropoffLocation);
        Robot robot1 = new Robot();
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.keyRelease(KeyEvent.VK_ENTER);

        Actions driverAge = new Actions(driver);
        WebElement driverAgeBtn = driver.findElement(By.xpath("//div[@aria-label='Choose driver age']"));
        driverAge.moveToElement(driverAgeBtn).click();

        WebElement ageInput = driver.findElement(By.xpath("//input[@placeholder='From?']"));
        ageInput.clear();
        JavascriptExecutor jsExecutor7 = (JavascriptExecutor) driver;
        jsExecutor7.executeScript("arguments[0].value='30'", ageInput);
        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);

        Actions startDate = new Actions(driver);
        WebElement startDateBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div[2]/div[1]/div[3]/div/div/div[1]/span[1]/span[2]"));
        startDate.moveToElement(startDateBtn).click();

        Actions startDateSelect = new Actions(driver);
        WebElement startDateSelectBtn = driver
                .findElement(By.xpath("//*[@id=\"popover\"]/div/div[2]/div/div/div[2]/div[2]/div[2]/div[9]"));
        startDateSelect.moveToElement(startDateSelectBtn).click();
        Robot robot3 = new Robot();
        robot3.keyPress(KeyEvent.VK_ENTER);
        robot3.keyRelease(KeyEvent.VK_ENTER);

        Actions endDate = new Actions(driver);
        WebElement endDateBtn = driver.findElement(By.xpath(
                "/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div[2]/div[1]/div[3]/div/div/div[3]/span[1]/span[2]"));
        endDate.moveToElement(endDateBtn).click();

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

    @Test
    public void editTrip() throws AWTException {
        Actions editTrip = new Actions(driver);
        WebElement editTripBtn = driver.findElement(By.xpath("//button[@aria-label='More options']"));
        editTrip.moveToElement(editTripBtn).click();
        Robot robot5 = new Robot();
        robot5.keyPress(KeyEvent.VK_ENTER);
        robot5.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void printTrip() {
        Actions editTrip = new Actions(driver);
        WebElement editTripBtn = driver.findElement(By.xpath("//button[@aria-label='More options']"));
        editTrip.moveToElement(editTripBtn).click();
        Actions printTrip = new Actions(driver);
        WebElement printTripBtn = driver.findElement(By.xpath("//div[normalize-space()='Print itinerary']"));
        printTrip.moveToElement(printTripBtn).click();
    }

    @Test
    public void addToCal() {

        Actions editTrip = new Actions(driver);
        WebElement editTripBtn = driver.findElement(By.xpath("//button[@aria-label='More options']"));
        editTrip.moveToElement(editTripBtn).click();

        Actions addToCal = new Actions(driver);
        WebElement addToCalBtn = driver.findElement(By.xpath("//div[normalize-space()='Add to calendar']"));
        addToCal.moveToElement(addToCalBtn).click();

        Actions copyToCal = new Actions(driver);
        WebElement copyToCalBtn = driver.findElement(
                By.xpath("/html/body/div[2]/div/div[3]/div/div/div[2]/div/div[2]/div[2]/div/button/span[1]/span"));
        copyToCal.moveToElement(copyToCalBtn).click();

        Actions close = new Actions(driver);
        WebElement closeBtn = driver.findElement(By.xpath("//div[@aria-label='Close']"));
        close.moveToElement(closeBtn).click();

    }

    @Test
    public void moveTrip() {

        Actions editTrip = new Actions(driver);
        WebElement editTripBtn = driver.findElement(By.xpath("//button[@aria-label='More options']"));
        editTrip.moveToElement(editTripBtn).click();

        Actions moveTrip = new Actions(driver);
        WebElement moveTripBtn = driver
                .findElement(By.xpath("//div[normalize-space()='//div[normalize-space()='Move to another account']']"));
        moveTrip.moveToElement(moveTripBtn).click();

        WebElement moveToAcct = driver.findElement(By.xpath("//input[@id='email']"));
        moveToAcct.clear();
        JavascriptExecutor jsExecutor7 = (JavascriptExecutor) driver;
        jsExecutor7.executeScript("arguments[0].value='xyz@gmail.com'", moveToAcct);

        Actions moveTripConfirm = new Actions(driver);
        WebElement moveTripConfirmBtn = driver
                .findElement(By.xpath("//div[@class='Iqt3-button-content'][normalize-space()='Move']"));
        moveTripConfirm.moveToElement(moveTripConfirmBtn).click();

        Actions moveTripSecondConfirm = new Actions(driver);
        WebElement moveTripSecondConfirmBtn = driver.findElement(By.xpath(
                "//button[@class='Iqt3 Iqt3-mod-bold Button-No-Standard-Style Iqt3-mod-variant-solid Iqt3-mod-theme-action Iqt3-mod-shape-rounded-small Iqt3-mod-shape-mod-default Iqt3-mod-spacing-default Iqt3-mod-size-default']"));
        moveTripSecondConfirm.moveToElement(moveTripSecondConfirmBtn).click();

        Actions moveTripConfirmClose = new Actions(driver);
        WebElement moveTripConfirmCloseBtn = driver.findElement(By.xpath("//div[contains(text(),'Close')]"));
        moveTripConfirmClose.moveToElement(moveTripConfirmCloseBtn).click();
    }

    @AfterTest
    public void quitFF() {
        driver.quit();
    }
}