package tests.webTests.footer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.webPo.footerPo.POBadges;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

public class TestBadges {

    public POBadges badgesPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    SoftAssert softAssert;


    @BeforeClass
    public void setupTestConnexionWeb() {
        badgesPage = new POBadges(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
        softAssert = new SoftAssert();
    }


    @Test
    public void test_getBadges() throws Exception {
        badgesPage.clickBadgesLinkFooter();
        badgesPage.sendKeysInputSearchTopRated();
        badgesPage.clickBtnSearch();
        Assert.assertEquals(badgesPage.getTextElementSearched(), stringsReader.readStringsXML("B_01 assertion"));

        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "dark"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "large"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case1"));

        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "light"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "medium"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case2"));

        badgesPage.selectCustomization(0, jsonReader.getStringJsonObject("badges", "theme", "white"));
        badgesPage.selectCustomization(1, jsonReader.getStringJsonObject("badges", "size", "small"));
        Assert.assertEquals(badgesPage.getTxtEmbedCode(), jsonReader.getStringJsonObject("badges", "embedCode", "case3"));
        badgesPage.copyBtnEmbedCode();
        badgesPage.clickBtnDownload();

        badgesPage.clickBtnCertificate();
        badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("certificates", "theme", "light"));
        badgesPage.selectCustomization(3, jsonReader.getStringJsonObject("certificates", "size", "usLetter"));
        badgesPage.selectCustomization(4, jsonReader.getStringJsonObject("certificates", "orientation", "portrait"));
        Assert.assertTrue(badgesPage.getCssCertificateBackground().contains(jsonReader.getStringJsonObject("certificates", "background", "light")));
        //Assert.assertEquals(footerMore.getCssCertificateSize(), "278.9mm");

        badgesPage.selectCustomization(2, jsonReader.getStringJsonObject("certificates", "theme", "dark"));
        badgesPage.selectCustomization(3, jsonReader.getStringJsonObject("certificates", "size", "a4Letter"));
        badgesPage.selectCustomization(4, jsonReader.getStringJsonObject("certificates", "orientation", "landscape"));
        Assert.assertTrue(badgesPage.getCssCertificateBackground().contains(jsonReader.getStringJsonObject("certificates", "background", "dark")));
        //Assert.assertEquals(footerMore.getCssCertificateSize(), "209.9mm");
        badgesPage.clickDownloadCertificate();

        badgesPage.clickBtnSocialTemplate();
        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformInsta", "dark"));
        badgesPage.clickBtnDownloadSocial();
        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformTwitter", "dark"));
        badgesPage.clickBtnDownloadSocial();
        badgesPage.selectCustomization(5, jsonReader.getStringJsonObject("socialTemplate", "platformFacebook", "dark"));
        //footerMore.clickBtnDownloadSocial();
        //footerMore.clickBtnCopyTemplate();

    }
}
