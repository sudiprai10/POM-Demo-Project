package com.hdfclife.insurance.page;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hdfclife.insurance.utility.Wait;

public class BasicDetailPage {

	public WebDriver driver;
	public Wait wait;
	
	@FindBy (id = "quote-gender-radio1")
	WebElement maleGenderRaidoBtn;
	
	@FindBy (id = "lifeAssuredFname")
	WebElement assurerFirstName;
	
	@FindBy (id = "lifeAssuredLname")
	WebElement assurerLastName;
	
	@FindBy (id = "lifeAssuredMOB")
	WebElement assurerMobileNo;
	
	@FindBy (id = "lifeAssuredEmail")
	WebElement assurerEmailId;
	
	@FindBy (id = "lifeAssuredDOB")
	WebElement assurerDOB;
	
	@FindBy (id = "lifeAssuredDOB")
	WebElement assurerCity;
	
	@FindBy (xpath = "//label[@for='quote-smoker-radio1']")
	WebElement smoker;
	
	
	public BasicDetailPage(WebDriver driver) {
		this.driver = driver;
		wait = new Wait(driver);
	}
	
	public void fillBasicDetailsForm() {
		if(!maleGenderRaidoBtn.isSelected()) {
			maleGenderRaidoBtn.click();
		}
		assurerFirstName.clear();
		assurerFirstName.sendKeys("Prashant");
		assurerLastName.clear();
		assurerLastName.sendKeys("Chavan");
		assurerMobileNo.clear();
		assurerMobileNo.sendKeys("123456789");
		assurerEmailId.clear();
		assurerEmailId.sendKeys("prashant.chavan@synechron.com");
		setCalendra("1975","Oct","10");
		smoker.click();
	}
	
	private void setCalendra(String year, String month, String date) {
		assurerDOB.click();
		WebElement yearBtn = driver.findElement(By.cssSelector(".dw-cal-year"));
		yearBtn.click();
		driver.findElement(By.xpath("//div[@class='dw-cal-sc-cell' and text()='"+year+"']")).click();
		WebElement monthBtn = driver.findElement(By.cssSelector(".dw-cal-month"));
		monthBtn.click();
		driver.findElement(By.xpath("//div[text()='"+month+"']")).click();
		wait.untilElementIsClickable(driver.findElement(By.xpath("//div[contains(@data-full,'9-"+date+"')]/div/div[text()='"+date+"']")));
		driver.findElement(By.xpath("//div[contains(@data-full,'9-20')]/div/div[text()='20']")).click();
	}
}
