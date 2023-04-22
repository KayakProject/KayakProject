package tests.webTests;

import org.testng.annotations.BeforeClass;
import pageObjects.webPo.POAns;
import pageObjects.webPo.POConnexion;
import utilities.JSONReader;
import utilities.StringsReader;

public class TestAns extends BaseTestWeb{

    POAns webPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;

    @BeforeClass
    public void setupTest(){
        webPage = new POAns(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
    }

}
