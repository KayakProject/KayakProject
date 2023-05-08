package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.MobileUtilities;
import utilities.elementsUtilities.WebUtilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Listeners implements ITestListener {
    CommonUtilities utils;
    BaseTest baseTest;
    BaseTest baseTestWeb;


    public void onTestStart(ITestResult result){
        utils = new WebUtilities(BaseTest.driver);
        String className = result.getTestClass().getRealClass().getSimpleName().substring(4);
        System.out.println(className);
        utils.readExcelData("A_001", "Authentication", "PENDING");
    }

    //This method prints stackTrace to each test case individually
    //This method takes screenshot of any failed test case
    public void onTestFailure(ITestResult result){
        utils = new WebUtilities(BaseTest.driver);
        String className = result.getTestClass().getRealClass().getSimpleName().substring(0, 3);
        utils.readExcelData("A_001", className, "FAIL");

        if(result.getThrowable() != null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
        }
        baseTest = new BaseTest();

        File file = null;
        file = BaseTest.appiumDriver.getScreenshotAs(OutputType.FILE);
        utils = new MobileUtilities(BaseTest.appiumDriver);

        try {
            String imagePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "com" + File.separator + "screenshots" + File.separator + result.getName() + "_" + utils.getDateTime() + "_" + "screenshot.png";
            FileUtils.copyFile(file, new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSuccess(ITestResult result){
        String className = result.getTestClass().getRealClass().getSimpleName().substring(0, 3);
        utils = new WebUtilities(BaseTest.driver);
        baseTestWeb = new BaseTest();
        utils.readExcelData("A_001", "Authentication", "PASS");
    }
}
