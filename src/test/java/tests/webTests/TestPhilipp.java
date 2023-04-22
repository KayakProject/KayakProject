package tests.webTests;

import org.testng.annotations.BeforeClass;
import pageObjects.webPo.POConnexion;
import pageObjects.webPo.POPhilipp;
import utilities.JSONReader;
import utilities.StringsReader;

public class TestPhilipp extends BaseTestWeb{

    POPhilipp webPage;
    public StringsReader stringsReader;
    public JSONReader jsonReader;

    @BeforeClass
    public void setupTestConnexionWeb(){
        webPage = new POPhilipp(BaseTestWeb.driver);
        stringsReader = new StringsReader();
        jsonReader = new JSONReader();
    }

}
