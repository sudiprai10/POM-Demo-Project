package com.hdfclife.insurance.utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Utilities class is where all project related utility methods are implemented
 * Specially commonly used methods are available
 */

public class Utilities {

	static int pageLoadTimeout = 60000;
	static int pageLoadWait = 1000;

	/*
	 * getCountOfElements to get count of web Element
	 */
	public static int getCountOfElements(List<WebElement> elements) {
		return elements.size();
	}

	/*
	 * getElementWithIndex to get web Element with index
	 */
	public static WebElement getElementWithIndex(List<WebElement> elements,
			int index) {
		return elements.get(index);
	}

	/*
	 * openUrl method accepts WebDriver instance and URL, actions covered==>>
	 * open browser, maximize browser and get URL
	 */
	public static void openUrl(WebDriver driver, String url) {
		driver.manage().window().maximize();
		driver.get(url);
	}

	/*
	 * closeBrowser method accepts WebDriver instance, actions covered==>> close
	 * browser
	 */
	public static void closeBrowser(WebDriver driver) {
		driver.close();
		driver.quit();

	}

	/*
	 * click method accepts WebElement, actions covered==>> click on element
	 */
	public static void click(WebElement element) {
		element.click();
	}

	/*
	 * enterIntoTextBox method accepts WebElement and textToEnter, actions
	 * covered==>> enter into text element
	 */
	public static void enterIntoTextBox(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	/*
	 * elementClickableWait method accepts WebDriverWait and WebElement ,
	 * actions covered==>> to check elementToBeClickable
	 */
	public static void elementClickableWait(WebDriverWait wait,
			WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/*
	 * elementInvisibilityWait method accepts WebDriverWait and WebElement ,
	 * actions covered==>> to check element Invisibility Wait
	 */
	public static void elementInvisibilityWait(WebDriverWait wait,
			WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/*
	 * elementStalenessWait method accepts WebDriverWait and WebElement ,
	 * actions covered==>> to check element Staleness Wait
	 */
	public static void elementStalenessWait(WebDriverWait wait,
			WebElement element) {
		wait.until(ExpectedConditions.stalenessOf(element));

	}

	/*
	 * selectByVisibleText method accepts WebDriverWait and WebElement , actions
	 * covered==>> to check selectByVisibleText
	 */
	public static void selectByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);

	}

	/*
	 * selectRadioButton method accepts WebDriverWait and WebElement , actions
	 * covered==>> to check elementClickableWait then click on radio button
	 * object
	 */

	public static void selectRadioButton(WebDriverWait wait, WebElement element) {
		Utilities.elementClickableWait(wait, element);
		element.click();

	}

	/*
	 * WaitForPageToLoad method accepts WebDriver and By , actions covered==>>
	 * wait till page loads for given element is visible
	 */
	public static void WaitForPageToLoad(WebDriver driver, By element) {
		boolean found = false;
		int currentWait = 0;

		while (!found && currentWait < pageLoadTimeout) {
			try {
				WebElement targetElement = driver.findElement(element);
				found = true;
			} catch (Exception e) {
				currentWait = currentWait + pageLoadWait;
				try {
					Thread.sleep(pageLoadWait);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		if (!found) {
			throw new NoSuchElementException(
					"we waited for 60 seconds but requested page is not loaded: "
							+ element);
		}
	}

	public static void scrollToElement(JavascriptExecutor jse,
			WebElement element) {
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/*
	 * setCalendar method accepts arguments, actions covered==>> choose
	 * year,month and date from calendar box
	 */
	public static void setCalendar(WebDriver driver, WebDriverWait wait,
			String date, String dateXpath1, String dateXpath2,
			String dateXpath3, String mon, String preMon, String postMon,
			String year, String preYear, String postYear) {
		String[] dDate = date.split("-");
		year(driver, year, preYear, postYear, dDate[2]);
		month(driver, mon, preMon, postMon, dDate[1]);
		date(driver, wait, dateXpath1, dateXpath2, dateXpath3, dDate[0]);
	}

	/*
	 * year method accepts year related arguments, actions covered==>> in this
	 * methods choose year as per given yYear
	 */
	public static void year(WebDriver driver, String year, String preYear,
			String postYear, String yYear) {
		while (Integer.parseInt(yYear) > Integer.parseInt(driver.findElement(
				By.xpath(year)).getText())) {
			driver.findElement(By.xpath(postYear)).click();
		}
	}

	/*
	 * month method accepts month related arguments, actions covered==>> in this
	 * methods choose month as per given mMonth
	 */
	public static void month(WebDriver driver, String mon, String preMon,
			String postMon, String mMonth) {
		while (!mMonth.equals(driver.findElement(By.xpath(mon)).getText())) {
			driver.findElement(By.xpath(postMon)).click();
		}
	}

	/*
	 * date method accepts date related arguments, actions covered==>> in this
	 * methods choose date as per given dDate
	 */
	public static void date(WebDriver driver, WebDriverWait wait,
			String dateXpath1, String dateXpath2, String dateXpath3,
			String dDate) {
		Boolean flag = false;
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 7; j++) {
				String xpath = dateXpath1 + i + dateXpath2 + j + dateXpath3;
				if (dDate.equals(driver.findElement(By.xpath(xpath)).getText())) {
					wait.until(ExpectedConditions.elementToBeClickable(driver
							.findElement(By.xpath(xpath))));
					driver.findElement(By.xpath(xpath)).click();
					flag = true;
					break;
				}
			}
			if (flag == true) {
				break;
			}
		}

	}

	/*
	 * setDateValue method accepts JavascriptExecutor,WebElement and date
	 * arguments, actions covered==>> in this method set given date using
	 * JavascriptExecutor
	 */
	public static void setDateValue(JavascriptExecutor jse,
			WebElement dateObject, String datevalue) {
		jse.executeScript("arguments[0].value='" + datevalue + "';", dateObject);
	}

}
