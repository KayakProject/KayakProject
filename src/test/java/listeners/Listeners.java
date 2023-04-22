package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.mobileTests.BaseTestMobile;
import utilities.elementsUtilities.CommonUtilities;
import utilities.elementsUtilities.MobileUtilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Listeners implements ITestListener {
    CommonUtilities utils;
    BaseTestMobile baseTest;

    //This method prints stackTrace to each test case individually
    //This method takes screenshot of any failed test case
    public void onTestFailure(ITestResult result){
        if(result.getThrowable() != null){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
        }
        baseTest = new BaseTestMobile();

        File file = null;
        file = BaseTestMobile.appiumDriver.getScreenshotAs(OutputType.FILE);
        utils = new MobileUtilities(BaseTestMobile.appiumDriver);

        try {
            String imagePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "com" + File.separator + "screenshots" + File.separator + result.getName() + "_" + utils.getDateTime() + "_" + "screenshot.png";
            FileUtils.copyFile(file, new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
