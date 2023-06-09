package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;
    public static AppiumDriver appiumDriver;
    public Properties props;
    public InputStream inputStream;
    public static boolean isMobile = false;
    public String browserUsedName;

    @Parameters({"browserName", "platform"})
    @BeforeClass
    public void setup(@Optional String browserName, String platform) throws IOException {
        if(platform.equals("mobile")){
            isMobile = true;
            props = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("android")
                    .setAutomationName(props.getProperty("androidAutomationName"))
                    .setAppPackage(props.getProperty("androidAppPackage"))
                    .setAppActivity(props.getProperty("androidAppActivity"))
                    .setAvdLaunchTimeout(Duration.ofSeconds(5000));

            URL url = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AndroidDriver(url, options);
            browserUsedName = options.getAppActivity().toString();
            System.out.println(browserUsedName);
        }
        else{
            if(browserName.equals("firefox")){
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new FirefoxDriver(options);
                browserUsedName = options.getBrowserName();
            }
            else{
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--headless=new"); //uncomment to stop displaying the browser
                driver = new ChromeDriver(options);
                browserUsedName = options.getBrowserName(); //This line verifies the name of the browser used during tests
            }
            driver.get("https://www.ca.kayak.com/");
            driver.manage().window().maximize();
        }
    }

    public static boolean isDriverMobile(){
        return isMobile;
    }

    public WebDriver getDriver(){
        return driver;
    }

    @Parameters({"platform"})
    @AfterClass
    public void teardown(String platform) {
        if(platform.equals("web")){
            driver.quit();
        }
        else{
            appiumDriver.quit();
        }
    }
}
