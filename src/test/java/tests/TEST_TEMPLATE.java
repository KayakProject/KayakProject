package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.commonPO.footerPo.POAffiliateNetwork;
import pageObjects.mobilePo.BaseMobilePageObjects;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;

public class TEST_TEMPLATE {

    public BaseMobilePageObjects base;
    public POAffiliateNetwork affiliatePage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    public SoftAssert softAssert;


    @Parameters({"platform"})
    @BeforeClass
    public void setupTestTemplate(String platform){
        if(platform.equals("mobile")){
            base = new BaseMobilePageObjects(BaseTest.appiumDriver);
            affiliatePage = new POAffiliateNetwork(BaseTest.appiumDriver);
            stringsReader = new StringsReader();
            jsonReader = new JSONReader();
            softAssert = new SoftAssert();
        }
        else if(platform.equals("web")){
            affiliatePage = new POAffiliateNetwork(BaseTest.driver);
            stringsReader = new StringsReader();
            jsonReader = new JSONReader();
            utils = new WebUtilities(BaseTest.driver);
            softAssert = new SoftAssert();
        }
    }

    @Parameters({"platform"})
    @Test
    public void testExample(String platform) throws Exception {
        //The first three methods are required. It opens the emulator, search Kayak on Google and click on the hamburger menu
        if(platform.equals("mobile")){
            base.clickGoogleSearchBox();
            base.switchContextHandle();
            base.clickNavMenu();
            base.clickAboutLink();
        }

        //The methods below come from your page object class. Here are some examples:
        affiliatePage.clickLinkAffiliates();
        affiliatePage.clickBtnJoinNow();
        affiliatePage.clickPersonalAccount();
        affiliatePage.clickContinueBtn();

        //The method below allows to use the data.json file
        //Explanation: in the affiliate page object, write in the registration email input an invalid email

        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "invalidEmail"));
        //The method below allows to use any String needed from the website to perform assertions
        //Explanation: check if the error message displayed in the page equals "Email address must be a valid format"
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(0), stringsReader.readStringsXML("AN_01 assertion"));

    }
}
