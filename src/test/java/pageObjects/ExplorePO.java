package pageObjects;

import org.openqa.selenium.WebDriver;

import utilities.elementsUtilities.WebUtilities;

public class ExplorePO {
	WebDriver driver;
    WebUtilities utils;

    public ExplorePO(WebDriver driver){
        this.driver = driver;
        utils = new WebUtilities(driver);
    }

	
}
