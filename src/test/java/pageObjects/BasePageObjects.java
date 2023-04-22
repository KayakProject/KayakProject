package pageObjects;

import org.openqa.selenium.WebDriver;
import utilities.elementsUtilities.WebUtilities;

public class BasePageObjects {

    //****************************************** don't touch this section *************************************************
    WebDriver driver;
    WebUtilities utils;

    public BasePageObjects(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }
    //****************************************** don't touch this section *************************************************
}
