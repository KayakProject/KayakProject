package tests.webTests.footer;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import pageObjects.webPo.footerPo.POAffiliateNetwork;
import tests.webTests.BaseTestWeb;
import utilities.JSONReader;
import utilities.StringsReader;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.IOException;

public class TestAffiliateNetwork {
    public POAffiliateNetwork affiliatePage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;
    public CommonUtilities utils;
    public SoftAssert softAssert;


    @BeforeClass
    public void setupTestConnexionWeb(){
        affiliatePage = new POAffiliateNetwork(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
        utils = new WebUtilities(BaseTestWeb.driver);
        softAssert = new SoftAssert();
    }


    //@Test
    public void test_applyAffiliateNetworkPersonal() throws Exception {
        affiliatePage.clickLinkAffiliates();
        affiliatePage.clickBtnJoinNow();
        affiliatePage.clickPersonalAccount();
        affiliatePage.clickContinueBtn();

        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "invalidEmail"));
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(0), stringsReader.readStringsXML("AN_01 assertion"));

        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdNoSpecialCharacters"));
        Assert.assertEquals(affiliatePage.getTxtErrorPswdCharacters(), stringsReader.readStringsXML("AN_02 assertion"));

        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdNoUppercase"));
        Assert.assertEquals(affiliatePage.getTxtErrorPswdCharacters(), stringsReader.readStringsXML("AN_03 assertion"));

        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "invalidData", "pwdLessThan12"));
        Assert.assertEquals(affiliatePage.getTxtErrorMsgRegistration(1), stringsReader.readStringsXML("AN_04 assertion"));

        affiliatePage.sendKeysRegistrationInputEmail(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"));
        affiliatePage.sendKeysRegistrationPassword(jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validPwd"));
        affiliatePage.selectCountryRegistration();
        affiliatePage.clickTermsUse();
        affiliatePage.clickContinueBtn();
        Assert.assertEquals(affiliatePage.getTxtContactInfo(), stringsReader.readStringsXML("AN_05 assertion"));

        Assert.assertEquals(affiliatePage.getTxtContactEmail(), jsonReader.getStringJsonObject("affiliateNetwork", "validData", "validEmail"));
    }
}
