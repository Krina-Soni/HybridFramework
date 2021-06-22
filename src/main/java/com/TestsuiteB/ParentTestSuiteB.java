package com.TestsuiteB;

import com.Basepackage.BaseInit;
import com.Utility.MyMethods;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class ParentTestSuiteB extends BaseInit {

    @BeforeSuite
    public void checkTestSuite() throws Exception {

        startUP();
        boolean output = MyMethods.checkTestSuiteExecution(ts, "TestSuite", "TestSuiteA");

        if(output) {

            logs.info("Execution mode of the testsuite TestSuiteA is set to YES");

        }else {

            throw new SkipException("Execution mode of the testsuite TestSuiteA is set to NO");

        }

    }
}