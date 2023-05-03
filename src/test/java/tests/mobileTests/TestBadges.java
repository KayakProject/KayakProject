package tests.mobileTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.mobilePo.BaseMobilePageObjects;
import pageObjects.mobilePo.POBadges;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;

public class TestBadges extends BaseTestMobile {

    BaseMobilePageObjects base;
    POBadges badgesPage;
    POBadges badgesPageWeb;
    public StringsReader stringsReader;
    public JSONReader jsonReader;

    @BeforeClass
    public void setupTestConnexionWeb(){
        base = new BaseMobilePageObjects(BaseTestMobile.appiumDriver);
        badgesPage = new POBadges(BaseTestMobile.appiumDriver);
        badgesPageWeb = new POBadges(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
    }

    //@Test
    public void test1() throws Exception {
        base.clickGoogleSearchBox();
        Assert.assertTrue(base.getUrl().contains(stringsReader.readStringsXML("FM_1 assertion")));
    }

    @Test
    public void test2() throws InterruptedException {
        base.clickGoogleSearchBox();
        badgesPageWeb.clickNavMenuBtn();
        badgesPage.clickAboutLinkMenu();
    }
}
