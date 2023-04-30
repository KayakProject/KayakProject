package tests.endToEnd;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.webPo.footerPo.POFooterContact;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

public class ScenarioBookingReceipt {
    public POFooterContact footerContact;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;


    @BeforeClass
    public void setupTestFindBooking(){
        footerContact = new POFooterContact(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
    }

    @Test
    public void test_scenario_01_FindBookingReceipt(){

    }
}
