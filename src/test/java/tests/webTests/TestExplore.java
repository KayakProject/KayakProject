package tests.webTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.ExplorePO;
import pageObjects.webPo.footerPo.POAffiliateNetwork;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

public class TestExplore extends BaseTestWeb {
	public ExplorePO explorePage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    public SoftAssert softAssert;


    @BeforeClass
    public void setupTestConnexionWeb(){
    	explorePage = new ExplorePO(BaseTestWeb.driver);       
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
        softAssert = new SoftAssert();
    }
    @Test
    public void enter() {
    	System.out.println("Testing");
    }
}
