package tests;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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
    @Test (priority = 1)
    public void test_1_verifyBrowserUsed(String platform) throws InterruptedException {
        String failMsg = "The browser Chrome has not opened.";
        if(platform.equals("mobile")){
            base.clickGoogleSearchBox();
            Assert.assertTrue(browserUsedName.contains("chrome"), failMsg);
        }
        else{
            Assert.assertTrue(browserUsedName.equals("chrome"), failMsg);
        }
    }

    @Parameters({"platform"})
    @Test (priority = 2, dependsOnMethods = {"test_1_verifyBrowserUsed"})
    public void test_2_openKayakUrl(String platform) throws Exception {
        String failMsg = "The current Url is not the main page of Kayak application.";
        if (platform.equals("mobile")) {
            base.searchKayakUrl();
            base.switchContextHandle();
            Assert.assertTrue(appiumDriver.getCurrentUrl().equals("https://www.ca.kayak.com/"), failMsg);
        }
        else{
            Assert.assertTrue(driver.getCurrentUrl().equals("https://www.ca.kayak.com/"), failMsg);
        }
    }

    @Parameters ({"platform"})
    @Test (priority = 3, dependsOnMethods = {"test_2_openKayakUrl"})
    public void test_3_clickAffiliateLink(String platform) throws Exception {
        String failMsg = "The affiliate page does not open.";
        if(platform.equals("mobile")){
            base.clickNavMenu();
            base.clickAboutLink();
        }
        affiliatePage.clickLinkAffiliates();
        Assert.assertEquals(affiliatePage.getTxtTitleAffiliatePage(platform), stringsReader.readStringsXML("AN_3 assertion"), failMsg);
    }

    @Test (priority = 4, dependsOnMethods = {"test_3_clickAffiliateLink"})
    public void test_4_clickJoinNowBtn() throws Exception {
        String failMsg = "The affiliate page is not displayed.";
        try{
            affiliatePage.clickBtnJoinNow();
            Assert.assertEquals(affiliatePage.getTxtJoinNowBtn(), stringsReader.readStringsXML("AN_4 assertion"), failMsg);
        }catch(NullPointerException | NoSuchElementException |TimeoutException e){
            e.printStackTrace();
            Assert.assertTrue(false, failMsg);
        }
    }

    @Test (priority = 5, dependsOnMethods = {"test_4_clickJoinNowBtn"})
    public void test_5_choosePersonalAccount() {
        String failMsg = "The continue button is not clickable";
        affiliatePage.clickPersonalAccount();
        Assert.assertFalse(affiliatePage.getColorBtnContinue().contains("AN_5 assertion"), failMsg);
    }

    @Test (priority = 6, dependsOnMethods = {"test_4_clickJoinNowBtn"})
    public void test_6_clickContinueBtn() throws Exception {
        String failMsg = "The section contact information is not displayed.";
        affiliatePage.clickContinueBtn();
        Assert.assertEquals(affiliatePage.getTitleSection(), stringsReader.readStringsXML("AN_6 assertion"), failMsg);
    }

    @Test (priority = 7, dependsOnMethods = {"test_6_clickContinueBtn"})
    public void test_7_enterInvalidEmail() throws Exception {
        String failMsg = "No error message is displayed.";
        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "invalidEmail"));
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(0), stringsReader.readStringsXML("AN_01 assertion"), failMsg);
    }

    @Test (priority = 8, dependsOnMethods = {"test_6_clickContinueBtn"})
    public void test_8_enterValidEmail() throws IOException {
        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"));
    }

    @Test (priority = 9, dependsOnMethods = {"test_6_clickContinueBtn"})
    public void test_9_enterPasswordShorter() throws Exception {
        String failMsg = "The error message is not displayed";
        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdLessThan12"));
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(1), stringsReader.readStringsXML("AN_04 assertion"), failMsg);
    }

    @Test (priority = 10, dependsOnMethods = {"test_6_clickContinueBtn"})
    public void test_10_enterPasswordNoSpecialChar() throws Exception {
        String failMsg = "The error message is not displayed";
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdNoSpecialCharacters"));
        Assert.assertEquals(affiliatePage.getTxtErrorPswdCharacters(), stringsReader.readStringsXML("AN_02 assertion"), failMsg);
    }

    @Test (priority = 11, dependsOnMethods = {"test_6_clickContinueBtn"})
    public void test_11_enterPasswordNoUppercase() throws Exception {
        String failMsg = "The error message is not displayed.";
        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdNoUppercase"));
        Assert.assertEquals(affiliatePage.getTxtErrorPswdCharacters(), stringsReader.readStringsXML("AN_03 assertion"), failMsg);
    }

    @Test (priority = 12, dependsOnMethods = {"test_6_clickContinueBtn"})
    public void test_12_chooseCountry() throws InterruptedException, IOException {
        affiliatePage.clearRegistrationInputEmail();
        affiliatePage.clearRegistrationPassword();
        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"));
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validPwd"));
        affiliatePage.selectCountryRegistration();
    }

    @Test (priority = 13, dependsOnMethods = {"test_6_clickContinueBtn"})
    public void test_13_checkTermsConditions() throws Exception {
        String failMsg = "The section contact information is not displayed.";
        affiliatePage.clickTermsUse();
        affiliatePage.clickContinueBtn();
        Assert.assertEquals(affiliatePage.getTxtContactInfo(), stringsReader.readStringsXML("AN_05 assertion"), failMsg);
        Assert.assertEquals(affiliatePage.getTxtContactEmail(), jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"), failMsg);
    }

    @Test (priority = 14, dependsOnMethods = {"test_6_clickContinueBtn"})
    public void test_14_clickContinueBtn() throws InterruptedException {
        String failMsg = "The contact information section is not displayed.";
        affiliatePage.sendKeysFirstName("name");
        affiliatePage.clearFirstName();
        Assert.assertEquals(affiliatePage.getTxtError(), "First name is required", failMsg);
    }

    @Test (priority = 15, dependsOnMethods = {"test_14_clickContinueBtn"})
    public void test_15_enterName() throws InterruptedException, IOException {
        String failMsg = "The error message is not displayed.";
        affiliatePage.sendKeysFirstName(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "firstName"));
        affiliatePage.clearLastName();
        Assert.assertEquals(affiliatePage.getTxtError(), "First name is required", failMsg);
    }

    @Test (priority = 16, dependsOnMethods = {"test_14_clickContinueBtn"})
    public void test_16_enterMiddleName() throws IOException {
        affiliatePage.sendKeysMiddleName(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "middleName"));
    }

    @Test (priority = 17, dependsOnMethods = {"test_14_clickContinueBtn"})
    public void test_17_enterLastName() throws IOException, InterruptedException {
        String failMsg = "The error message is not displayed.";
        affiliatePage.sendKeysLastName(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "lastName"));
        Assert.assertEquals(affiliatePage.getTxtError(), "Last name is required", failMsg);
    }

    @Test (priority = 18, dependsOnMethods = {"test_14_clickContinueBtn"})
    public void test_18_verifyEmail() throws IOException {
        String failMsg = "The email displayed is not the same one entered in the basic information section.";
        Assert.assertEquals(affiliatePage.getTxtEmailContact(), jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"), failMsg);
    }

    @Test (priority = 19, dependsOnMethods = {"test_14_clickContinueBtn"})
    public void test_19_checkGovernmentOrg() {
        affiliatePage.clickNoGovernment();
    }

    @Test (priority = 20, dependsOnMethods = {"test_14_clickContinueBtn"})
    public void test_20_clickContinueBtn() throws Exception {
        String failMsg = "The website information section is not displayed.";
        affiliatePage.clickContinueBtn();
        Assert.assertEquals(affiliatePage.getTitleSection(), stringsReader.readStringsXML("AN_20 assertion"), failMsg);
    }

    @Test (priority = 21, dependsOnMethods = {"test_20_clickContinueBtn"})
    public void test_21_enterUrl() {
        affiliatePage.sendKeysWebsite("websiteURL");
    }

    @Test (priority = 22, dependsOnMethods = {"test_20_clickContinueBtn"})
    public void test_22_selectTrafficSource() {
        affiliatePage.checkSourceSEO();
    }

    @Test (priority = 23, dependsOnMethods = {"test_20_clickContinueBtn"})
    public void test_23_selectRevenue() {
        String failMsg = "The revenue dropdown cannot be selected.";
        try{
            affiliatePage.selectRevenue();
        }catch(NullPointerException | ElementClickInterceptedException e){
            Assert.assertTrue(false, failMsg);
        }
    }

    @Test (priority = 24, dependsOnMethods = {"test_20_clickContinueBtn"})
    public void test_24_selectDescription() {
        affiliatePage.checkBusinessOther();
    }

    @Test (priority = 25, dependsOnMethods = {"test_20_clickContinueBtn"})
    public void test_25_clickContinueBtn() throws Exception {
        String failMsg = "The review information section is not displayed.";
        affiliatePage.clickContinueBtn();
        Assert.assertEquals(affiliatePage.getTitleSection(), stringsReader.readStringsXML("AN_25 assertion"), failMsg);
    }

    @Test (priority = 26, dependsOnMethods = {"test_20_clickContinueBtn"})
    public void test_26_clickSubmitBtn() {
        String failMsg = "Clicking on the submit button didn't successfully sent the registration.";
        try{
            affiliatePage.clickSubmitBtn();
            softAssert.assertEquals("A message about a successful submission is visible", "A message about a successful submission is visible", failMsg);
        }catch(NoSuchElementException e){
            Assert.assertTrue(false, failMsg);
        }
    }
}
