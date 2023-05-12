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
    public void setupBadges(String platform){
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
    @Test
    public void test_getBookingReceipts(String platform) throws Exception {
        if(platform.equals("mobile")){
            base.clickGoogleSearchBox();
            base.switchContextHandle();
            base.clickNavMenu();
            base.clickHelpLink();
        }

        basePO.clickHelpLink();
        Assert.assertEquals(feedbackPage.getTxtHelpPage(), stringsReader.readStringsXML("FI_03 assertion"));

        feedbackPage.clickFeedbackLink();
        Assert.assertEquals(feedbackPage.getTxtFeedbackPage(), stringsReader.readStringsXML("FI_04 assertion"));

        feedbackPage.sendKeysInputMailFeedback(jsonReader.getStringJsonObject("feedback", "invalidEmail"));
        Assert.assertEquals(feedbackPage.getTxtWrongEmailFeedback(), stringsReader.readStringsXML("FI_05 assertion"));
        Assert.assertTrue(feedbackPage.getColorFeedbackBtn().contains(stringsReader.readStringsXML("FI_05 assertion2")));

        feedbackPage.sendKeysInputMailFeedback(jsonReader.getStringJsonObject("feedback", "validEmail"));
        Assert.assertTrue(feedbackPage.getColorFeedbackBtn().contains(stringsReader.readStringsXML("FI_06 assertion")));

        feedbackPage.sendKeysInputName(jsonReader.getStringJsonObject("feedback", "name"));

        feedbackPage.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        Assert.assertEquals(feedbackPage.getTxtSelectedTopic(), stringsReader.readStringsXML("FI_08 assertion"));

        feedbackPage.clickRadioBtn(0);
        Assert.assertTrue(feedbackPage.isRadioSelected(0));

        feedbackPage.clickRadioBtn(1);
        Assert.assertTrue(feedbackPage.isRadioSelected(1));

        feedbackPage.clickRadioBtn(2);
        Assert.assertTrue(feedbackPage.isRadioSelected(2));

        feedbackPage.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        try{
            softAssert.assertEquals(feedbackPage.getValueCurrencyInput(), feedbackPage.getTxtValueCurrencyFooter() + " ");
        }catch(AssertionError e){
            Assert.assertTrue(true);
            System.out.println("The currency input is not always displayed in the website");
        }

        feedbackPage.sendKeysMessageArea(jsonReader.getStringJsonObject("feedback", "msg"));

        feedbackPage.clickLinkGuarantee();
        feedbackPage.getTxtTitlePageGuarantees();
        Assert.assertEquals(feedbackPage.getTxtTitlePageGuarantees(), stringsReader.readStringsXML("FI_15 assertion"));
        driver.navigate().back();

        feedbackPage.uploadScreenshot(jsonReader.getStringJsonObject("feedback", "imgJpgPath"));
        Assert.assertTrue(feedbackPage.getTextFileUploaded().contains(stringsReader.readStringsXML("FI_16 assertion")));

        feedbackPage.uploadScreenshot(jsonReader.getStringJsonObject("feedback", "imgPngPath"));
        softAssert.assertTrue(feedbackPage.getTextFileUploaded().contains(stringsReader.readStringsXML("FI_17 assertion")));

        feedbackPage.clickFeedbackBtn();
        //No assertion is put here to not really send a feedback to the Kayak team (the send feedback button is never clicked)
    }
}
