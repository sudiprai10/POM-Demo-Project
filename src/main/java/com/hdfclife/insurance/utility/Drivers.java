package com.hdfclife.insurance.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
/*
 * Drivers class is to initialization of browsers
 * Currently only chrome browsers has been implemented in static methods
 */
public class Drivers {
	private static WebDriver driver = null;

	/*
	 * getChromeDriver method is use to initialize 
	 * chrome browser without any allow/don't allow notification
	 */
	public static WebDriver getChromeDriver() {
		System.setProperty(
				"webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\src\\main\\java\\com\\hdfclife\\insurance\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

	/*
	 * getChromeDriverOptions method is use to initialize 
	 * chrome browser with any allow/don't allow notification
	 */
	public static WebDriver getChromeDriverOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty(
				"webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "\\src\\main\\java\\com\\hdfclife\\insurance\\resources\\chromedriver.exe");
		driver = new ChromeDriver(options);
		return driver;

	}
}
