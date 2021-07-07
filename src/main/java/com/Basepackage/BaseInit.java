package com.Basepackage;

/*Define Properties Files - SiteData & Object Storage
 * Reference of WebDriver
 * Define logs
 * Launch Browser
 * Maximize browser window
 * Delete All Cookies
 * Define TimeUnit
 * Reference of ExcelFileReader - TestSuite, TestSuiteA, TestSuiteB, TestSuiteC
 * Create isElementPresent method
 * Initialize Extent Report
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
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
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.Utility.ExcelFileReader;

public class BaseInit {
  public static WebDriver driver=null;
  public static Properties siteData=null;
  public static Properties objectStorage=null;
  public static Logger logs=null;

  public static ExcelFileReader ts =null;
  public static ExcelFileReader tsa =null;
  public static ExcelFileReader tsb =null;
  public static ExcelFileReader tsc =null;

  public static ExtentHtmlReporter reporter=null;
  public static ExtentReports reports =null;
  public static ExtentTest test=null;

  public void startUP() throws IOException{

    reporter=new ExtentHtmlReporter("./src/main/resources/Reports/Reports.html");
    reporter.config().setDocumentTitle("Gadgets Gallery Report");
    reporter.config().setTheme(Theme.DARK);
    reporter.config().setReportName("Release Report_1");

    reports= new ExtentReports();
    reports.setSystemInfo("Environment", "Testing");
    reports.setSystemInfo("Team name", "Circuit Breaker");
    reports.attachReporter(reporter);

    logs=Logger.getLogger("devpinoyLogger");
    logs.info("Sitedata properties file is loading now");
    logs.info("Browser will be launching soon");

    siteData=new Properties();
    FileInputStream fi= new FileInputStream("./src/main/resources/propertiesData/SiteData.properties");
    siteData.load(fi);

    logs.info("Object Storage properties file is loading now");

    objectStorage=new Properties();
    fi= new FileInputStream("./src/main/resources/propertiesData/ObjectStorage.properties");
    objectStorage.load(fi);

    String browserKey= siteData.getProperty("browser");

    if(browserKey.equalsIgnoreCase("chrome")) {
      System.setProperty("Webdriver.chrome.driver", System.getProperty("user.dir") + "chromedriver");
      driver=new ChromeDriver();
      logs.info("Chrome Browser launched");
    }
    else
    {
      logs.info("No Browser defined");
      System.out.println("No Browser defined");
    }

    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    ts= new  ExcelFileReader("./src/main/resources/testinformation/TestSuite.xlsx");
    tsa= new ExcelFileReader("./src/main/resources/testinformation/TestSuiteA.xlsx");
    tsb= new ExcelFileReader("./src/main/resources/testinformation/TestSuiteB.xlsx");
    tsc= new ExcelFileReader("./src/main/resources/testinformation/TestSuiteC.xlsx");

    logs.info("Start UP function successfully started");
  }
  public static WebElement isElementPresent(String propKey) {

    try {
      if(propKey.contains("xpath")) {
        return driver.findElement(By.xpath(objectStorage.getProperty(propKey)));
      }
      else if(propKey.contains("name")) {
        return driver.findElement(By.name(objectStorage.getProperty(propKey)));
      }

      else if(propKey.contains("id")) {
        return driver.findElement(By.id(objectStorage.getProperty(propKey)));
      }

      else if(propKey.contains("linkText")) {
        return driver.findElement(By.linkText(objectStorage.getProperty(propKey)));
      }

      else {
        System.out.println("Key not found in the properties file");
      }
    }
    catch(Exception e) {
    }
    return null;
  }
}
