package tests.webTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.webPo.POConnexion;
import utilities.StringsReader;

public class TestConnexionWeb extends BaseTestWeb{

    //****************************************** don't touch this section *************************************************
    POConnexion webPage;
    public StringsReader stringsReader;

    @BeforeClass
    public void setupTestConnexionWeb(){
        webPage = new POConnexion(BaseTestWeb.driver);
        stringsReader = new StringsReader();
    }
    //****************************************** don't touch this section *************************************************

    
    //Create 1 method per test case bellow this section.
    //Always start the method with "test" followed by the number of the test case
    //Always finish a test case with an assertion
    @Test
    public void test_001_connectUser() throws Exception {
        webPage.poClickSignInBtn();
        String continueWithEmailTxt = webPage.poGetTextContinueWithEmail();
        Assert.assertEquals(stringsReader.readStringsXML("String Continue with email"), continueWithEmailTxt);
    }

}
