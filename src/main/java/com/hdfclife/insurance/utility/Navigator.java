package com.hdfclife.insurance.utility;


import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hdfclife.insurance.page.BasicDetailPage;

public class Navigator {

    private WebDriver driver;
    private Wait wait;
    
    @FindBy(css = ".featured-plan-container-inner")
    private WebElement featurePlanBox;
    
    @FindBy(css = ".btn-buy-online")
    private WebElement buyOnelineBtn;

    public Navigator(WebDriver driver) {
        this.driver = driver;
        wait = new Wait(driver);
    }

    public void toHomePage() {
    	WebDriverFactory.getInstance().disableImplicitWaits(driver);
        if(WebDriverFactory.getInstance().getUrl() != null) {
            driver.get(WebDriverFactory.getInstance().getUrl());
        } else {
            driver.close();
        }
    }

	public void toHDFCLifeClick2Protect3DPlusPlan() {
		WebElement buyOnlineBtn = driver.findElement(By.cssSelector(".btn-buy-online"));
        wait.untilElementIsVisible(buyOnlineBtn);
        buyOnlineBtn.click();
        navigateToBasicDetail().fillBasicDetailsForm();;
	}
	
	public BasicDetailPage navigateToBasicDetail() {
		Set<String> obj = driver.getWindowHandles();
		Iterator<String> itr = obj.iterator();
		itr.next();
		driver.switchTo().window(itr.next());
		wait.untilPageIsLoaded();
        return PageFactory.initElements(driver, BasicDetailPage.class);
    }
}