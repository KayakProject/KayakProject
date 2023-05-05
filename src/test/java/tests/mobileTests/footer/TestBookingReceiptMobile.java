package tests.mobileTests.footer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.mobilePo.POBadges;
import tests.mobileTests.BaseTestMobile;
import utilities.JSONReader;
import utilities.StringsReader;

public class TestBookingReceiptMobile {

    BaseMobilePageObjects base;
    POBadges badgesPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    SoftAssert softAssert;

    @BeforeClass
    public void setupTestConnexionWeb(){
        base = new BaseMobilePageObjects(BaseTestMobile.appiumDriver);
        badgesPage = new POBadges(BaseTestMobile.appiumDriver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        softAssert = new SoftAssert();
    }

    //@Test
    public void test1() throws Exception {
        base.clickGoogleSearchBox();
        Assert.assertTrue(base.getUrl().contains(stringsReader.readStringsXML("FM_1 assertion")));
    }

    //@Test
    public void test2() throws Exception {
        base.clickGoogleSearchBox();
        badgesPage.clickNavMenuBtn();
        badgesPage.clickHelpLinkMenu();
        badgesPage.clickFindBookingsBtn();
        badgesPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        badgesPage.clickFindBookingBtn();
        Assert.assertEquals(badgesPage.getTxtError(), stringsReader.readStringsXML("RB_01 assertion"));

        badgesPage.clearReferenceNumber();
        badgesPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
        badgesPage.clickFindBookingBtn();
        Assert.assertEquals(badgesPage.getTxtError(), stringsReader.readStringsXML("RB_02 assertion"));

        badgesPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        badgesPage.clickFindBookingBtn();
        Assert.assertEquals(badgesPage.getTxtNoBooking(), stringsReader.readStringsXML("RB_03 assertion"));

        badgesPage.clearReferenceNumber();
        badgesPage.clearTravellerName();
        badgesPage.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "referenceNb"));
        badgesPage.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerLastName"));
        badgesPage.clickFindBookingBtn();
        softAssert.assertEquals(badgesPage.getTxtNoBooking(), stringsReader.readStringsXML("RB_04 assertion"));


        //Credit card section
        badgesPage.clickCreditCardBooking();
        badgesPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardMoreNb"));
        badgesPage.clickTravellerPhoneInput();
        Assert.assertEquals(badgesPage.getTxtError(), stringsReader.readStringsXML("RB_05 assertion"));

        badgesPage.clearCreditCard();
        badgesPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardLetters"));
        badgesPage.clickTravellerPhoneInput();
        Assert.assertEquals(badgesPage.getTxtError(), stringsReader.readStringsXML("RB_06 assertion"));

        badgesPage.clearCreditCard();
        badgesPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        badgesPage.clickFindBookingCreditBtn();
        Assert.assertEquals(badgesPage.getTxtError(), stringsReader.readStringsXML("RB_07 assertion"));

        badgesPage.clearCreditCard();
        badgesPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
        badgesPage.clickFindBookingCreditBtn();
        Assert.assertEquals(badgesPage.getTxtError(), stringsReader.readStringsXML("RB_08 assertion"));

        badgesPage.clearCreditCard();
        badgesPage.clearTravellerPhone();
        badgesPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        badgesPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
        badgesPage.clickFindBookingCreditBtn();
        Assert.assertEquals(badgesPage.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_09 assertion"));

        badgesPage.clearCreditCard();
        badgesPage.clearTravellerPhone();
        badgesPage.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "creditCard"));
        badgesPage.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerPhone"));
        badgesPage.clickFindBookingCreditBtn();
        softAssert.assertEquals(badgesPage.getTxtInvalidCredit(), stringsReader.readStringsXML("RB_10 assertion"));

        badgesPage.clickEmailBooking();
        badgesPage.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "invalidEmail"));
        badgesPage.clickBtnBookingMail();
        Assert.assertEquals(badgesPage.getTxtMsgInvalidEmailBooking(), stringsReader.readStringsXML("RB_11 assertion"));

        badgesPage.clearEmailInput();
        badgesPage.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "validEmail"));
        badgesPage.clickBtnBookingMail();
        Assert.assertEquals(badgesPage.getTxtMsgValidEmailBooking(), stringsReader.readStringsXML("RB_12 assertion"));
        badgesPage.clickCloseBtnAlert();
    }

}
