package utilities.elementsUtilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class CommonUtilities {

    //****************************************** don't touch this section *************************************************
    WebDriver driver;
    AppiumDriver appiumDriver;

    public CommonUtilities(WebDriver driver){
        this.driver = driver;
    }

    public CommonUtilities(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }


    public abstract WebElement waitForElement(WebElement element);

    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    //****************************************** don't touch this section *************************************************
}
