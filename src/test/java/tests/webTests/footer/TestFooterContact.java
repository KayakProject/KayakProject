package tests.webTests.footer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.webPo.footerPo.POFooterContact;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;

public class TestFooterContact extends BaseTestWeb{

    public POFooterContact footerContact;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;


    @BeforeClass
    public void setupTestConnexionWeb(){
        footerContact = new POFooterContact(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
    }

    //@Test
    public void test_FZ_001_searchKeywordFAQ() throws Exception {
        footerContact.clickHelpLink();
        footerContact.sendKeysSearchBoxFAQ(stringsReader.readStringsXML("FZ_001 data"));
        Assert.assertEquals(footerContact.getTxtCarRentalFAQ(), stringsReader.readStringsXML("FZ_001 assertion"));
    }

    //@Test
    public void test_FZ_002_searchBookingReferenceNumberOnly() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        footerContact.clickFindBookingBtn();
        Assert.assertEquals(footerContact.getTxtErrorEmptyInput(), stringsReader.readStringsXML("FZ_002 assertion"));
    }

    //@Test
    public void test_FZ_003_searchBookingTravellerNameOnly() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
        footerContact.clickFindBookingBtn();
        Assert.assertEquals(footerContact.getTxtErrorEmptyInput(), stringsReader.readStringsXML("FZ_003 assertion"));
    }

    //@Test
    public void test_FZ_004_searchBookingInvalidReferenceInvalidName() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerLastName"));
        footerContact.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "referenceNb"));
        footerContact.clickFindBookingBtn();
        Assert.assertEquals(footerContact.getTxtNoBooking(), stringsReader.readStringsXML("FZ_004 assertion"));
    }

    //@Test
    public void test_FZ_005_searchBookingValidReferenceValidName() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.sendKeysReferenceNumber(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerLastName"));
        footerContact.sendKeysTravellerName(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "referenceNb"));
        footerContact.clickFindBookingBtn();
        Assert.assertEquals(footerContact.getTxtNoBooking(), stringsReader.readStringsXML("FZ_005 assertion"));
    }

    //@Test
    public void test_FZ_006_searchBookingInvalidCardNbOnly() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.clickCreditCardBooking();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardMoreNb"));
        footerContact.clickTravellerPhoneInput();
        Assert.assertEquals(footerContact.getTxtErrorEmptyInput(), stringsReader.readStringsXML("FZ_006 assertion"));
    }

    //@Test
    public void test_FZ_007_searchBookingInvalidCardLettersOnly() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.clickCreditCardBooking();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCardLetters"));
        footerContact.clickTravellerPhoneInput();
        Assert.assertEquals(footerContact.getTxtErrorEmptyInput(), stringsReader.readStringsXML("FZ_007 assertion"));
    }

    //@Test
    public void test_FZ_008_searchBookingCardOnly() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.clickCreditCardBooking();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        footerContact.clickFindBookingCreditBtn();
        Assert.assertEquals(footerContact.getTxtErrorEmptyInput(), stringsReader.readStringsXML("FZ_008 assertion"));
    }

    //@Test
    public void test_FZ_009_searchBookingPhoneOnly() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.clickCreditCardBooking();
        footerContact.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
        footerContact.clickFindBookingCreditBtn();
        Assert.assertEquals(footerContact.getTxtErrorEmptyInput(), stringsReader.readStringsXML("FZ_009 assertion"));
    }

    //@Test
    public void test_FZ_010_searchBookingInvalidCardInvalidPhone() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.clickCreditCardBooking();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "creditCard"));
        footerContact.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "travellerPhone"));
        footerContact.clickFindBookingCreditBtn();
        Assert.assertEquals(footerContact.getTxtInvalidCredit(), stringsReader.readStringsXML("FZ_010 assertion"));
    }

    //@Test
    public void test_FZ_011_searchBookingValidCardValidPhone() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.clickCreditCardBooking();
        footerContact.sendKeysCreditCardInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "creditCard"));
        footerContact.sendKeysTravellerPhone(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "travellerPhone"));
        footerContact.clickFindBookingCreditBtn();
        Assert.assertEquals(footerContact.getTxtInvalidCredit(), stringsReader.readStringsXML("FZ_011 assertion"));
    }

    //@Test
    public void test_FZ_012_searchBookingInvalidEmail() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.clickEmailBooking();
        footerContact.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "invalidRequest", "invalidEmail"));
        footerContact.clickBtnBookingMail();
        Assert.assertEquals(footerContact.getTxtMsgInvalidEmailBooking(), stringsReader.readStringsXML("FZ_012 assertion"));
    }

    //@Test
    public void test_FZ_013_sendBookingReceiptValidEmail() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickBookingLink();
        footerContact.clickEmailBooking();
        footerContact.sendKeysEmailInput(jsonReader.getStringJsonObject("bookingReceipt", "validRequest", "validEmail"));
        footerContact.clickBtnBookingMail();
        Assert.assertEquals(footerContact.getTxtMsgValidEmailBooking(), stringsReader.readStringsXML("FZ_013 assertion"));
        footerContact.clickCloseBtnAlert();
    }

    //@Test
    public void test_FZ_014_sendFeedbackInvalidEmail() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickFeedbackLink();
        footerContact.sendKeysInputMailFeedback(jsonReader.getStringJsonObject("feedback", "invalidEmail"));
        Assert.assertEquals(footerContact.getTxtWrongEmailFeedback(), stringsReader.readStringsXML("FZ_014 assertion"));
    }

    //@Test
    public void test_FZ_015_priceFeedbackVerifySelectedTopic() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickFeedbackLink();
        footerContact.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        Assert.assertEquals(footerContact.getTxtSelectedTopic(), stringsReader.readStringsXML("FZ_015 assertion"));
    }

    //@Test
    public void test_FZ_016_priceFeedbackVerifyRadioBtnSelected() throws IOException, InterruptedException {
        footerContact.clickHelpLink();
        footerContact.clickFeedbackLink();
        footerContact.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        footerContact.clickRadioBtn(0);
        Assert.assertTrue(footerContact.isRadioSelected(0));
        footerContact.clickRadioBtn(1);
        Assert.assertTrue(footerContact.isRadioSelected(1));
        footerContact.clickRadioBtn(2);
        Assert.assertTrue(footerContact.isRadioSelected(2));
    }

    //@Test
    public void test_FZ_017_priceFeedbackUserCurrencyDisplayed() throws IOException, InterruptedException {
        footerContact.clickHelpLink();
        footerContact.clickFeedbackLink();
        footerContact.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        Assert.assertEquals(footerContact.getValueCurrencyInput(), footerContact.getTxtValueCurrencyFooter() + " ");
    }

    //@Test
    public void test_FZ_018_priceFeedbackloadJpegImg(){

    }

    //@Test
    public void test_FZ_019_priceFeedbackSubmitSuccessful(){

    }

    //@Test
    public void test_FZ_020_redirectUserToBuyAd() throws Exception {
        footerContact.clickHelpLink();
        footerContact.clickFeedbackLink();
        footerContact.selectTopicFeedback(stringsReader.readStringsXML("FZ_020 data"));
        footerContact.clickContactBtnUsBuyAd();
        Assert.assertEquals(footerContact.getTxtTitlePartnerPage(), stringsReader.readStringsXML("FZ_020 assertion"));
    }

    //@Test
    public void test_FZ_021_registrationInvalidEmail() throws Exception {
        footerContact.clickLinkAffiliates();
        footerContact.clickBtnJoinNow();
        footerContact.clickPersonalAccount();
        footerContact.clickContinueBtn();
        footerContact.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "invalidEmail"));
        Assert.assertEquals(footerContact.getTxtErrorMsgRegistration(0), stringsReader.readStringsXML("FZ_021 assertion"));
    }

    @Test
    public void test_FZ_022_registrationPasswordNoSpecialCharacters() throws Exception {
        footerContact.clickLinkAffiliates();
        footerContact.clickBtnJoinNow();
        footerContact.clickPersonalAccount();
        footerContact.clickContinueBtn();
        footerContact.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "invalidEmail"));
        Assert.assertEquals(footerContact.getTxtErrorMsgRegistration(1), stringsReader.readStringsXML("FZ_021 assertion"));
    }

}
