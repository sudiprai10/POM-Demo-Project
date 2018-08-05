package com.hdfclife.insurance.cukes;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\panderah\\workspace\\CucumberProject\\insurance\\src\\test\\java\\com\\hdfclife\\insurance\\features\\BuyTermInsurance.feature", glue = { "com.hdfclife.insurance.stepdef" })
public class Runcukes {

}