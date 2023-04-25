package tests.mobileTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class BaseTestMobile {

    //****************************************** don't touch this section *************************************************
    public static AppiumDriver appiumDriver;
    public Properties props;
    public InputStream inputStream;

    @BeforeClass
    public void setup() throws IOException {
        props = new Properties();
        String propFileName = "config.properties";
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        props.load(inputStream);

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("android")
                .setAutomationName(props.getProperty("androidAutomationName"))
                .setAppPackage(props.getProperty("androidAppPackage"))
                .setAppActivity(props.getProperty("androidAppActivity"))
                //.setUdid("emulator-5554")
                //.setAppWaitDuration(Duration.ofSeconds(40))
                .setApp(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + "app" + File.separator + "kayak.apk");

        URL url = new URL("http://localhost:4723/wd/hub");
        appiumDriver = new AppiumDriver(url, options);
    }


    //@AfterClass
    public void teardown(){
        appiumDriver.quit();
    }
    //****************************************** don't touch this section *************************************************
}
