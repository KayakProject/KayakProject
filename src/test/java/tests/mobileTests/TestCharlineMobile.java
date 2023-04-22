package tests.mobileTests;

import org.testng.annotations.BeforeClass;
import pageObjects.webPo.POConnexion;
import utilities.JSONReader;
import utilities.StringsReader;

public class TestCharlineMobile extends BaseTestMobile {

    POConnexion mobilePage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;

    @BeforeClass
    public void setupTestConnexionWeb(){
        mobilePage = new POConnexion(BaseTestMobile.appiumDriver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
    }
}
