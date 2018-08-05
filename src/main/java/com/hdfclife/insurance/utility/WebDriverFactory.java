package com.hdfclife.insurance.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {
    private static WebDriverFactory instance = null;
    private Configuration config;
    private int implicitWaitSeconds;
    private String url = null;
    static WebDriver driver = null;

    public static WebDriverFactory getInstance() {
        if (instance == null) {
            instance = new WebDriverFactory();
        }
        return instance;
    }

    protected WebDriverFactory() {
        config = Configuration.getInstance();
        implicitWaitSeconds = Integer.parseInt(config.get("selenium.implicitWaitSeconds"));
    }

    /**
     * Get the current driver
     *
     * @return Driver used by all the classes
     */
    public static WebDriver getDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        //capabilities.setCapability("platform", Platform.LINUX);
        capabilities.setCapability("platform", Platform.ANY);
        capabilities.setCapability("dom.ipc.plugins.enabled", false);
        
        try {
            driver = new ChromeDriver();
//            driver.setFileDetector(new LocalFileDetector());
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.err.println("Error starting remote driver: " + e.getMessage());
            System.exit(-1);
        }
        return driver;
    }

    /**
     * Disable the timeout applied when looking for DOM elements.
     * This is very useful when checking if a node has been destroyed.
     *
     * IMPORTANT: remember to call enableImplicitWaits() after the assertion has been performed
     *
     * @param driver Driver for which to disable the implicit waits
     * @see #enableImplicitWaits
     */
    public void disableImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Restores the original timeout used in DOM queries
     *
     * @param driver Driver for which to enable the implicit waits
     */
    public void enableImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(implicitWaitSeconds, TimeUnit.SECONDS);
    }

	public String getUrl() {
		config = Configuration.getInstance();
        return config.get("url");
	}
}