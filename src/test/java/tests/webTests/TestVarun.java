package tests.webTests;

import org.testng.annotations.BeforeClass;
import pageObjects.webPo.POConnexion;
import pageObjects.webPo.POVarun;
import utilities.JSONReader;
import utilities.StringsReader;

public class TestVarun extends BaseTestWeb{

    POVarun webPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;

    @BeforeClass
    public void setupTestConnexionWeb(){
        webPage = new POVarun(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
    }

}
