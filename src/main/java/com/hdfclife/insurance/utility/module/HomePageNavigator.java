package com.hdfclife.insurance.utility.module;

import org.openqa.selenium.WebDriver;

import com.hdfclife.insurance.utility.Navigator;
import com.hdfclife.insurance.utility.Wait;

public class HomePageNavigator {

	private WebDriver driver;
	private Navigator navigate;
	private Wait wait;
	
	public HomePageNavigator(WebDriver driver) {
		this.driver = driver;
		navigate = new Navigator(driver);
		wait = new Wait(driver);
	}
	
}
