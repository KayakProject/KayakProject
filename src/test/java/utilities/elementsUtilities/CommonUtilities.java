package utilities.elementsUtilities;

import io.appium.java_client.AppiumDriver;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class CommonUtilities {

    WebDriver driver;
    AppiumDriver appiumDriver;
    String dataFile = "/Users/charlinelavigne/Desktop/End-To-End Test Cases.xlsx";

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

    //This method checks if a file has been downloaded on user computer
    public boolean verifyDownloadedFile(String fileName){
        String downloadedPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
        File dir = new File(downloadedPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }


    //This method enters the results of the test cases in the Test cases excel file
    public String readExcelData(double testCaseId, String sheetName, String result, int cellNb) {
        String data = null;

        try {
            File file = new File(dataFile);
            InputStream fileReader = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileReader);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row;
            Cell cell;
            int rowCount = 8 + (int) testCaseId;
            row = sheet.getRow(rowCount);

                for(int j = 0; j<=100; j++){
                    cell = row.getCell(j);
                    if (cell.getCellType().toString().equals("NUMERIC") && cell.getNumericCellValue() == testCaseId) {
                        data = sheet.getRow(rowCount).getCell(cellNb).getStringCellValue();
                        sheet.getRow(rowCount).getCell(cellNb).setCellValue(result);
                        FileOutputStream out = new FileOutputStream(dataFile);
                        workbook.write(out);
                        out.close();
                        fileReader.close();
                        return data;
                    }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public String addFailedExcelMsg(String msg, int cellNb, String sheetName, double testCaseId){
        String data = null;

        try {
            File file = new File(dataFile);
            InputStream fileReader = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileReader);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row;
            Cell cell;
            int rowCount = 8 + (int) testCaseId;
            int cellCount = cellNb;
            row = sheet.getRow(rowCount);

            for(int j = 0; j<=cellCount; j++){
                cell = row.getCell(j);
                if (cell.getCellType().toString().equals("NUMERIC") && cell.getNumericCellValue() == testCaseId) {
                    data = sheet.getRow(rowCount).getCell(cellNb).getStringCellValue();
                    sheet.getRow(rowCount).getCell(cellNb).setCellValue(msg);
                    FileOutputStream out = new FileOutputStream("/Users/charlinelavigne/Desktop/End-To-End Test Cases.xlsx");
                    workbook.write(out);
                    out.close();
                    fileReader.close();
                    return data;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
