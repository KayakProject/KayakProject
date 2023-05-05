package tests.webTests.footer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.BasePageObjects;
import pageObjects.webPo.footerPo.POBookingReceipt;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

public class TestBookingReceipt extends BaseTestWeb{

    public POBookingReceipt receiptPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    SoftAssert softAssert;
    public BasePageObjects basePO;


    @BeforeClass
    public void setupTestConnexionWeb(){
        receiptPage = new POBookingReceipt(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
        softAssert = new SoftAssert();
        basePO = new BasePageObjects(BaseTestWeb.driver);
    }

    @Test
    public void test_requestBookingReceipt() throws Exception {
        basePO.clickHelpLink();

        //Reference number section
        receiptPage.clickBookingLink();
        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_01 assertion"));

        receiptPage.clearReferenceNumber();
        receiptPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_02 assertion"));

        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        receiptPage.clickFindBookingBtn();
        Assert.assertEquals(receiptPage.getTxtNoBooking(), stringsReader.readStringsXML("RB_03 assertion"));

        receiptPage.clearReferenceNumber();
        receiptPage.clearTravellerName();
        receiptPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "referenceNb"));
        receiptPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerLastName"));
        receiptPage.clickFindBookingBtn();
        softAssert.assertEquals(receiptPage.getTxtNoBooking(), stringsReader.readStringsXML("RB_04 assertion"));


        //Credit card section
        receiptPage.clickCreditCardBooking();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardMoreNb"));
        receiptPage.clickTravellerPhoneInput();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_05 assertion"));

        receiptPage.clearCreditCard();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardLetters"));
        receiptPage.clickTravellerPhoneInput();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_06 assertion"));

        receiptPage.clearCreditCard();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_07 assertion"));

        receiptPage.clearCreditCard();
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtError(), stringsReader.readStringsXML("RB_08 assertion"));

        receiptPage.clearCreditCard();
        receiptPage.clearTravellerPhone();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
        receiptPage.clickFindBookingCreditBtn();
        Assert.assertEquals(receiptPage.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_09 assertion"));

        receiptPage.clearCreditCard();
        receiptPage.clearTravellerPhone();
        receiptPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "creditCard"));
        receiptPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerPhone"));
        receiptPage.clickFindBookingCreditBtn();
        softAssert.assertEquals(receiptPage.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_10 assertion"));


        //Email section
        receiptPage.clickEmailBooking();
        receiptPage.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "invalidEmail"));
        receiptPage.clickBtnBookingMail();
        Assert.assertEquals(receiptPage.getTxtMsgInvalidEmailBooking(), stringsReader.readStringsXML("RB_11 assertion"));

        receiptPage.clearEmailInput();
        receiptPage.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "validEmail"));
        receiptPage.clickBtnBookingMail();
        Assert.assertEquals(receiptPage.getTxtMsgValidEmailBooking(), stringsReader.readStringsXML("RB_12 assertion"));
        receiptPage.clickCloseBtnAlert();
    }

}
