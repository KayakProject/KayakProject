package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.commonPO.footerPo.POAffiliateNetwork;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;

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
    @Test (priority = 0)
    public void test_1(String platform) throws Exception {
        if (platform.equals("mobile")) {
            base.clickGoogleSearchBox();
            base.switchContextHandle();
            base.clickNavMenu();
            base.clickAboutLink();
        }

    }

    @Test (priority = 3)
    public void test_3_clickAffiliateLink() throws InterruptedException {
        affiliatePage.clickLinkAffiliates();
    }

    @Test (priority = 4)
    public void test_4_clickJoinNowBtn() throws InterruptedException {
        affiliatePage.clickBtnJoinNow();
    }

    @Test (priority = 5)
    public void test_5_choosePersonnalAccount() {
        affiliatePage.clickPersonalAccount();
    }

    @Test (priority = 6)
    public void test_6_clickContinueBtn() throws InterruptedException {
        affiliatePage.clickContinueBtn();
    }

    @Test (priority = 7)
    public void test_7_enterInvalidEmail() throws Exception {
        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "invalidEmail"));
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(0), stringsReader.readStringsXML("AN_01 assertion"));
    }

    @Test (priority = 8)
    public void test_8_enterValidEmail() {

    }

    @Test (priority = 9)
    public void test_9_enterPasswordShorter() throws Exception {
        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdLessThan12"));
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(1), stringsReader.readStringsXML("AN_04 assertion"));
    }

    @Test (priority = 10)
    public void test_10_enterPasswordNoSpecialChar() throws Exception {
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdNoSpecialCharacters"));
        Assert.assertEquals(affiliatePage.getTxtErrorPswdCharacters(), stringsReader.readStringsXML("AN_02 assertion"));
    }

    @Test (priority = 11)
    public void test_11_enterPasswordNoUppercase() throws Exception {
        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdNoUppercase"));
        Assert.assertEquals(affiliatePage.getTxtErrorPswdCharacters(), stringsReader.readStringsXML("AN_03 assertion"));
    }

    @Test (priority = 12)
    public void test_12_chooseCountry() throws InterruptedException, IOException {
        affiliatePage.clearRegistrationInputEmail();
        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"));
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validPwd"));
        affiliatePage.selectCountryRegistration();
    }

    @Test (priority = 13)
    public void test_13_checkTermsConditions() throws Exception {
        affiliatePage.clickTermsUse();
        affiliatePage.clickContinueBtn();
        Assert.assertEquals(affiliatePage.getTxtContactInfo(), stringsReader.readStringsXML("AN_05 assertion"));
        Assert.assertEquals(affiliatePage.getTxtContactEmail(), jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"));
    }

    @Test (priority = 14)
    public void test_14_clickContinueBtn() {
        affiliatePage.sendKeysFirstName("name");
        affiliatePage.clearFirstName();
        Assert.assertEquals(affiliatePage.getTxtError(), "First name is required");
    }

    @Test (priority = 15)
    public void test_15_enterName() throws InterruptedException {
        affiliatePage.sendKeysFirstName("name");
        affiliatePage.sendKeysMiddleName("name");
        affiliatePage.sendKeysLastName("name");
        affiliatePage.clearLastName();
        Assert.assertEquals(affiliatePage.getTxtError(), "Last name is required");
    }

    @Test (priority = 16)
    public void test_16_enterMiddleName() {

    }

    @Test (priority = 17)
    public void test_17_enterLastName() {
        affiliatePage.sendKeysLastName("name");
    }

    @Test (priority = 18)
    public void test_18_verifyEmail() {

    }

    @Test (priority = 19)
    public void test_19_checkGovernmentOrg() {
        affiliatePage.clickNoGovernment();
    }

    @Test (priority = 20)
    public void test_20_clickContinueBtn() throws InterruptedException {
        affiliatePage.clickContinueBtn();
    }

    @Test (priority = 21)
    public void test_21_enterUrl() {
        affiliatePage.sendKeysWebsite("websiteURL");
    }

    @Test (priority = 22)
    public void test_22_selectTrafficSource() {
        affiliatePage.checkSourceSEO();
    }

    @Test (priority = 23)
    public void test_23_selectRevenue() {
        affiliatePage.selectRevenue();
    }

    @Test (priority = 24)
    public void test_24_selectDescription() {
        affiliatePage.checkBusinessOther();
    }

    @Test (priority = 25)
    public void test_25_clickContinueBtn() throws InterruptedException {
        affiliatePage.clickContinueBtn();
    }

    @Test (priority = 26)
    public void test_26_clickSubmitBtn() {
        affiliatePage.clickSubmitBtn();
        softAssert.assertEquals("A message about a successful submission is visible", "A message about a successful submission is visible");
    }
}
