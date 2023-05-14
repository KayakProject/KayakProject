package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.commonPO.footerPo.POBadges;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

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
    public void test_1_(String platform) throws Exception {
        if (platform.equals("mobile")) {
            base.clickGoogleSearchBox();
            base.switchContextHandle();
            base.clickNavMenu();
            base.clickAboutLink();
        }
    }

    @Test (priority = 3)
    public void test_3_clickBadgesLink() throws Exception {
        badgesPage.clickBadgesLinkFooter();
        Assert.assertEquals(badgesPage.getTxtBadgesTitlePage(), stringsReader.readStringsXML("B_03 assertion"));
    }

    @Test (priority = 4, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_4_enterHotelNotAwarded() throws IOException, InterruptedException {
        badgesPage.sendKeysInputSearchTopRated(jsonReader.getStringJsonObject("badges", "notAwardedHotel"));
        badgesPage.sendKeysInvalidHotel();
        Assert.assertEquals(badgesPage.getTxtInputHotel(), jsonReader.getStringJsonObject("badges", "notAwardedHotelFullName"));
    }

    @Test (priority = 5, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_5_clickBtnSearch() throws Exception {
        badgesPage.clickBtnSearch();
        Assert.assertEquals(badgesPage.getTxtHotelNotAwarded(), stringsReader.readStringsXML("B_04 assertion"));
    }

    @Test (priority = 6, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_6_EnterHotelAwarded() throws Exception {
        badgesPage.sendKeysInputSearchTopRated(jsonReader.getStringJsonObject("badges", "awardedHotel"));
        badgesPage.sendKeysValidHotel();
        Assert.assertEquals(badgesPage.getTxtInputHotel(), jsonReader.getStringJsonObject("badges", "awardedHotelFullName"));
    }

    @Test (priority = 7, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_7_clickBtnSearch() throws Exception {
        badgesPage.clickBtnSearch();
        Assert.assertEquals(badgesPage.getTxtHotelAwardedTitle(), stringsReader.readStringsXML("B_07 assertion"));
    }

    @Test (priority = 8, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_8_verifyValuesDropdown() throws Exception {
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(0), stringsReader.readStringsXML("B_09 assertion"));
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(2), stringsReader.readStringsXML("B_09 assertion2"));
    }

    @Test (priority = 9, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_9_verifyEmbedCode() throws Exception {
        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "dark"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "large"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case1"));
    }

    @Test (priority = 10, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_10_verifyBadgeVisual() throws Exception {
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_10 assertion")));
    }

    @Test (priority = 11, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_11_downloadBadge() throws Exception {
        badgesPage.clickBtnDownload();
        Assert.assertTrue(badgesPage.isDownloaded("DARK_LARGE_TRAVEL_AWARDS.png"));
    }

    @Test (priority = 12, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_12_copyEmbedCode() throws Exception {
        badgesPage.copyBtnEmbedCode();
    }

    @Test (priority = 13, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_13_repeatTest9To12WithLightMedium() throws Exception {
        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "light"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "medium"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case2"));
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_13 assertion")));
    }

    @Test (priority = 14, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_14_repeatTest9To12WithWhiteSmall() throws Exception {
        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "white"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "small"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case3"));
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_14 assertion")));
    }

    @Test (priority = 15, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_15_clickCertificatesBtn() throws Exception {
        badgesPage.clickBtnCertificate();
        Assert.assertEquals(badgesPage.getTxtSelectedBtn(), stringsReader.readStringsXML("B_15 assertion"));
    }

    @Test (priority = 16, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_16_verifyDefaultValuesCertificate() throws Exception {
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(4), stringsReader.readStringsXML("B_16 assertion"));
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(6), stringsReader.readStringsXML("B_16 assertion2"));
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(8), stringsReader.readStringsXML("B_16 assertion3"));

    }

    @Test (priority = 17, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_17_verifyValuesDarkUsLandscape() throws Exception {
        badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("certificates", "theme", "light"));
        badgesPage.selectCustomization(3, jsonReader.getStringJsonObject("certificates", "size", "usLetter"));
        badgesPage.selectCustomization(4, jsonReader.getStringJsonObject("certificates", "orientation", "portrait"));
        Assert.assertTrue(badgesPage.getCssCertificateBackground().contains(jsonReader.getStringJsonObject("certificates", "background", "light")));
    }

    @Test (priority = 18, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_18_downloadCertificate() throws Exception {
        badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("certificates", "theme", "dark"));
        badgesPage.selectCustomization(3, jsonReader.getStringJsonObject("certificates", "size", "a4Letter"));
        badgesPage.selectCustomization(4, jsonReader.getStringJsonObject("certificates", "orientation", "landscape"));
        Assert.assertTrue(badgesPage.getCssCertificateBackground().contains(jsonReader.getStringJsonObject("certificates", "background", "dark")));
    }

    @Test (priority = 19, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_19_clickSocialTemplateBtn() throws Exception {
        badgesPage.clickDownloadCertificate();
    }

    @Test (priority = 20, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_20_selectPlatformInsta() throws Exception {
        badgesPage.clickBtnSocialTemplate();
        Assert.assertEquals(badgesPage.getTxtSelectedBtn(), stringsReader.readStringsXML("B_19 assertion"));
    }

    @Test (priority = 21, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_21_downloadImgInsta() throws Exception {
        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformInsta"));
        badgesPage.clickBtnDownloadSocial();
    }

    @Test (priority = 22, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_22_copyTemplateInsta() throws Exception {
        badgesPage.clickBtnCopyTemplate();
    }

    @Test (priority = 23, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_23_repeatTest20To22Twitter() throws Exception {
        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformTwitter"));
        badgesPage.clickBtnDownloadSocial();
        badgesPage.clickBtnCopyTemplate();
    }

    @Test (priority = 24, dependsOnMethods = {"test_3_clickBadgesLink"})
    public void test_24_repeatTest20To22Fb() throws Exception {
        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformFacebook"));
        badgesPage.clickBtnDownloadSocial();
        badgesPage.clickBtnCopyTemplate();
    }
}
