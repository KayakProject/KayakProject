package tests.webTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

public class BaseTestWeb {

    //****************************************** don't touch this section *************************************************
    public static WebDriver driver;

    @Parameters({"browserName"})
    @BeforeSuite
    public void setup(@Optional String browserName){
        if(browserName.equals("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new FirefoxDriver(options);
        }
        else{
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            //options.addArguments("--headless=new"); //uncomment to stop displaying the browser
            driver = new ChromeDriver(options);
        }
        driver.get("https://www.ca.kayak.com/");
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void teardown() {
        driver.quit();
    }
    //****************************************** don't touch this section *************************************************
}
