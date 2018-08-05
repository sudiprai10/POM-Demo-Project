package com.hdfclife.insurance.utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

/**
 * Created by sudip on 8/3/18.
 */
public class InsuranceTestCase {
    protected WebDriver driver;
    protected Navigator navigate;
    protected String url;

    @BeforeClass
    public void instantiateDriver() {
    	driver = WebDriverFactory.getDriver();
        navigate = new Navigator(driver);
        navigate.toHomePage();
    }

    @BeforeClass
    public void logCurrentClassName() {
        System.out.println(this.getClass().getName());
    }

    @AfterMethod
    public void logTestStatus(final ITestResult testResult) {
        if (!testResult.isSuccess()) {
            System.err.println(testResult.getMethod().getMethodName() + ": Failed");
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
//        driver.quit();
    }
}
