package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

    public void onTestSuccess(ITestResult result){
        String className = result.getTestClass().getRealClass().getSimpleName().substring(4);
        String scenarioStr = result.getName();
        int start = scenarioStr.indexOf("_")+ "_".length();
        int end = scenarioStr.lastIndexOf("_");
        double scenarioNb = Double.parseDouble(scenarioStr.substring(start, end));
        if(scenarioNb < 10){
            scenarioNb = Double.parseDouble(result.getName().substring(5, 6));
        }
        else{
            scenarioNb = Double.parseDouble(result.getName().substring(5, 7));
        }
        if(baseTest.isDriverMobile()){
            utils = new WebUtilities(BaseTest.appiumDriver);
        }
        else{
            utils = new WebUtilities(BaseTest.driver);
            utils.readExcelData(scenarioNb, className, "PASS", 7);
        }
    }

    //This method prints stackTrace to each test case individually
    //This method takes screenshot of any failed test case
    public void onTestFailure(ITestResult result){
        double scenarioNb = 0.0;
        String className = "any class";
        String msg = "msg";

        File file = null;
        TakesScreenshot scrShot = null;

        if(result.getThrowable() != null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
        }

        try{
            String scenarioStr = result.getName();
            int start = scenarioStr.indexOf("_")+ "_".length();
            int end = scenarioStr.lastIndexOf("_");
            scenarioNb = Double.parseDouble(scenarioStr.substring(start, end));

            if(scenarioNb < 10){
                scenarioNb = Double.parseDouble(result.getName().substring(5, 6));
            }
            else{
                scenarioNb = Double.parseDouble(result.getName().substring(5, 7));
            }

            className = result.getTestClass().getRealClass().getSimpleName().substring(4);

            String resultMsg = result.getThrowable().toString();
            int first = resultMsg.indexOf(":")+ ":".length();
            int  last = resultMsg.lastIndexOf(".");
            msg = resultMsg.substring(first, last);



            scrShot = ((TakesScreenshot) baseTest.driver);
        }
        catch(StringIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        if(baseTest.isDriverMobile()){
            scrShot = ((TakesScreenshot) BaseTest.appiumDriver);
            utils = new MobileUtilities(BaseTest.appiumDriver);
        }
        else{
            utils = new WebUtilities(BaseTest.driver);
            utils.readExcelData(scenarioNb, className, "FAIL", 7);
            utils.addFailedExcelMsg(msg, 6, className, scenarioNb);
        }

        file = scrShot.getScreenshotAs(OutputType.FILE);

        try {
            String imagePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "screenshots" + File.separator + result.getName() + "_" + utils.getDateTime() + "_" + "screenshot.jpg";
            FileUtils.copyFile(file, new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result){
        if(baseTest.isDriverMobile()){
            utils = new WebUtilities(BaseTest.appiumDriver);
        }
        else{
            utils = new WebUtilities(BaseTest.driver);
            String className = result.getTestClass().getRealClass().getSimpleName().substring(4);

            String scenarioStr = result.getName();
            int start = scenarioStr.indexOf("_")+ "_".length();
            int end = scenarioStr.lastIndexOf("_");
            double scenarioNb = Double.parseDouble(scenarioStr.substring(start, end));

            if(scenarioNb < 10){
                scenarioNb = Double.parseDouble(result.getName().substring(5, 6));
            }
            else{
                scenarioNb = Double.parseDouble(result.getName().substring(5, 7));
            }
            utils.readExcelData(scenarioNb, className, "CANNOT CHECK", 7);
        }
    }
}
