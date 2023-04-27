package tests.webTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.webPo.POConnexion;
import pageObjects.webPo.POFooterCompany;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;


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
        String careerPageStr = stringsReader.readStringsXML("FC_002");
        footerCompany.clickCareerLink();
        String careerTitleStr = footerCompany.getTextCareerTitle();
        Assert.assertTrue(careerTitleStr.contains(careerPageStr));
    }


    @Test
    public void test_FC_003_displayOpenPositionsByLocation() throws Exception {
        footerCompany.clickCareerLink();
        footerCompany.clickViewPositionBtn();
        footerCompany.selectLocation(stringsReader.readStringsXML("FC_003 data"));
        Assert.assertEquals(footerCompany.getTextOpenPositions(), stringsReader.readStringsXML("FC_003 assertion"));
    }
}
