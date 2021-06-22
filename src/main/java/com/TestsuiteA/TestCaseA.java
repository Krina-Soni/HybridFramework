package com.TestsuiteA;

import com.Utility.MyMethods;
import com.aventstack.extentreports.Status;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;

public class TestCaseA extends ParentTestSuiteA{
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
}
