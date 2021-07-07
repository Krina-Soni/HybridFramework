package com.TestSuiteA;

import com.Utility.MyMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Manufacturerselection extends ParentTestSuiteA {

    @BeforeTest
    public void checkTestCase() {

        test= reports.createTest("Manufacturer Selection");

//		MyMethods.checkTestSuiteExecution(file, sheetName, testSuiteID)
        boolean output= MyMethods.checkTestCaseExecution(tsa, "TestCases", this.getClass().getSimpleName());
        logs.info("checkBeforeTest()");

        if(output) {
            logs.info("Execution of the test case  " + this.getClass().getSimpleName()+ "  is set to Yes");
        }else {
            throw new SkipException("Execution of the test case" + this.getClass().getSimpleName()+ "is set to No");
//			logs.info("Execution of the test case" + this.getClass().getSimpleName()+ "is set to No");
        }
    }

    @AfterTest
    public void AfterTest() {
        reports.flush();

    }

    @Test
    public void testManufacturersSelection() throws InterruptedException {

        driver.get(siteData.getProperty("url"));

        WebElement manuDD =isElementPresent("dd_manufacturer_name");
        List<WebElement> manuValues= manuDD.findElements(By.tagName("option"));

        for(int count=1 ; count<manuValues.size();count++ ) {

            manuValues.get(count).click();
            logs.info("Title: "+driver.getTitle() +"    URL:"+driver.getCurrentUrl());
//			Thread.sleep(4000);
            driver.navigate().back();

            //We need to add the below lines again because it is a virtual pointer and the list gets reset everytime the page is refreshed

            manuDD =isElementPresent("dd_manufacturer_name");
            manuValues= manuDD.findElements(By.tagName("option"));
        }



    }
}
