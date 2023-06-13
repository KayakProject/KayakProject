package tests;

import org.openqa.selenium.ElementNotInteractableException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.footerPo.POBadges;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TestBadges extends BaseTest {

    public BaseMobilePageObjects base;
    public POBadges badgesPage;
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
            badgesPage = new POBadges(BaseTest.appiumDriver);
        }
        else if(platform.equals("web")){
            badgesPage = new POBadges(BaseTest.driver);
            utils = new WebUtilities(BaseTest.driver);
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
    public void test_3_clickBadgesLink(String platform) throws Exception {
        String failMsg = "The badge page does not open.";
        if(platform.equals("mobile")){
            base.clickNavMenu();
            base.clickAboutLink();
        }
        badgesPage.clickBadgesLinkFooter();
        Assert.assertEquals(badgesPage.getTxtBadgesTitlePage(), stringsReader.readStringsXML("B_03 assertion"), failMsg);
    }

    @Test (priority = 4, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_4_enterHotelNotAwarded() throws IOException, InterruptedException {
        String failMsg = "A suggestion of hotels is not displayed.";
        badgesPage.sendKeysInputSearchTopRated(jsonReader.getStringJsonObject("badges", "notAwardedHotel"));
        badgesPage.sendKeysInvalidHotel();
        Assert.assertEquals(badgesPage.getTxtInputHotel(), jsonReader.getStringJsonObject("badges", "notAwardedHotelFullName"), failMsg);
    }

    @Test (priority = 5, dependsOnMethods = {"test_4_enterHotelNotAwarded"})
    public void test_5_clickBtnSearch() throws Exception {
        String failMsg = "A message does not indicate that the hotel is not awarded.";
        badgesPage.clickBtnSearch();
        Assert.assertEquals(badgesPage.getTxtHotelNotAwarded(), stringsReader.readStringsXML("B_04 assertion"), failMsg);
    }

    @Test (priority = 6, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_6_EnterHotelAwarded() throws Exception {
        String failMsg = "No message congratulates the hotel for Kayak awards.";
        badgesPage.sendKeysInputSearchTopRated(jsonReader.getStringJsonObject("badges", "awardedHotel"));
        badgesPage.sendKeysValidHotel();
        Assert.assertEquals(badgesPage.getTxtInputHotel(), jsonReader.getStringJsonObject("badges", "awardedHotelFullName"), failMsg);
    }

    @Test (priority = 7, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_7_clickBtnSearch() throws Exception {
        String failMsg = "The hotel name is not displayed.";
        badgesPage.clickBtnSearch();
        Assert.assertEquals(badgesPage.getTxtHotelAwardedTitle(), stringsReader.readStringsXML("B_07 assertion"), failMsg);
    }

    @Parameters({"platform"})
    @Test (priority = 8, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_8_verifyValuesDropdown(String platform) throws Exception {
        String failMsg = "The value expected in the dropdown are not displayed.";
        if(platform.equals("mobile")){
            Assert.assertEquals(badgesPage.getTxtSelectedCustom(1), stringsReader.readStringsXML("B_09 assertion"), failMsg);
            Assert.assertEquals(badgesPage.getTxtSelectedCustom(2), stringsReader.readStringsXML("B_09 assertion2"), failMsg);
        }
        else{
            Assert.assertEquals(badgesPage.getTxtSelectedCustom(0), stringsReader.readStringsXML("B_09 assertion"), failMsg);
            Assert.assertEquals(badgesPage.getTxtSelectedCustom(2), stringsReader.readStringsXML("B_09 assertion2"), failMsg);
        }
    }

    @Parameters({"platform"})
    @Test (priority = 9, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_9_verifyEmbedCode(String platform) throws Exception {
        String failMsg = "The embed code does not have the values expected.";
        if(platform.equals("mobile")){
            badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "theme", "dark"));
            badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("badges", "size", "large"));
        }
        else{
            badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "dark"));
            badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "large"));
        }

        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case1"), failMsg);
    }

    @Test (priority = 10, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_10_verifyBadgeVisual() throws Exception {
        String failMsg = "The badge does not match visually the values entered.";
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_10 assertion")), failMsg);
    }

    @Test (priority = 11, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_11_downloadBadge() throws IOException {
        String failMsg = "The badge is not downloaded.";
        badgesPage.clickBtnDownload();
        Assert.assertTrue(badgesPage.isDownloaded(jsonReader.getStringJsonObject("badges", "fileNameDownloaded", "badgeDownloaded1")), failMsg);
    }

    @Test (priority = 12, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_12_copyEmbedCode() throws IOException, UnsupportedFlavorException {
        String failMsg = "The embed code is not copied.";
        badgesPage.copyBtnEmbedCode();
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), badgesPage.getTxtClipboard(), failMsg);
    }

    @Parameters({"platform"})
    @Test (priority = 13, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_13_repeatTest9To12WithLightMedium(String platform) throws Exception {
        String failMsg1 = "The embed code does not contain the values expected.";
        String failMsg2 = "The badge does not match visually the values selected";
        if(platform.equals("mobile")){
            badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "theme", "light"));
            badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("badges", "size", "medium"));
        }
        else{
            badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "light"));
            badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "medium"));
        }
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case2"), failMsg1);
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_13 assertion")), failMsg2);
    }

    @Parameters({"platform"})
    @Test (priority = 14, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_14_repeatTest9To12WithWhiteSmall(String platform) throws Exception {
        String failMsg1 = "The embed code does not contain the values expected.";
        String failMsg2 = "The badge does not match visually the values selected.";

        if(platform.equals("mobile")){
            badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "theme", "white"));
            badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("badges", "size", "small"));
        }
        else{
            badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "white"));
            badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "small"));
        }
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case3"), failMsg1);
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_14 assertion")), failMsg2);
    }

    @Test (priority = 15, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_15_clickCertificatesBtn() throws Exception {
        String failMsg = "The certificate tab is not selected.";
        badgesPage.clickBtnCertificate();
        Assert.assertEquals(badgesPage.getTxtSelectedBtn(), stringsReader.readStringsXML("B_15 assertion"), failMsg);
    }

    @Test (priority = 16, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_16_verifyDefaultValuesCertificate() throws Exception {
        String failMsg = "The default values are not selected.";
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(4), stringsReader.readStringsXML("B_16 assertion"), failMsg);
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(6), stringsReader.readStringsXML("B_16 assertion2"), failMsg);
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(8), stringsReader.readStringsXML("B_16 assertion3"), failMsg);
    }

    @Test (priority = 17, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_17_verifyValuesDarkUsLandscape() throws Exception {
        String failMsg = "The badge doesn't match visually the values selected.";
        badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("certificates", "theme", "light"));
        badgesPage.selectCustomization(3, jsonReader.getStringJsonObject("certificates", "size", "usLetter"));
        badgesPage.selectCustomization(4, jsonReader.getStringJsonObject("certificates", "orientation", "portrait"));
        Assert.assertTrue(badgesPage.getCssCertificateBackground().contains(jsonReader.getStringJsonObject("certificates", "background", "light")), failMsg);
    }

    @Parameters({"platform"})
    @Test (priority = 18, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_18_downloadCertificate(String platform) throws Exception {
        String failMsg = "The badge doesn't match visually the values selected.";
        try{
            badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("certificates", "theme", "dark"));
            badgesPage.selectCustomization(3, jsonReader.getStringJsonObject("certificates", "size", "a4Letter"));
            badgesPage.selectCustomization(4, jsonReader.getStringJsonObject("certificates", "orientation", "landscape"));
            if(platform.equals("mobile")){
                base.clickCancelDownload();
            }
            Assert.assertTrue(badgesPage.getCssCertificateBackground().contains(jsonReader.getStringJsonObject("certificates", "background", "dark")), failMsg);
        }
        catch(ElementNotInteractableException e){
            Assert.assertTrue(false, failMsg);
        }
    }

    @Test (priority = 19, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_19_clickSocialTemplateBtn() throws IOException {
        String failMsg = "The certificate is not downloaded on user computer.";
        badgesPage.clickDownloadCertificate();
        Assert.assertTrue(badgesPage.isDownloaded(jsonReader.getStringJsonObject("badges", "fileNameDownloaded", "certificateDownloaded1")), failMsg);
    }

    @Test (priority = 20, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_20_selectPlatformInsta() throws Exception {
        String failMsg = "The social template tab is not selected.";
        badgesPage.clickBtnSocialTemplate();
        Assert.assertEquals(badgesPage.getTxtSelectedBtn(), stringsReader.readStringsXML("B_19 assertion"), failMsg);
    }

    @Test (priority = 21, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_21_downloadImgInsta() throws Exception {
        String failMsg = "The Instagram template is not downloaded.";
        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformInsta"));
        badgesPage.clickBtnDownloadSocial();
        Assert.assertTrue(badgesPage.isDownloaded(jsonReader.getStringJsonObject("badges", "fileNameDownloaded", "platformInsta")), failMsg);
    }

    @Test (priority = 22, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_22_copyTemplateInsta() throws IOException, UnsupportedFlavorException {
        String failMsg = "The template is not copied.";
        badgesPage.clickBtnCopyTemplate();
        Assert.assertEquals(badgesPage.getTxtSocialTemplate(), badgesPage.getTxtClipboard(), failMsg);
    }

    @Test (priority = 23, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_23_repeatTest20To22Twitter() throws Exception {
        String failMsg = "The Twitter template is not downloaded.";
        try {
            badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformTwitter"));
            badgesPage.clickBtnDownloadSocial();
            badgesPage.clickBtnCopyTemplate();
            Assert.assertTrue(badgesPage.isDownloaded(jsonReader.getStringJsonObject("badges", "fileNameDownloaded", "platformTwitter")), failMsg);
        }
        catch(ElementNotInteractableException e){
            Assert.assertTrue(false, failMsg);
        }
    }

    @Test (priority = 24, dependsOnMethods = {"test_6_EnterHotelAwarded"})
    public void test_24_repeatTest20To22Fb() throws Exception {
        String failMsg = "The Facebook template is not downloaded.";
        try {
            badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformFacebook"));
            badgesPage.clickBtnDownloadSocial();
            badgesPage.clickBtnCopyTemplate();
            Assert.assertTrue(badgesPage.isDownloaded(jsonReader.getStringJsonObject("badges", "fileNameDownloaded", "platformFacebook")), failMsg);
        }
        catch(ElementNotInteractableException e){
            Assert.assertTrue(false, failMsg);
        }
    }
}
