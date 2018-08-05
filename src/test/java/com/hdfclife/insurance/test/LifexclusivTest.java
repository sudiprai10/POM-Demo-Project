package com.hdfclife.insurance.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hdfclife.insurance.utility.Drivers;
import com.hdfclife.insurance.utility.Utilities;

/*
 * LifexclusivTest class is which has multiple methods which shows
 * different expects of links available on the given URL
 */
public class LifexclusivTest {
	private WebDriver driver;
	String url = "http://www.lifexclusiv.com";

	/*
	 * verifyLinksCount method print total count of links available
	 */
	@Test
	public void verifyLinksCount() {
		driver = Drivers.getChromeDriver();
		Utilities.openUrl(driver, url);
		List<WebElement> locators = driver.findElements(By.tagName("a"));
		int countOfElements = Utilities.getCountOfElements(locators);
		System.out.println("count:  " + countOfElements);
		Utilities.closeBrowser(driver);
	}

	/*
	 * printAllLinks method print total all links available
	 */
	@Test
	public void printAllLinks() {
		List<String> lLink = new ArrayList<String>();
		driver = Drivers.getChromeDriver();
		Utilities.openUrl(driver, url);
		List<WebElement> locators = driver.findElements(By.tagName("a"));
		int countOfElements = Utilities.getCountOfElements(locators);
		for (int index = 0; index < countOfElements; index++) {
			String link = Utilities.getElementWithIndex(locators, index)
					.getAttribute("href");
			lLink.add(link);
		}

		System.out.println("**************printAllLinks********************");
		for (int i = 0; i < lLink.size(); i++) {
			System.out.println(lLink.get(i));
		}

		System.out.println("**********************************");
		Utilities.closeBrowser(driver);
	}

	/*
	 * printAllUniqueLinks method print total all unique links available
	 */
	@Test
	public void printAllUniqueLinks() {
		HashSet<String> sLink = new HashSet<String>();
		driver = Drivers.getChromeDriver();
		Utilities.openUrl(driver, url);
		List<WebElement> locators = driver.findElements(By.tagName("a"));
		int countOfElements = Utilities.getCountOfElements(locators);
		for (int index = 0; index < countOfElements; index++) {
			String link = Utilities.getElementWithIndex(locators, index)
					.getAttribute("href");
			sLink.add(link);
		}
		Iterator<String> itr = sLink.iterator();
		System.out
				.println("************printAllUniqueLinks*********************");
		while (itr.hasNext()) {
			String href = itr.next();
			System.out.println(href);
		}
		Utilities.closeBrowser(driver);
	}

	/*
	 * openAllUniqueClickableLinks method print total all unique as well as
	 * clickable links
	 */
	@Test
	public void openAllUniqueClickableLinks() {
		System.out
				.println("***************openAllUniqueClickableLinks******************");
		Set<String> sLink = new HashSet<String>();
		driver = Drivers.getChromeDriver();
		Utilities.openUrl(driver, url);
		List<WebElement> locators = driver.findElements(By.tagName("a"));
		int countOfElements = Utilities.getCountOfElements(locators);
		for (int index = 0; index < countOfElements; index++) {
			String link = Utilities.getElementWithIndex(locators, index)
					.getAttribute("href");
			sLink.add(link);
		}
		Iterator<String> itr = sLink.iterator();
		while (itr.hasNext()) {
			String href = itr.next();
			if (href != null && href.contains("http")) {
				driver.navigate().to(href);

				System.out.println(href);
			}
		}
		Utilities.closeBrowser(driver);
	}

}
