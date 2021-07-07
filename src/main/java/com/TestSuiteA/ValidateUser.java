package com.TestSuiteA;

import com.Utility.MyMethods;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidateUser extends ParentTestSuiteA{
    @BeforeTest
    public void checkTestCase() {

        test= reports.createTest("Validate User");

//		MyMethods.checkTestSuiteExecution(file, sheetName, testSuiteID)
        boolean output= MyMethods.checkTestCaseExecution(tsa, "TestCases", this.getClass().getSimpleName());
        logs.info("checkBeforeTest()");
        test.log(Status.INFO, "URL is loaded");

        if(output) {
            logs.info("Execution of the test case  " + this.getClass().getSimpleName()+ "  is set to Yes");
        }else {
            throw new SkipException("Execution of the test case" + this.getClass().getSimpleName()+ "is set to No");
//			logs.info("Execution of the test case" + this.getClass().getSimpleName()+ "is set to No");
        }
    }

    @Test(dataProvider="getTestDataFromXLS")
    public void testValidateUser(String email, String pwd) {

        driver.get(siteData.getProperty("url"));
        logs.info("URL Loaded");
        logs.info("Login with "+email+" and "+pwd);
        test.log(Status.PASS, "Login with "+email+" and "+pwd);
        MyMethods.signIN(email,pwd);

        try {
            Boolean checkLogin = driver.findElement(By.linkText("Log Off")).isDisplayed();

            if(checkLogin) {
                logs.info("user is already logged in");
                System.out.println("Already logged in");
                MyMethods.signOUT();
                test.log(Status.PASS, "Valid Email address and password");

            }
        }
        catch(Exception e) {
            logs.info("Invalid Username or Password");
            test.log(Status.FAIL, "Invalid Email address or password");
        }
    }

    @AfterTest
    public void AfterTest() {
        reports.flush();

    }

    @DataProvider
    public Object[][] getTestDataFromXLS() {
//		MyMethods.getTestData(data, sheetName)
        return MyMethods.getTestData(tsa, "ValidateUser");
    }
}
