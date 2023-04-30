package tests.webTests.authentication;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import pageObjects.webPo.authenticationPo.POConnexion;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;

public class TestAuthentication extends BaseTestWeb {

    //****************************************** don't touch this section *************************************************
    public POConnexion webPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;


    @BeforeClass
    public void setupTestConnexionWeb(){
        webPage = new POConnexion(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
    }
    //****************************************** don't touch this section *************************************************


    //Create 1 method per test case bellow this section.
    //@Test
    public void test_001_authenticationInvalidUsername() throws Exception {
        webPage.clickSignInBtn();
        String continueWithEmailTxt = webPage.getTextContinueWithEmail();
        Assert.assertEquals( continueWithEmailTxt, stringsReader.readStringsXML("String Continue with email"));
        webPage.clickContinueWithEmailBtn();
        webPage.sendKeysUsernameMail(jsonReader.getStringJsonObject("invalidUser", "invalidUsername"));
        webPage.clickUsernameConnexionBtn();
        String wrongUsernameTxt = webPage.getTextErrorUsername();
        Assert.assertEquals(wrongUsernameTxt, stringsReader.readStringsXML("Error text wrong username"));
    }

    //@Test
    public void test_002_travelRestrictionValidCountry() throws IOException, InterruptedException {
        webPage.clickTravelRestrictionBtn();
        webPage.clickanywhereTravelTxt();
        webPage.sendKeysCountryInput(jsonReader.getStringJsonObject("countryTravelRestrictionsValid", "countryName"));
    }

}
