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


public class AddTravellersEndToEnd {

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
    public void endToEndAddTrav() throws InterruptedException, AWTException {

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

        Actions viewTravTab = new Actions(driver);
        WebElement viewTravTabBtn = driver.findElement(By.xpath("//button[contains(text(), 'Travellers')]"));
        viewTravTab.moveToElement(viewTravTabBtn).click();

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
        jsExecutor19.executeScript("arguments[0].value='04/05/1977'", addDOB);

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
        jsExecutor21.executeScript("arguments[0].value='janesmith@yahoo.com'", addEmail);

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

    @AfterTest
    public void quitFF() {
        driver.quit();
    }
}