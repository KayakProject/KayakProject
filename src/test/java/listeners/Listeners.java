package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;
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


    //This method prints stackTrace to each test case individually
    //This method takes screenshot of any failed test case
    public void onTestFailure(ITestResult result){
        utils = new WebUtilities(BaseTest.driver);
        String className = result.getTestClass().getRealClass().getSimpleName().substring(4);
        double scenarioNb = Double.parseDouble(result.getName().substring(5, 6));
        utils.readExcelData(scenarioNb, className, "FAIL");

        if(result.getThrowable() != null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
        }

        File file = null;
        TakesScreenshot scrShot;
        if(baseTest.isDriverMobile()){
            scrShot =((TakesScreenshot) BaseTest.appiumDriver);
        }
        else{
            scrShot =((TakesScreenshot) BaseTest.driver);
        }
        file = scrShot.getScreenshotAs(OutputType.FILE);

        try {
            String imagePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "screenshots" + File.separator + result.getName() + "_" + utils.getDateTime() + "_" + "screenshot.jpg";
            FileUtils.copyFile(file, new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSuccess(ITestResult result){
        if(baseTest.isDriverMobile()){
            utils = new WebUtilities(BaseTest.appiumDriver);
        }
        else{
            utils = new WebUtilities(BaseTest.driver);
            String className = result.getTestClass().getRealClass().getSimpleName().substring(4);
            double scenarioNb = Double.parseDouble(result.getName().substring(5, 6));
            utils.readExcelData(scenarioNb, className, "PASS");
        }
    }

    public void onTestSkipped(ITestResult result){
        if(baseTest.isDriverMobile()){
            utils = new WebUtilities(BaseTest.appiumDriver);
        }
        else{
            utils = new WebUtilities(BaseTest.driver);
            String className = result.getTestClass().getRealClass().getSimpleName().substring(4);
            double scenarioNb = Double.parseDouble(result.getName().substring(5, 6));
            utils.readExcelData(scenarioNb, className, "CANNOT CHECK");
        }
    }
}
