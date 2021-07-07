package com.Utility;
import java.io.File;
import java.io.IOException;

import com.Basepackage.BaseInit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class MyMethods extends BaseInit {


    public static void signIN(String email, String pwd) {
        System.out.println("SIGN IN ");
        //driver.findElement(By.linkText("log yourself in")).click();
        //driver.findElement(By.linkText(storage.getProperty("lnk_logurselfin_linkText"))).click();
        isElementPresent("lnk_logurselfin_linkText").click();
        isElementPresent("ip_emailAdd_name").sendKeys(email);
        isElementPresent("ip_pwd_name").sendKeys(pwd);
        isElementPresent("btn_signIN_id").click();
        MyMethods.getScreenShot("AfterLogin", driver);
    }

    public static void signOUT() {

        isElementPresent("lnk_logoff_linkText").click();
        isElementPresent("lnk_continue_id").click();

        //	checkTestSuiteExecution(ts, "TestSuite", "TestSuiteB");

        //file = ts;
    }

    public static boolean checkTestCaseExecution(ExcelFileReader file, String sheetName, String testCaseID) {

        int rows = file.totalRow(sheetName);

        for (int row = 1; row < rows; row++) {

            String testCaseName = file.getData(sheetName, row, 0);

            if (testCaseName.equalsIgnoreCase(testCaseID)) {

                String exeMode = file.getData(sheetName, row, 2);

                if (exeMode.equalsIgnoreCase("y"))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    public static boolean checkTestSuiteExecution(ExcelFileReader file, String sheetName, String testSuiteID) {
        int rows = file.totalRow(sheetName);
        for (int row = 1; row < rows; row++) {
            String testCaseName = file.getData(sheetName, row, 0);

            if (testCaseName.equalsIgnoreCase(testSuiteID)) {
                String exeMode = file.getData(sheetName, row, 2);
                if (exeMode.equalsIgnoreCase("y"))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    public static Object[][] getTestData(ExcelFileReader data, String sheetName) {

        int cols = data.totalColumn(sheetName);
        int rows = data.totalRow(sheetName);

        Object[][] myData = new Object[rows - 1][cols];

        for (int row = 1; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                
                myData[row - 1][col] = data.getData(sheetName, row, col);
            }
        }
        return myData;
    }

    public static String getScreenShot(String imageName, WebDriver driver) {

        TakesScreenshot ts = (TakesScreenshot)driver;

        File scrFile = ts.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/src/main/resources/Screenshots/" +imageName+System.currentTimeMillis()+".png";

        //System.out.println(path);
        File destination =new File(path);

        try {

            FileHandler.copy(scrFile,destination);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return path;
    }
}