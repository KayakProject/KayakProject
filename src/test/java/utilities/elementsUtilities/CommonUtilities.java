package utilities.elementsUtilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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


    public abstract WebElement waitForElement(By locator);


    //This method retrieves the current date of the day
    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    //This method selects an element from a dropdown list
    public WebElement selectDropDown(List<WebElement> element, String dropdownText){
        WebElement dropdownValue = null;
        for(WebElement e:element){
            String val=e.getText();
            if(val.equals(dropdownText)){
                dropdownValue = e;
                e.click();
                break;
            }
        }
        return dropdownValue;
    }


    //****************************************** don't touch this section *************************************************
}
