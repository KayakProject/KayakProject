package tests.webTests;

import org.testng.annotations.BeforeClass;
import pageObjects.webPo.POConnexion;
import pageObjects.webPo.POPhilipp;
import utilities.StringsReader;

public class TestPhilipp extends BaseTestWeb{

    //****************************************** don't touch this section *************************************************
    POPhilipp webPage;
    public StringsReader stringsReader;

    @BeforeClass
    public void setupTestConnexionWeb(){
        webPage = new POPhilipp(BaseTestWeb.driver);
        stringsReader = new StringsReader();
    }
    //****************************************** don't touch this section *************************************************

}
