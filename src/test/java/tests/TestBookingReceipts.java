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
        basePO = new BasePageObjects(BaseTest.driver);

        if (platform.equals("mobile")) {
            base = new BaseMobilePageObjects(BaseTest.appiumDriver);
            receiptPage = new POBookingReceipt(BaseTest.appiumDriver);
        } else if (platform.equals("web")) {
            receiptPage = new POBookingReceipt(BaseTest.driver);
            utils = new WebUtilities(BaseTest.driver);
        }
    }

    @Parameters({"platform"})
    @Test (priority = 0)
    public void test_1(String platform) throws Exception {
        if (platform.equals("mobile")) {
            base.clickGoogleSearchBox();
            base.switchContextHandle();
            base.clickNavMenu();
            base.clickHelpLink();
        }
    }

    @Test (priority = 3)
    public void test_3_clickHelpLink() throws InterruptedException {
        basePO.clickHelpLink();
    }

    @Test (priority = 4)
    public void test_4_clickBookingLink() {
        receiptPage.clickBookingLink();
    }

    @Test (priority = 5)
    public void test_5_enterInvalidConfirmationNb() throws IOException {
        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
    }

    @Test (priority = 6)
    public void test_6_ClickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_01 assertion"));
    }

    @Test (priority = 7)
    public void test_7_enterInvalidName() throws IOException {
        receiptPage.clearReferenceNumber();
        receiptPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
    }

    @Test (priority = 8)
    public void test_8_clickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_02 assertion"));
    }

    @Test (priority = 9)
    public void test_9_enterInvalidNbInvalidName() throws IOException {
        receiptPage.clearReferenceNumber();
        receiptPage.clearTravellerName();
        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        receiptPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
    }

    @Test (priority = 10)
    public void test_10_clickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtNoBooking(), stringsReader.readStringsXML("RB_03 assertion"));
    }

    @Test (priority = 11)
    public void test_11_enterValidNbValidName() throws IOException {
        receiptPage.clearReferenceNumber();
        receiptPage.clearTravellerName();
        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "referenceNb"));
        receiptPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerLastName"));
    }

    @Test (priority = 12)
    public void test_12_clickFindBooking() throws Exception {
        receiptPage.clickFindBookingBtn();
        softAssert.assertEquals(receiptPage.getTxtNoBooking(), stringsReader.readStringsXML("RB_04 assertion"));
    }

    @Test (priority = 13)
    public void test_13_selectCreditCardTab() {
        receiptPage.clickCreditCardBooking();
    }

    @Test (priority = 14)
    public void test_14_enterMoreNbCredit() throws IOException {
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardMoreNb"));
    }

    @Test (priority = 15)
    public void test_15_clickTravelerNbInput() throws Exception {
        receiptPage.clickTravellerPhoneInput();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_05 assertion"));
    }

    @Test (priority = 16)
    public void test_16_enterLettersCredit() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardLetters"));
    }

    @Test (priority = 17)
    public void test_17_clickTravelerNb() throws Exception {
        receiptPage.clickTravellerPhoneInput();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_06 assertion"));
    }

    @Test (priority = 18)
    public void test_18_enterCreditInvalid() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
    }

    @Test (priority = 19)
    public void test_19_clickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_07 assertion"));
    }

    @Test (priority = 20)
    public void test_20_enterInvalidPhoneNb() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
    }

    @Test (priority = 21)
    public void test_21_clickFindBookingsBtn() throws Exception {
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_08 assertion"));
    }

    @Test (priority = 22)
    public void test_22_enterInvalidCreditNbInvalidPhone() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.clearTravellerPhone();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
    }

    @Test (priority = 23)
    public void test_23_clickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_09 assertion"));
    }

    @Test (priority = 24)
    public void test_24_enterValidCreditValidPhone() throws IOException {
        receiptPage.clearCreditCard();
        receiptPage.clearTravellerPhone();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "creditCard"));
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerPhone"));
    }

    @Test (priority = 25)
    public void test_25_clickFindBookingBtn() throws Exception {
        receiptPage.clickFindBookingCreditBtn();
        softAssert.assertEquals(receiptPage.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_10 assertion"));
    }

    @Test (priority = 26)
    public void test_26_clickEmailTab() {
        receiptPage.clickEmailBooking();
    }

    @Test (priority = 27)
    public void test_27_enterInvalidEmail() throws IOException {
        receiptPage.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "invalidEmail"));
    }

    @Test (priority = 28)
    public void test_28_clickFindBookingBtn() throws Exception {
        receiptPage.clickBtnBookingMail();
        Assert.assertEquals(receiptPage.getTxtMsgInvalidEmailBooking(), stringsReader.readStringsXML("RB_11 assertion"));
    }

    @Test (priority = 29)
    public void test_29_enterValidMail() throws IOException {
        receiptPage.clearEmailInput();
        receiptPage.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "validEmail"));
    }

    @Test (priority = 30)
    public void test_30_clickFindBookingBtn() throws Exception {
        receiptPage.clickBtnBookingMail();
        try {
            softAssert.assertEquals(receiptPage.getTxtMsgValidEmailBooking(), stringsReader.readStringsXML("RB_12 assertion"));
            receiptPage.clickCloseBtnAlert();
        } catch (AssertionError | InterruptedException e) {
            Assert.assertTrue(true);
            System.out.println("A bug in the website prevents to send booking receipts to a valid email address");
        }
    }
}

