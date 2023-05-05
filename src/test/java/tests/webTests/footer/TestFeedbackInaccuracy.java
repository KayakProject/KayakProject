package tests.webTests.footer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.BasePageObjects;
import pageObjects.webPo.footerPo.POFeedbackInaccuracy;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;

public class TestFeedbackInaccuracy {

    public POFeedbackInaccuracy feedbackInaccuracyPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    public SoftAssert softAssert;
    public BasePageObjects baseObject;


    @BeforeClass
    public void setupTestConnexionWeb(){
        feedbackInaccuracyPage = new POFeedbackInaccuracy(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
        softAssert = new SoftAssert();
        baseObject = new BasePageObjects(BaseTestWeb.driver);
    }

    @Test
    public void test_sendPriceInaccuracyFeedback() throws Exception {
        baseObject.clickHelpLink();
        feedbackInaccuracyPage.clickFeedbackLink();
        feedbackInaccuracyPage.sendKeysInputMailFeedback(jsonReader.getStringJsonObject("feedback", "invalidEmail"));
        Assert.assertEquals(feedbackInaccuracyPage.getTxtWrongEmailFeedback(), stringsReader.readStringsXML("FI_01 assertion"));

        feedbackInaccuracyPage.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        Assert.assertEquals(feedbackInaccuracyPage.getTxtSelectedTopic(), stringsReader.readStringsXML("FI_02 assertion"));

        feedbackInaccuracyPage.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        feedbackInaccuracyPage.clickRadioBtn(0);
        Assert.assertTrue(feedbackInaccuracyPage.isRadioSelected(0));
        feedbackInaccuracyPage.clickRadioBtn(1);
        Assert.assertTrue(feedbackInaccuracyPage.isRadioSelected(1));
        feedbackInaccuracyPage.clickRadioBtn(2);
        Assert.assertTrue(feedbackInaccuracyPage.isRadioSelected(2));

        feedbackInaccuracyPage.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        Assert.assertEquals(feedbackInaccuracyPage.getValueCurrencyInput(), feedbackInaccuracyPage.getTxtValueCurrencyFooter() + " ");

        feedbackInaccuracyPage.selectTopicFeedback(jsonReader.getStringJsonObject("feedback", "priceTopic"));
        feedbackInaccuracyPage.uploadScreenshot();
        Assert.assertTrue(feedbackInaccuracyPage.getTextFileUploaded().contains(stringsReader.readStringsXML("FI_03 assertion")));


    }
}
