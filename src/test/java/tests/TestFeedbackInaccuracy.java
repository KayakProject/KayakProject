package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.BasePageObjects;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.commonPO.footerPo.POFeedbackInaccuracy;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;

public class TestFeedbackInaccuracy extends BaseTest {

    public BaseMobilePageObjects base;
    public BasePageObjects basePO;
    public POFeedbackInaccuracy feedbackPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    public SoftAssert softAssert;


    @Parameters({"platform"})
    @BeforeClass
    public void setupFeedbackInaccuracy(String platform){
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        softAssert = new SoftAssert();

        if(platform.equals("mobile")){
            base = new BaseMobilePageObjects(BaseTest.appiumDriver);
            feedbackPage = new POFeedbackInaccuracy(BaseTest.appiumDriver);
            basePO = new BasePageObjects(BaseTest.appiumDriver);
        }
        else if(platform.equals("web")){
            feedbackPage = new POFeedbackInaccuracy(BaseTest.driver);
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
    @Test (priority = 2)
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
    @Test (priority = 3)
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
    public void test_4_clickGiveFeedbackLink() throws Exception {
        String failMsg = "The feedback page does not open.";
        feedbackPage.clickFeedbackLink();
        Assert.assertEquals(feedbackPage.getTxtFeedbackPage(), stringsReader.readStringsXML("FI_04 assertion"), failMsg);
    }

    @Test (priority = 5, dependsOnMethods = {"test_4_clickGiveFeedbackLink"})
    public void test_5_enterInvalidEmail() throws Exception {
        String failMsg1 = "The error message about invalid email is not displayed.";
        String failMsg2 = "The color of the feedback button is not grey";
        feedbackPage.sendKeysInputMailFeedback(jsonReader.getStringJsonObject("feedback", "invalidEmail"));
        Assert.assertEquals(feedbackPage.getTxtWrongEmailFeedback(), stringsReader.readStringsXML("FI_05 assertion"), failMsg1);
        Assert.assertTrue(feedbackPage.getColorFeedbackBtn().contains(stringsReader.readStringsXML("FI_05 assertion2")), failMsg2);
    }

    @Test (priority = 6, dependsOnMethods = {"test_4_clickGiveFeedbackLink"})
    public void test_6_enterValidEmail() throws Exception {
        String failMsg = "The feedback button is not orange.";
        feedbackPage.sendKeysInputMailFeedback(jsonReader.getStringJsonObject("feedback", "validEmail"));
        Assert.assertTrue(feedbackPage.getColorFeedbackBtn().contains(stringsReader.readStringsXML("FI_06 assertion")), failMsg);
    }

    @Test (priority = 7, dependsOnMethods = {"test_4_clickGiveFeedbackLink"})
    public void test_7_enterName() throws IOException {
        feedbackPage.sendKeysInputName(jsonReader.getStringJsonObject("feedback", "name"));
    }

    @Test (priority = 8, dependsOnMethods = {"test_4_clickGiveFeedbackLink"})
    public void test_8_selectPriceInaccuracyTopic() throws Exception {
        String failMsg = "The selected topic is not the price inaccuracy feedback.";
        feedbackPage.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        Assert.assertEquals(feedbackPage.getTxtSelectedTopic(), stringsReader.readStringsXML("FI_08 assertion"), failMsg);
    }

    @Test (priority = 9, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_9_selectRadioBtnHigherPrice() throws InterruptedException {
        String failMsg = "The radio button is not selected.";
        feedbackPage.clickRadioBtn(0);
        Assert.assertTrue(feedbackPage.isRadioSelected(0), failMsg);
    }

    @Test (priority = 11, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_10_selectRadioBtnLowerPrice() throws InterruptedException {
        String failMsg = "The radio button is not selected.";
        feedbackPage.clickRadioBtn(1);
        Assert.assertTrue(feedbackPage.isRadioSelected(1), failMsg);
    }

    @Test (priority = 10, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_11_selectRadioBtnResult() throws InterruptedException {
        String failMsg = "The radio button is not selected.";
        feedbackPage.clickRadioBtn(2);
        Assert.assertTrue(feedbackPage.isRadioSelected(2), failMsg);
    }

    @Test (priority = 12, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_12_verifyCurrency() throws IOException, InterruptedException {
        String failMsg = "The currency input field is not displayed.";
        feedbackPage.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));

        softAssert.assertTrue(feedbackPage.getValueCurrencyInput().equals(jsonReader.getStringJsonObject("feedback", "currencyInput")));
        softAssert.assertTrue(feedbackPage.getValueCurrencyInput().equals(jsonReader.getStringJsonObject("feedback", "currencyWebsite")));
        softAssert.assertAll(failMsg);
    }

    @Test (priority = 13, dependsOnMethods = {"test_12_verifyCurrency"})
    public void test_13_enterPriceAmount() throws IOException {
        feedbackPage.sendKeysCurrency(jsonReader.getStringJsonObject("feedback", "priceAmount"));
    }

    @Test (priority = 14, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_14_enterMessage() throws Exception {
        feedbackPage.sendKeysMessageArea(jsonReader.getStringJsonObject("feedback", "msg"));
    }

    @Test (priority = 15, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_15_verifyLinkGuarantee() throws Exception {
        String failMsg = "The link about guarantees does not work.";
        feedbackPage.clickLinkGuarantee();
        feedbackPage.getTxtTitlePageGuarantees();
        Assert.assertEquals(feedbackPage.getTxtTitlePageGuarantees(), stringsReader.readStringsXML("FI_15 assertion"), failMsg);
    }

    @Parameters({"platform"})
    @Test (priority = 16, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_16_verifyUploadJpeg(String platform) throws Exception {
        if(platform.equals("mobile")){
            appiumDriver.navigate().back();
        }
        else{
            driver.navigate().back();
        }
        String failMsg = "Jpeg files cannot be uploaded.";
        feedbackPage.uploadScreenshot(jsonReader.getStringJsonObject("feedback", "imgJpgPath"));
        try{
            Assert.assertTrue(feedbackPage.getTextFileUploaded().contains(stringsReader.readStringsXML("FI_16 assertion")), failMsg);
        }catch(NullPointerException e){
            Assert.assertTrue(false, failMsg);
        }

    }

    @Test (priority = 17, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_17_verifyUploadPng() throws Exception {
        String failMsg = "Png files cannot be uploaded.";
        feedbackPage.uploadScreenshot(jsonReader.getStringJsonObject("feedback", "imgPngPath"));
        try{
            softAssert.assertTrue(feedbackPage.getTextFileUploaded().contains(stringsReader.readStringsXML("FI_17 assertion")), failMsg);
        }catch(NullPointerException e){
            Assert.assertTrue(false, failMsg);
        }
    }

    @Test (priority = 18, dependsOnMethods = {"test_8_selectPriceInaccuracyTopic"})
    public void test_18_clickSendBtn() {
        String failMsg = "Clicking on the send feedback button does not send the feedback form.";
        feedbackPage.clickFeedbackBtn();
        //No assertion is done here in order to not send a feedback to the Kayak team (the send feedback button is never clicked)
    }
}
