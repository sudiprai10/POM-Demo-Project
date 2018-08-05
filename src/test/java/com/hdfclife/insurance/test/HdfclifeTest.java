package com.hdfclife.insurance.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import com.hdfclife.insurance.utility.InsuranceTestCase;
import com.hdfclife.insurance.utility.module.HomePageNavigator;

/*
 * HdfclifeTest class is which has multiple methods which shows
 * different expects of links available on the given URL
 */
public class HdfclifeTest extends InsuranceTestCase {
	
	private HomePageNavigator navigateFromHomePage;

	@Test
	public void verifyBasicDetailsEntry() {
		navigate.toHDFCLifeClick2Protect3DPlusPlan();
	
	}
}
