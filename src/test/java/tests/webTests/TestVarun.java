package tests.webTests;

import org.testng.annotations.BeforeClass;
import pageObjects.webPo.POConnexion;
import pageObjects.webPo.POVarun;
import utilities.StringsReader;

public class TestVarun extends BaseTestWeb{

    //****************************************** don't touch this section *************************************************
    POVarun webPage;
    public StringsReader stringsReader;

    @BeforeClass
    public void setupTestConnexionWeb(){
        webPage = new POVarun(BaseTestWeb.driver);
        stringsReader = new StringsReader();
    }
    //****************************************** don't touch this section *************************************************

}
