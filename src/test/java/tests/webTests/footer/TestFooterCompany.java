package tests.webTests.footer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.webPo.POFooterCompany;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;


public class TestFooterCompany extends BaseTestWeb {
    public POFooterCompany footerCompany;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;


    @BeforeClass
    public void setupTestConnexionWeb(){
        footerCompany = new POFooterCompany(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
    }


    @Test
    public void test_FC_002_accessCareerPage() throws Exception {
        footerCompany.clickCareerLink();
        Assert.assertTrue(footerCompany.getTextCareerTitle().contains(stringsReader.readStringsXML("FC_002")));
    }


    @Test
    public void test_FC_003_displayOpenPositionsByLocation() throws Exception {
        footerCompany.clickCareerLink();
        footerCompany.clickViewPositionBtn();
        footerCompany.selectLocation(jsonReader.getStringJsonObject("openPositions", "true"));
        Assert.assertEquals(footerCompany.getTextOpenPositions(), stringsReader.readStringsXML("FC_003 assertion"));
    }


    @Test
    public void test_FC_004_displayMessageNoOpenPositionByDepartment() throws Exception {
        footerCompany.clickCareerLink();
        footerCompany.clickViewPositionBtn();
        footerCompany.selectDepartment(jsonReader.getStringJsonObject("openPositions", "false"));
        Assert.assertEquals(footerCompany.getTextNoPositions(), stringsReader.readStringsXML("FC_004 assertion"));
    }


    @Test
    public void test_FC_005_slideCarouselCareer() throws InterruptedException {
        footerCompany.clickCareerLink();
        footerCompany.clickRightArrowCarousel();
        Assert.assertTrue(footerCompany.isDisplayedflexibleCarousel());
        footerCompany.clickLeftArrowCarousel();
        Assert.assertTrue(footerCompany.isDisplayedWellnessCarousel());
    }

    @Test
    public void test_FC_007_redirectToInterviewPage() throws Exception {
        footerCompany.clickCareerLink();
        Assert.assertEquals(footerCompany.getVideoType(), stringsReader.readStringsXML("FC_007 assertion"));
    }

    @Test
    public void test_FC_009_sendAppDownloadToPhone() throws IOException, InterruptedException {
        BaseTestWeb.driver.navigate().back(); //CHECK AGAIN THIS ONE
        footerCompany.clickMobileLink();
        footerCompany.selectPhoneCode(jsonReader.getStringJsonObject("phoneContact", "countryCode"));
        footerCompany.sendKeysPhoneNumber(jsonReader.getStringJsonObject("phoneContact", "phoneNumber"));
        footerCompany.clickSendAppPhoneBtn();
        Assert.assertTrue(footerCompany.isPhoneLinkSent());
    }

    @Test
    public void test_FC_010_downloadKayakAndroid() throws Exception {
        BaseTestWeb.driver.navigate().back(); //CHECK AGAIN THIS ONE
        footerCompany.clickMobileLink();
        footerCompany.clickDownloadAppAndroid();
        Assert.assertEquals(footerCompany.getTitlePage(), stringsReader.readStringsXML("FC_010 assertion"));
    }

    @Test
    public void test_FC_011_downloadKayakIphone() throws Exception {
        BaseTestWeb.driver.navigate().back(); //goes back to mobile page
        BaseTestWeb.driver.navigate().back(); //goes back to home page
        footerCompany.clickMobileLink();
        footerCompany.clickDownloadAppIphone();
        Assert.assertEquals(footerCompany.getTitlePage(), stringsReader.readStringsXML("FC_011 assertion"));
    }

    @Test
    public void test_FC_012_replaceLinkMobileFooter() throws Exception {
        BaseTestWeb.driver.navigate().back(); //goes back to mobile page
        BaseTestWeb.driver.navigate().back(); //goes back to home page
        footerCompany.clickMobileLink();
        Assert.assertEquals(footerCompany.getTextLinkFooter(), stringsReader.readStringsXML("FC_012 assertion"));
    }

}
