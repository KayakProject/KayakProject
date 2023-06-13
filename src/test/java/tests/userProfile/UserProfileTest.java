package tests.userProfile;

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

public class UserProfileTest {

    public static WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void invokeFirefox() {
        System.setProperty("webdriver.gecko.driver", "/Users/philippgorishniy/geckodriver");
        driver = new FirefoxDriver();
        driver.get("https://www.ca.kayak.com/");
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
    public void goToDB() {

        Actions goToAcct = new Actions(driver);
        WebElement goToAcctBtn = driver.findElement(By.xpath("//span[@class='account-name inspectlet-sensitive']"));
        goToAcct.moveToElement(goToAcctBtn).click();

        Actions goToDB = new Actions(driver);
        WebElement goToDBBtn = driver.findElement(By.xpath("//div[@id='account-menu-item-label']"));
        goToDB.moveToElement(goToDBBtn).click();

    }

    @Test
    public void goToTrips() {

        Actions goToTrips = new Actions(driver);
        WebElement goToTripsBtn = driver.findElement(By.xpath("//div[@class='Iqt3-button-content']"));
        goToTrips.moveToElement(goToTripsBtn).click();
    }

    @Test
    public void scroll() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,10)");
        js.executeScript("window.scrollBy(0,-10)");
    }

    @Test
    public void viewAcctTab() throws InterruptedException {

        Actions viewAcctTab = new Actions(driver);
        WebElement viewAcctTabBtn = driver.findElement(By.xpath("//button[contains(text(), 'Account')]"));
        viewAcctTab.moveToElement(viewAcctTabBtn).click();

    }

    @Test
    public void changeAvatar() throws InterruptedException {

        Actions changeAvatar = new Actions(driver);
        WebElement changeAvatarBtn = driver.findElement(By.xpath("//*[@id=\"b_cg\"]"));
        changeAvatar.moveToElement(changeAvatarBtn).click();

        Actions selectAvatar = new Actions(driver);
        WebElement selectAvatarBtn = driver.findElement(By.xpath("//div[@id='b_cg-petCarousel']//div[3]//img[1]"));
        selectAvatar.moveToElement(selectAvatarBtn).click();

    }

    @Test
    public void deleteAvatar() throws InterruptedException {

        Actions deleteAvatar = new Actions(driver);
        WebElement deleteAvatarBtn = driver
                .findElement(By.xpath("/html/body/div[1]/div[1]/main/section/div/div/div[1]/div/div[1]"));
        deleteAvatar.moveToElement(deleteAvatarBtn).click();

        Actions confirmDeleteAvatar = new Actions(driver);
        WebElement confirmDeleteAvatarBtn = driver
                .findElement(By.xpath("/html/body/div[3]/div[3]/div/div/div[3]/button"));
        confirmDeleteAvatar.moveToElement(confirmDeleteAvatarBtn).click();

    }

    @Test
    public void editName() throws InterruptedException {

        Actions editName = new Actions(driver);
        WebElement editNameBtn = driver.findElement(By.xpath("//button[@aria-label='edit your name']"));
        editName.moveToElement(editNameBtn).click();

        WebElement firstNameInput = driver.findElement(By.xpath("//input[@aria-label='First name']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='Jean'", firstNameInput);

        WebElement lastNameInput = driver.findElement(By.xpath("//input[@aria-label='Last name']"));
        JavascriptExecutor jsExecutor2 = (JavascriptExecutor) driver;
        jsExecutor2.executeScript("arguments[0].value='Untel'", lastNameInput);

        Actions saveName = new Actions(driver);
        WebElement saveNameBtn = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[1]/div/div/div[5]/div[3]/button[1]"));
        saveName.moveToElement(saveNameBtn).click();

    }

    @Test
    public void editDisplayName() {

        Actions editDisplayName = new Actions(driver);
        WebElement editDisplayNameBtn = driver.findElement(By.xpath("//button[@aria-label='edit your display name']"));
        editDisplayName.moveToElement(editDisplayNameBtn).click();

        WebElement displayNameInput = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[1]/div/div/div[6]/div[1]/input"));
        JavascriptExecutor jsExecutor3 = (JavascriptExecutor) driver;
        jsExecutor3.executeScript("arguments[0].value='JUntel'", displayNameInput);

        Actions saveName = new Actions(driver);
        WebElement saveNameBtn = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[1]/div/div/div[6]/div[2]/button[1]"));
        saveName.moveToElement(saveNameBtn).click();

    }

    @Test
    public void selectSite() throws InterruptedException {

        Select classSelect = new Select(driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[1]/div/div/div[1]/div[6]/div[2]/div/select")));
        classSelect.selectByVisibleText("Canada (EN)");
        Thread.sleep(3000);

    }

    @Test
    public void readCalInstructions() {

        Actions readCalInstructions = new Actions(driver);
        WebElement readCalInstructionsBtn = driver.findElement(By.xpath("//a[contains(text(), 'Read instructions')]"));
        readCalInstructions.moveToElement(readCalInstructionsBtn).click();
    }

    @Test
    public void togglePI() {

        Actions toggleGC = new Actions(driver);
        WebElement toggleGCSlider = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[5]/div/div[2]/div[2]/div/label"));
        toggleGC.moveToElement(toggleGCSlider).click();

    }

    @Test
    public void toggleTP() {

        Actions toggleTP = new Actions(driver);
        WebElement toggleTPSlider = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[5]/div/div[3]/div[2]/div/label"));
        toggleTP.moveToElement(toggleTPSlider).click();
    }

    @Test
    public void toggleBP() {

        Actions toggleBP = new Actions(driver);
        WebElement toggleBPSlider = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[5]/div/div[4]/div[2]/div/label"));
        toggleBP.moveToElement(toggleBPSlider).click();
    }

    @Test
    public void viewPrefTab() throws InterruptedException {

        Actions viewPrefTab = new Actions(driver);
        WebElement viewPrefTabBtn = driver.findElement(By.xpath("//button[contains(text(), 'Preferences')]"));
        viewPrefTab.moveToElement(viewPrefTabBtn).click();

    }

    @Test
    public void airportsSet() throws AWTException {

        WebElement homeAirInput = driver.findElement(By.xpath("//input[@placeholder='Add a primary home airport']"));
        JavascriptExecutor jsExecutor4 = (JavascriptExecutor) driver;
        jsExecutor4.executeScript("arguments[0].value='YUL'", homeAirInput);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement secondAirInput = driver
                .findElement(By.xpath("//input[@placeholder='Search for alternative airports']"));
        JavascriptExecutor jsExecutor5 = (JavascriptExecutor) driver;
        jsExecutor5.executeScript("arguments[0].value='YYZ'", secondAirInput);

        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void airlinesSet() throws AWTException {

        WebElement prefAirInput = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div/div/div[2]/section/div/div[1]/div/div/div/input"));
        JavascriptExecutor jsExecutor6 = (JavascriptExecutor) driver;
        jsExecutor6.executeScript("arguments[0].value='AC'", prefAirInput);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement avoidAirInput = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div/div/div[2]/section/div/div[2]/div/div/div/input"));
        JavascriptExecutor jsExecutor7 = (JavascriptExecutor) driver;
        jsExecutor7.executeScript("arguments[0].value='DL'", avoidAirInput);

        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void hotelsSet() throws AWTException {

        WebElement prefHotelInput = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div/div/div[3]/section/div/div[1]/div/div/div/input"));
        JavascriptExecutor jsExecutor8 = (JavascriptExecutor) driver;
        jsExecutor8.executeScript("arguments[0].value='AC'", prefHotelInput);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement avoidHotelInput = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div/div/div[3]/section/div/div[2]/div/div/div/input"));
        JavascriptExecutor jsExecutor9 = (JavascriptExecutor) driver;
        jsExecutor9.executeScript("arguments[0].value='DL'", avoidHotelInput);

        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void BSSet() throws AWTException {

        WebElement prefBSInput = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div/div/div[4]/section/div/div[1]/div/div/div/input"));
        JavascriptExecutor jsExecutor10 = (JavascriptExecutor) driver;
        jsExecutor10.executeScript("arguments[0].value='FlightHub'", prefBSInput);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement avoidBSInput = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div/div/div[4]/section/div/div[2]/div/div/div/input"));
        JavascriptExecutor jsExecutor11 = (JavascriptExecutor) driver;
        jsExecutor11.executeScript("arguments[0].value='Flightsearch'", avoidBSInput);

        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void savePlace() {

        Actions savePlace = new Actions(driver);
        WebElement savePlaceBtn = driver
                .findElement(By.xpath("//input[@placeholder='Search for a hotel, address or landmark']"));
        savePlace.moveToElement(savePlaceBtn).click();

        WebElement placeInput = driver.findElement(By.xpath(""));
        JavascriptExecutor jsExecutor12 = (JavascriptExecutor) driver;
        jsExecutor12.executeScript("arguments[0].value='Montreal'", placeInput);

        WebElement placeAddress = driver.findElement(By.xpath("//input[@aria-label='Address title']"));
        JavascriptExecutor jsExecutor13 = (JavascriptExecutor) driver;
        jsExecutor13.executeScript("arguments[0].value='MTL'", placeAddress);

        Actions saveChanges = new Actions(driver);
        WebElement saveChangesBtn = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div/div/section/div/div/div[2]/div/div[1]/button"));
        saveChanges.moveToElement(saveChangesBtn).click();
    }

    @Test
    public void deletePlace() {

        Actions deletePlace = new Actions(driver);
        WebElement deletePlaceBtn = driver.findElement(By.xpath("//button[contains(text(), 'Delete')]"));
        deletePlace.moveToElement(deletePlaceBtn).click();
    }

    @Test
    public void viewTravTab() {

        Actions viewTravTab = new Actions(driver);
        WebElement viewTravTabBtn = driver.findElement(By.xpath("//button[contains(text(), 'Travellers')]"));
        viewTravTab.moveToElement(viewTravTabBtn).click();
    }

    @Test
    public void editTrav() throws InterruptedException {

        Actions editTravTab = new Actions(driver);
        WebElement editTravTabBtn = driver.findElement(By.xpath("//a[contains(text(), 'Edit traveler')]"));
        editTravTab.moveToElement(editTravTabBtn).click();

        WebElement addFN = driver.findElement(By.xpath("//input[@id='firstName']"));
        JavascriptExecutor jsExecutor12 = (JavascriptExecutor) driver;
        jsExecutor12.executeScript("arguments[0].value='Jean'", addFN);

        WebElement addLN = driver.findElement(By.xpath("//input[@id='lastName']"));
        JavascriptExecutor jsExecutor13 = (JavascriptExecutor) driver;
        jsExecutor13.executeScript("arguments[0].value='Pierre'", addLN);

        WebElement addMN = driver.findElement(By.xpath("//input[@id='middleName']"));
        JavascriptExecutor jsExecutor14 = (JavascriptExecutor) driver;
        jsExecutor14.executeScript("arguments[0].value='Untel'", addMN);

        WebElement addDOB = driver.findElement(By.xpath("//input[@id='dateOfBirth']"));
        JavascriptExecutor jsExecutor15 = (JavascriptExecutor) driver;
        jsExecutor15.executeScript("arguments[0].value='04/05/1977'", addDOB);

        Select genderSelect = new Select(driver.findElement(By.xpath("//select[@name='gender']")));
        genderSelect.selectByVisibleText("Male (M)");
        Thread.sleep(3000);

        Select phoneCountryCodeSelect = new Select(
                driver.findElement(By.xpath("//select[@name='phoneCountryCodeISO']")));
        phoneCountryCodeSelect.selectByVisibleText("Canada");
        Thread.sleep(3000);

        WebElement addPhone = driver.findElement(By.xpath("//input[@aria-label='Cell phone number']"));
        JavascriptExecutor jsExecutor16 = (JavascriptExecutor) driver;
        jsExecutor16.executeScript("arguments[0].value='5141234567'", addPhone);

        Actions saveTrav = new Actions(driver);
        WebElement saveTravBtn = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[1]/section/div/div/div[2]/div[3]/div[6]/div/div/div[1]/button/div[1]/div"));
        saveTrav.moveToElement(saveTravBtn).click();

    }

    @Test
    public void editComp() throws InterruptedException {

        Actions editCompTab = new Actions(driver);
        WebElement editCompBtn = driver.findElement(By.xpath("//button[contains(text(),'Add traveller')]"));
        editCompTab.moveToElement(editCompBtn).click();

        WebElement addFN = driver.findElement(By.xpath("//input[@id='firstName']"));
        JavascriptExecutor jsExecutor16 = (JavascriptExecutor) driver;
        jsExecutor16.executeScript("arguments[0].value='Jane'", addFN);

        WebElement addLN = driver.findElement(By.xpath("//input[@id='lastName']"));
        JavascriptExecutor jsExecutor17 = (JavascriptExecutor) driver;
        jsExecutor17.executeScript("arguments[0].value='Smith'", addLN);

        WebElement addMN = driver.findElement(By.xpath("//input[@id='middleName']"));
        JavascriptExecutor jsExecutor18 = (JavascriptExecutor) driver;
        jsExecutor18.executeScript("arguments[0].value='Mary'", addMN);

        WebElement addDOB = driver.findElement(By.xpath("//input[@id='dateOfBirth']"));
        JavascriptExecutor jsExecutor19 = (JavascriptExecutor) driver;
        jsExecutor19.executeScript("arguments[0].value='01/01/1977'", addDOB);

        Select genderSelect = new Select(driver.findElement(By.xpath("//select[@name='gender']")));
        genderSelect.selectByVisibleText("Female (F)");
        Thread.sleep(3000);

        Select phoneCountryCodeSelect = new Select(
                driver.findElement(By.xpath("//select[@name='phoneCountryCodeISO']")));
        phoneCountryCodeSelect.selectByVisibleText("Canada");
        Thread.sleep(3000);

        WebElement addPhone = driver.findElement(By.xpath("//input[@aria-label='Cell phone number']"));
        JavascriptExecutor jsExecutor20 = (JavascriptExecutor) driver;
        jsExecutor20.executeScript("arguments[0].value='5149876543'", addPhone);

        WebElement addEmail = driver.findElement(By.xpath("//input[@aria-label='Email']"));
        JavascriptExecutor jsExecutor21 = (JavascriptExecutor) driver;
        jsExecutor21.executeScript("arguments[0].value='jmsmith@yahoo.com'", addEmail);

        Actions addTravProg = new Actions(driver);
        WebElement addTravProgBtn = driver
                .findElement(By.xpath("//div[contains(text(),'Add another rewards program')]"));
        addTravProg.moveToElement(addTravProgBtn).click();

        Select loyaltyProgSelect = new Select(driver.findElement(By.xpath("//select[@id=\"loyaltyPrograms.0.code\"]")));
        loyaltyProgSelect.selectByVisibleText("Air Canada - Aeroplan");
        Thread.sleep(3000);

        WebElement addRP = driver.findElement(By.xpath("//input[@aria-label='Rewards program number']"));
        JavascriptExecutor jsExecutor22 = (JavascriptExecutor) driver;
        jsExecutor22.executeScript("arguments[0].value='1234567890'", addRP);

        Actions saveComp = new Actions(driver);
        WebElement saveCompBtn = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[2]/div/section/div/div/div[7]/div/div[1]/button/div[1]/div"));
        saveComp.moveToElement(saveCompBtn).click();
    }

    @Test
    public void viewNotifTab() {

        Actions viewNotifTab = new Actions(driver);
        WebElement viewNotifTabBtn = driver.findElement(By.xpath("//button[contains(text(), 'Notifications')]"));
        viewNotifTab.moveToElement(viewNotifTabBtn).click();
    }

    @Test
    public void createFA() throws AWTException {

        Actions createPA = new Actions(driver);
        WebElement createPABtn = driver.findElement(By.xpath("//button[@aria-label='Create a Price Alert']"));
        createPA.moveToElement(createPABtn).click();

        Actions createFA = new Actions(driver);
        WebElement createFABtn = driver.findElement(By.xpath("//div[@aria-label='Flight']"));
        createFA.moveToElement(createFABtn).click();

        WebElement from = driver.findElement(By.xpath("//input[@aria-label='Flight origin input']"));
        JavascriptExecutor jsExecutor23 = (JavascriptExecutor) driver;
        jsExecutor23.executeScript("arguments[0].value='YUL'", from);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        WebElement to = driver.findElement(By.xpath("//input[@aria-label='To']"));
        JavascriptExecutor jsExecutor24 = (JavascriptExecutor) driver;
        jsExecutor24.executeScript("arguments[0].value='NYC'", to);
        Robot robot2 = new Robot();
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);

        Actions depart = new Actions(driver);
        WebElement departBtn = driver.findElement(
                By.xpath("/html/body/div[22]/div[3]/div/div/div/div[3]/form/div[2]/div/div[8]/div/div[1]/button[2]"));
        depart.moveToElement(departBtn).click();

        Actions rtrn = new Actions(driver);
        WebElement rtrnBtn = driver.findElement(
                By.xpath("/html/body/div[22]/div[3]/div/div/div/div[3]/form/div[2]/div/div[8]/div/div[3]/button[2]"));
        rtrn.moveToElement(rtrnBtn).click();
        rtrn.moveToElement(rtrnBtn).click();
        rtrn.moveToElement(rtrnBtn).click();
        rtrn.moveToElement(rtrnBtn).click();

        Actions mobile = new Actions(driver);
        WebElement mobileBtn = driver.findElement(By
                .xpath("/html/body/div[22]/div[3]/div/div/div/div[3]/form/div[2]/div/div[9]/div/div/div[1]/div/input"));
        mobile.moveToElement(mobileBtn).click();

        Actions saveFA = new Actions(driver);
        WebElement saveFABtn = driver
                .findElement(By.xpath("/html/body/div[22]/div[3]/div/div/div/div[3]/form/div[2]/div/div[10]/button"));
        saveFA.moveToElement(saveFABtn).click();

    }

    @Test
    public void createSA() throws AWTException {

        Actions createPA = new Actions(driver);
        WebElement createPABtn = driver.findElement(By.xpath("//button[@aria-label='Create a Price Alert']"));
        createPA.moveToElement(createPABtn).click();

        Actions createSA = new Actions(driver);
        WebElement createSABtn = driver.findElement(By.xpath("//div[@aria-label='Stay']"));
        createSA.moveToElement(createSABtn).click();

        WebElement whereTo = driver.findElement(By.xpath("//input[@aria-label='Where to?']"));
        JavascriptExecutor jsExecutor24 = (JavascriptExecutor) driver;
        jsExecutor24.executeScript("arguments[0].value='New York'", whereTo);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Actions checkIn = new Actions(driver);
        WebElement checkInBtn = driver
                .findElement(By.xpath("/html/body/div[25]/div[3]/div/div/div/div[3]/form/div[4]/div/div[1]/button[2]"));
        checkIn.moveToElement(checkInBtn).click();

        Actions checkOut = new Actions(driver);
        WebElement checkOutBtn = driver
                .findElement(By.xpath("/html/body/div[25]/div[3]/div/div/div/div[3]/form/div[4]/div/div[3]/button[2]"));
        checkOut.moveToElement(checkOutBtn).click();
        checkOut.moveToElement(checkOutBtn).click();
        checkOut.moveToElement(checkOutBtn).click();

        Actions mobile = new Actions(driver);
        WebElement mobileBtn = driver.findElement(
                By.xpath("/html/body/div[25]/div[3]/div/div/div/div[3]/form/div[5]/div/div/div[2]/div/input"));
        mobile.moveToElement(mobileBtn).click();

        Actions saveSA = new Actions(driver);
        WebElement saveSABtn = driver
                .findElement(By.xpath("/html/body/div[25]/div[3]/div/div/div/div[3]/form/div[6]/button"));
        saveSA.moveToElement(saveSABtn).click();

    }

    @Test
    public void createCA() throws AWTException {

        Actions createPA = new Actions(driver);
        WebElement createPABtn = driver.findElement(By.xpath("//button[@aria-label='Create a Price Alert']"));
        createPA.moveToElement(createPABtn).click();

        Actions createCA = new Actions(driver);
        WebElement createCABtn = driver.findElement(By.xpath("//div[@aria-label='Car']"));
        createCA.moveToElement(createCABtn).click();

        WebElement from = driver.findElement(By.xpath("//input[@aria-label='Pick-up location']"));
        JavascriptExecutor jsExecutor25 = (JavascriptExecutor) driver;
        jsExecutor25.executeScript("arguments[0].value='LGA'", from);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Actions fromCal = new Actions(driver);
        WebElement fromCalBtn = driver.findElement(By.xpath("//span[@aria-label='Start date calendar input']"));
        fromCal.moveToElement(fromCalBtn).click();

        Actions start = new Actions(driver);
        WebElement startBtn = driver
                .findElement(By.xpath("/html/body/div[36]/div/div[2]/div/div/div[2]/div[1]/div[2]/div[31]"));
        start.moveToElement(startBtn).click();

        Actions end = new Actions(driver);
        WebElement endBtn = driver
                .findElement(By.xpath("/html/body/div[36]/div/div[2]/div/div/div[2]/div[1]/div[2]/div[34]"));
        end.moveToElement(endBtn).click();

        Actions saveCA = new Actions(driver);
        WebElement saveCABtn = driver
                .findElement(By.xpath("/html/body/div[35]/div/div[3]/div/div/div[2]/section/article/div[4]/button"));
        saveCA.moveToElement(saveCABtn).click();
    }

    @Test
    public void toggleEmailPrefs() {

        Actions TH = new Actions(driver);
        WebElement THBtn = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[2]/div/div[3]/div[2]/div/input"));
        TH.moveToElement(THBtn).click();

        Actions SO = new Actions(driver);
        WebElement SOBtn = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[2]/div/div[4]/div[2]/div/input"));
        SO.moveToElement(SOBtn).click();

        Actions GG = new Actions(driver);
        WebElement GGBtn = driver.findElement(
                By.xpath("/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[2]/div/div[5]/div[2]/div/input"));
        GG.moveToElement(GGBtn).click();

    }

    @Test
    public void toggleTripNotifs() {

        Actions email = new Actions(driver);
        WebElement emailBtn = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[3]/div[1]/div/div[2]/div[1]/div[2]/input"));
        email.moveToElement(emailBtn).click();

        Actions sms = new Actions(driver);
        WebElement smsBtn = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[3]/div[1]/div/div[3]/div[1]/div[2]/input"));
        sms.moveToElement(smsBtn).click();

        Actions all = new Actions(driver);
        WebElement allBtn = driver.findElement(By.xpath(
                "/html/body/div[1]/div[1]/main/div[1]/section[2]/div/div[3]/div[1]/div/div[1]/div[2]/div/input"));
        all.moveToElement(allBtn).click();
    }

    @AfterTest
    public void quitFF() {
        driver.quit();
    }
}
