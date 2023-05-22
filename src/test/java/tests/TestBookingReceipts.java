package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.BasePageObjects;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.commonPO.footerPo.POBookingReceipt;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;

public class TestBookingReceipts extends BaseTest {

    public BaseMobilePageObjects base;
    public BasePageObjects basePO;
    public POBookingReceipt receiptPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    public SoftAssert softAssert;


    @Parameters({"platform"})
    @BeforeClass
    public void setupBadges(String platform) {
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        softAssert = new SoftAssert();

        if (platform.equals("mobile")) {
            base = new BaseMobilePageObjects(BaseTest.appiumDriver);
            receiptPage = new POBookingReceipt(BaseTest.appiumDriver);
            basePO = new BasePageObjects(BaseTest.appiumDriver);
        } else if (platform.equals("web")) {
            receiptPage = new POBookingReceipt(BaseTest.driver);
            utils = new WebUtilities(BaseTest.driver);
            basePO = new BasePageObjects(BaseTest.driver);
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

    @Parameters({"platform"})
    @Test (priority = 3, dependsOnMethods = {"test_2_openKayakUrl"})
    public void test_3_clickHelpLink(String platform) throws Exception {
        String failMsg = "User cannot click on Help link.";
        if (platform.equals("mobile")) {
            base.clickNavMenu();
            base.clickHelpLink();
            Assert.assertEquals(base.getTxtHelpPage(), stringsReader.readStringsXML("FI_03 assertion"), failMsg);
        }
        else{
            basePO.clickHelpLink();
            Assert.assertEquals(basePO.getTxtHelpPage(), stringsReader.readStringsXML("FI_03 assertion"), failMsg);
        }
    }

    @Test (priority = 4, dependsOnMethods = {"test_3_clickHelpLink"})
    public void test_4_clickBookingLink() throws Exception {
        String failMsg = "The booking receipt page is not displayed";
        receiptPage.clickBookingLink();
        Assert.assertEquals(receiptPage.getTitleBookingPage(), stringsReader.readStringsXML("RB_4 assertion"), failMsg);
    }

    @Test (priority = 5, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_5_enterInvalidConfirmationNb() throws IOException {
        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
    }

    @Test (priority = 6, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_6_ClickFindBookingBtn() throws Exception {
        String failMsg = "The expected error message is not displayed";
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_01 assertion"), failMsg);
    }

    @Test (priority = 7, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_7_enterInvalidName() throws IOException {
        receiptPage.clearReferenceNumber();
        receiptPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
    }

    @Test (priority = 8, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_8_clickFindBookingBtn() throws Exception {
        String failMsg = "The expected error message is not displayed";
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_02 assertion"), failMsg);
    }

    @Test (priority = 9, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_9_enterInvalidNbInvalidName() throws IOException {
        receiptPage.clearReferenceNumber();
        receiptPage.clearTravellerName();
        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        receiptPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
    }

    @Test (priority = 10, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_10_clickFindBookingBtn() throws Exception {
        String failMsg = "The expected error message is not displayed";
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtNoBooking(), stringsReader.readStringsXML("RB_03 assertion"), failMsg);
    }

    @Test (priority = 11, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_11_enterValidNbValidName() throws IOException {
        receiptPage.clearReferenceNumber();
        receiptPage.clearTravellerName();
        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "referenceNb"));
        receiptPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerLastName"));
    }

    @Test (priority = 12, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_12_clickFindBooking() throws Exception {
        String failMsg = "The expected error message is not displayed";
        receiptPage.clickFindBookingBtn();
        softAssert.assertEquals(receiptPage.getTxtNoBooking(), stringsReader.readStringsXML("RB_04 assertion"), failMsg);
    }

    @Test (priority = 13, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_13_selectCreditCardTab() {
        String failMsg = "The credit card tab is not selected";
        receiptPage.clickCreditCardBooking();
        Assert.assertTrue(receiptPage.isCreditSectionSelected());
    }

    @Test (priority = 14, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_14_enterMoreNbCredit() throws IOException {
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardMoreNb"));
    }

    @Test (priority = 15, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_15_clickTravelerNbInput() throws Exception {
        receiptPage.clickTravellerPhoneInput();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_05 assertion"));
    }

    @Test (priority = 16, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_16_enterLettersCredit() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardLetters"));
    }

    @Test (priority = 17, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_17_clickTravelerNb() throws Exception {
        receiptPage.clickTravellerPhoneInput();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_06 assertion"));
    }

    @Test (priority = 18, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_18_enterCreditInvalid() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
    }

    @Test (priority = 19, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_19_clickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_07 assertion"));
    }

    @Test (priority = 20, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_20_enterInvalidPhoneNb() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
    }

    @Test (priority = 21, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_21_clickFindBookingsBtn() throws Exception {
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_08 assertion"));
    }

    @Test (priority = 22, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_22_enterInvalidCreditNbInvalidPhone() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.clearTravellerPhone();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
    }

    @Test (priority = 23, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_23_clickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_09 assertion"));
    }

    @Test (priority = 24, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_24_enterValidCreditValidPhone() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.clearTravellerPhone();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "creditCard"));
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerPhone"));
    }

    @Test (priority = 25, dependsOnMethods = {"test_13_selectCreditCardTab"})
    public void test_25_clickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingCreditBtn();
        softAssert.assertEquals(receiptPage.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_10 assertion"));
    }

    @Test (priority = 26, dependsOnMethods = {"test_4_clickBookingLink"})
    public void test_26_clickEmailTab() {
        receiptPage.clickEmailBooking();
    }

    @Test (priority = 27, dependsOnMethods = {"test_26_clickEmailTab"})
    public void test_27_enterInvalidEmail() throws IOException {
        receiptPage.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "invalidEmail"));
    }

    @Test (priority = 28, dependsOnMethods = {"test_26_clickEmailTab"})
    public void test_28_clickFindBookingBtn() throws Exception {
        receiptPage.clickBtnBookingMail();
        Assert.assertEquals(receiptPage.getTxtMsgInvalidEmailBooking(), stringsReader.readStringsXML("RB_11 assertion"));
    }

    @Test (priority = 29, dependsOnMethods = {"test_26_clickEmailTab"})
    public void test_29_enterValidMail() throws IOException {
        receiptPage.clearEmailInput();
        receiptPage.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "validEmail"));
    }

    @Test (priority = 30, dependsOnMethods = {"test_26_clickEmailTab"})
    public void test_30_clickFindBookingBtn() throws Exception {
        String failMsg = "No email was sent to the user email.";
        receiptPage.clickBtnBookingMail();
        Assert.assertEquals(receiptPage.getTxtMsgValidEmailBooking(), stringsReader.readStringsXML("RB_12 assertion"), failMsg);
        receiptPage.clickCloseBtnAlert();
    }
}

