package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.webPo.footerPo.POBadges;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

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
    @Test
    public void test_getBadges(String platform) throws Exception {
        if(platform.equals("mobile")){
            base.clickGoogleSearchBox();
            base.switchContextHandle();
            base.clickNavMenu();
            base.clickAboutLink();
        }

        badgesPage.clickBadgesLinkFooter();
        Assert.assertEquals(badgesPage.getTxtBadgesTitlePage(), stringsReader.readStringsXML("B_03 assertion"));

        badgesPage.sendKeysInputSearchTopRated(jsonReader.getStringJsonObject("badges", "notAwardedHotel"));
        badgesPage.sendKeysInvalidHotel();
        Assert.assertEquals(badgesPage.getTxtInputHotel(), jsonReader.getStringJsonObject("badges", "notAwardedHotelFullName"));

        badgesPage.clickBtnSearch();
        Assert.assertEquals(badgesPage.getTxtHotelNotAwarded(), stringsReader.readStringsXML("B_04 assertion"));

        badgesPage.sendKeysInputSearchTopRated(jsonReader.getStringJsonObject("badges", "awardedHotel"));
        badgesPage.sendKeysValidHotel();
        Assert.assertEquals(badgesPage.getTxtInputHotel(), jsonReader.getStringJsonObject("badges", "awardedHotelFullName"));

        badgesPage.clickBtnSearch();
        Assert.assertEquals(badgesPage.getTxtHotelAwardedTitle(), stringsReader.readStringsXML("B_07 assertion"));

        Assert.assertEquals(badgesPage.getTxtSelectedCustom(0), stringsReader.readStringsXML("B_09 assertion"));
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(2), stringsReader.readStringsXML("B_09 assertion2"));

        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "dark"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "large"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case1"));
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_10 assertion")));
        badgesPage.copyBtnEmbedCode();
        badgesPage.clickBtnDownload();

        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "light"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "medium"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case2"));
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_13 assertion")));

        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "white"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "small"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case3"));
        Assert.assertTrue(badgesPage.getTxtHeightBadge(stringsReader.readStringsXML("B_14 assertion")));

        badgesPage.clickBtnCertificate();
        Assert.assertEquals(badgesPage.getTxtSelectedBtn(), stringsReader.readStringsXML("B_15 assertion"));

        Assert.assertEquals(badgesPage.getTxtSelectedCustom(4), stringsReader.readStringsXML("B_16 assertion"));
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(6), stringsReader.readStringsXML("B_16 assertion2"));
        Assert.assertEquals(badgesPage.getTxtSelectedCustom(8), stringsReader.readStringsXML("B_16 assertion3"));


        badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("certificates", "theme", "light"));
        badgesPage.selectCustomization(3, jsonReader.getStringJsonObject("certificates", "size", "usLetter"));
        badgesPage.selectCustomization(4, jsonReader.getStringJsonObject("certificates", "orientation", "portrait"));
        Assert.assertTrue(badgesPage.getCssCertificateBackground().contains(jsonReader.getStringJsonObject("certificates", "background", "light")));

        badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("certificates", "theme", "dark"));
        badgesPage.selectCustomization(3, jsonReader.getStringJsonObject("certificates", "size", "a4Letter"));
        badgesPage.selectCustomization(4, jsonReader.getStringJsonObject("certificates", "orientation", "landscape"));
        Assert.assertTrue(badgesPage.getCssCertificateBackground().contains(jsonReader.getStringJsonObject("certificates", "background", "dark")));

        badgesPage.clickDownloadCertificate();

        badgesPage.clickBtnSocialTemplate();
        Assert.assertEquals(badgesPage.getTxtSelectedBtn(), stringsReader.readStringsXML("B_19 assertion"));

        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformInsta"));
        badgesPage.clickBtnDownloadSocial();
        badgesPage.clickBtnCopyTemplate();

        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformTwitter"));
        badgesPage.clickBtnDownloadSocial();
        badgesPage.clickBtnCopyTemplate();

        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformFacebook"));
        badgesPage.clickBtnDownloadSocial();
        badgesPage.clickBtnCopyTemplate();
    }
}
