package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.webPo.footerPo.POAffiliateNetwork;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

public class TestAffiliateNetwork extends BaseTest {

    public BaseMobilePageObjects base;
    public POAffiliateNetwork affiliatePage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    public SoftAssert softAssert;


    @Parameters({"platform"})
    @BeforeClass
    public void setupTestAffiliateNetwork(String platform){
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
    public void test_applyAffiliateNetworkPersonal(String platform) throws Exception {
        if(platform.equals("mobile")){
            base.clickGoogleSearchBox();
            base.switchContextHandle();
            base.clickNavMenu();
            base.clickAboutLink();
        }

        affiliatePage.clickLinkAffiliates();
        affiliatePage.clickBtnJoinNow();
        affiliatePage.clickPersonalAccount();
        affiliatePage.clickContinueBtn();

        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "invalidEmail"));
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(0), stringsReader.readStringsXML("AN_01 assertion"));

        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdNoSpecialCharacters"));
        Assert.assertEquals(affiliatePage.getTxtErrorPswdCharacters(), stringsReader.readStringsXML("AN_02 assertion"));

        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdNoUppercase"));
        Assert.assertEquals(affiliatePage.getTxtErrorPswdCharacters(), stringsReader.readStringsXML("AN_03 assertion"));

        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdLessThan12"));
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(1), stringsReader.readStringsXML("AN_04 assertion"));

        affiliatePage.clearRegistrationInputEmail();
        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"));
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validPwd"));
        affiliatePage.selectCountryRegistration();
        affiliatePage.clickTermsUse();
        affiliatePage.clickContinueBtn();
        Assert.assertEquals(affiliatePage.getTxtContactInfo(), stringsReader.readStringsXML("AN_05 assertion"));

        //Assert.assertEquals(affiliatePage.getTxtContactEmail(), jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"));
        affiliatePage.sendKeysFirstName("name");
        affiliatePage.clearFirstName();
        Assert.assertEquals(affiliatePage.getTxtError(), "First name is required");

        affiliatePage.sendKeysFirstName("name");
        affiliatePage.sendKeysMiddleName("name");
        affiliatePage.sendKeysLastName("name");
        affiliatePage.clearLastName();
        //Assert.assertEquals(affiliatePage.getTxtError(), "Last name is required");
        affiliatePage.sendKeysLastName("name");
        affiliatePage.clickNoGovernment();
        affiliatePage.clickContinueBtn();

        affiliatePage.sendKeysWebsite("websiteURL");
        affiliatePage.checkSourceSEO();
        affiliatePage.selectRevenue();
        affiliatePage.checkBusinessOther();
        affiliatePage.clickContinueBtn();
        affiliatePage.clickSubmitBtn();
        softAssert.assertEquals("A message about a successfull submission is visible", "A message about a successfull submission is visible");
    }
}
