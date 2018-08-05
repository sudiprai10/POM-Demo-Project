package com.hdfclife.insurance.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hdfclife.insurance.utility.Drivers;
import com.hdfclife.insurance.utility.Utilities;

public class BrokenLinks {

	private WebDriver driver;

	/*
	 * verifyBrokenLinks method print total all links available
	 */
	@Test
	public void verifyBrokenLinksOfLifexclusiv() {
		String url = "http://www.lifexclusiv.com";
		driver = Drivers.getChromeDriver();
		Utilities.openUrl(driver, url);
		List<WebElement> locators = driver.findElements(By.tagName("a"));
		int countOfElements = Utilities.getCountOfElements(locators);
		for (int index = 0; index < countOfElements; index++) {
			String link = Utilities.getElementWithIndex(locators, index)
					.getAttribute("href");
			verifyBrokenLink(link);
		}

		Utilities.closeBrowser(driver);
	}

	/*
	 * verifyBrokenLinks method print total all links available
	 */
	@Test
	public void verifyBrokenLinksOfHdfclife() {
		String url = "https://www.hdfclife.com/";
		driver = Drivers.getChromeDriverOptions();
		Utilities.openUrl(driver, url);
		List<WebElement> locators = driver.findElements(By.tagName("a"));
		int countOfElements = Utilities.getCountOfElements(locators);
		for (int index = 0; index < countOfElements; index++) {
			String link = Utilities.getElementWithIndex(locators, index)
					.getAttribute("href");
			verifyBrokenLink(link);
		}

		Utilities.closeBrowser(driver);
	}

	private void verifyBrokenLink(String link) {
		try {
			URL urlLink = new URL(link);
			HttpURLConnection http = (HttpURLConnection) urlLink
					.openConnection();
			http.setConnectTimeout(2000);
			http.connect();
			if (http.getResponseCode() == 200) {
				System.out.println(link + ", ResponseCode: "
						+ http.getResponseCode() + ", ResponseMessage: "
						+ http.getResponseMessage());
			} else {
				System.out.println(link + ", ResponseCode: "
						+ http.getResponseCode() + ", ResponseMessage: "
						+ http.getResponseMessage());
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
