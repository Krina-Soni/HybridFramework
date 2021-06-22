package com.TestsuiteA;

import com.Basepackage.BaseInit;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.Utility.MyMethods;
import com.aventstack.extentreports.ExtentReports;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.Utility.ExcelFileReader;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class ParentTestSuiteA extends BaseInit {

    @BeforeSuite
    public void checkTestSuite() throws Exception
    {
        startUP();

        Boolean output = MyMethods.checkTestSuiteExecution(ts, "TestSuite", "TestSuiteA");

        if (output) {

            logs.info("Execution mode of the testsuite TestSuiteA is set to YES");
        }
        else {

            throw new SkipException("Execution mode of the testsuite TestSuiteA is set to NO");

        }
    }

}
