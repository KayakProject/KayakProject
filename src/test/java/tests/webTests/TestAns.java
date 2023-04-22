package tests.webTests;

import org.testng.annotations.BeforeClass;
import pageObjects.webPo.POAns;
import pageObjects.webPo.POConnexion;
import utilities.StringsReader;

public class TestAns extends BaseTestWeb{

    //****************************************** don't touch this section *************************************************
    POAns webPage;
    public StringsReader stringsReader;

    @BeforeClass
    public void setupTest(){
        webPage = new POAns(BaseTestWeb.driver);
        stringsReader = new StringsReader();
    }
    //****************************************** don't touch this section *************************************************

}
