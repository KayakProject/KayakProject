package utilities.elementsUtilities;

import io.appium.java_client.AppiumDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public abstract class CommonUtilities {

    WebDriver driver;
    AppiumDriver appiumDriver;

    public CommonUtilities(WebDriver driver){
        this.driver = driver;
    }

    public CommonUtilities(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
    }


    public abstract WebElement waitForElement(By locator);

    //This method retrieves the current date of the day
    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    //This method enters the results of the test cases in the Test cases excel file
    public String readExcelData(String testCaseId, String sheetName, String result) {
        String dataFile = "/Users/charlinelavigne/Desktop/Test Cases.xlsx";
        String data = null;

        try {
            File file = new File(dataFile);
            InputStream fileReader = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileReader);
            Sheet sheet = workbook.getSheet(sheetName);
            System.out.println(sheet);
            Row row;
            Cell cell;
            int rowCount = 2;
            int cellCount = 8;
            //int rowCount = sheet.getLastRowNum();

            for(int i = 1; i<rowCount; i++) {
                row = sheet.getRow(i);
                //System.out.println(sheet.getRow(i));

                for(int j = 0; j<=cellCount; j++){
                    cell = row.getCell(j);

                    if (cell.getStringCellValue().equals(testCaseId)) {
                        data = sheet.getRow(i).getCell(8).getStringCellValue();
                        sheet.getRow(i).getCell(8).setCellValue("PASS");
                        FileOutputStream out = new FileOutputStream("/Users/charlinelavigne/Desktop/Test Cases.xlsx");
                        workbook.write(out);
                        out.close();
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
