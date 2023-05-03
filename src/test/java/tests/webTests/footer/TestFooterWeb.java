package tests.webTests.footer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.webPo.footerPo.POFooterContact;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

public class TestFooterWeb extends BaseTestWeb{

    public POFooterContact footerContact;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    SoftAssert softAssert;


    @BeforeClass
    public void setupTestConnexionWeb(){
        footerContact = new POFooterContact(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
        softAssert = new SoftAssert();
    }


    @Test
    public void test_01_requestBookingReceipt() throws Exception {
        footerContact.clickHelpLink();

        //Reference number section
        footerContact.clickBookingLink();
        footerContact.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        footerContact.clickFindBookingBtn();
        Assert.assertEquals(footerContact.getTxtError(), stringsReader.readStringsXML("RB_01 assertion"));

        footerContact.clearReferenceNumber();
        footerContact.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
        footerContact.clickFindBookingBtn();
        Assert.assertEquals(footerContact.getTxtError(), stringsReader.readStringsXML("RB_02 assertion"));

        footerContact.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        footerContact.clickFindBookingBtn();
        Assert.assertEquals(footerContact.getTxtNoBooking(), stringsReader.readStringsXML("RB_03 assertion"));

        footerContact.clearReferenceNumber();
        footerContact.clearTravellerName();
        footerContact.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "referenceNb"));
        footerContact.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerLastName"));
        footerContact.clickFindBookingBtn();
        softAssert.assertEquals(footerContact.getTxtNoBooking(), stringsReader.readStringsXML("RB_04 assertion"));


        //Credit card section
        footerContact.clickCreditCardBooking();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardMoreNb"));
        footerContact.clickTravellerPhoneInput();
        Assert.assertEquals(footerContact.getTxtError(), stringsReader.readStringsXML("RB_05 assertion"));

        footerContact.clearCreditCard();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardLetters"));
        footerContact.clickTravellerPhoneInput();
        Assert.assertEquals(footerContact.getTxtError(), stringsReader.readStringsXML("RB_06 assertion"));

        footerContact.clearCreditCard();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        footerContact.clickFindBookingCreditBtn();
        Assert.assertEquals(footerContact.getTxtError(), stringsReader.readStringsXML("RB_07 assertion"));

        footerContact.clearCreditCard();
        footerContact.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
        footerContact.clickFindBookingCreditBtn();
        Assert.assertEquals(footerContact.getTxtError(), stringsReader.readStringsXML("RB_08 assertion"));

        footerContact.clearCreditCard();
        footerContact.clearTravellerPhone();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        footerContact.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
        footerContact.clickFindBookingCreditBtn();
        Assert.assertEquals(footerContact.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_09 assertion"));

        footerContact.clearCreditCard();
        footerContact.clearTravellerPhone();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "creditCard"));
        footerContact.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerPhone"));
        footerContact.clickFindBookingCreditBtn();
        softAssert.assertEquals(footerContact.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_10 assertion"));

        footerContact.clickEmailBooking();
        footerContact.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "invalidEmail"));
        footerContact.clickBtnBookingMail();
        Assert.assertEquals(footerContact.getTxtMsgInvalidEmailBooking(), stringsReader.readStringsXML("RB_11 assertion"));

        footerContact.clearEmailInput();
        footerContact.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "validEmail"));
        footerContact.clickBtnBookingMail();
        Assert.assertEquals(footerContact.getTxtMsgValidEmailBooking(), stringsReader.readStringsXML("RB_12 assertion"));
        footerContact.clickCloseBtnAlert();
    }
}
