package tests.webTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.webPo.POFooterCompany;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;


public class TestFooterCompany extends BaseTestWeb{
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
    public void test_FC_005_slideCarouselCareer(){

    }
}
