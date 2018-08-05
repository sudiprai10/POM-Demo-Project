package com.hdfclife.insurance.stepdef;

import com.hdfclife.insurance.utility.InsuranceTestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hdfclife.insurance.page.TermInsurancePlan;
import com.hdfclife.insurance.utility.Drivers;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/*
 * StepDef class is an implementation of cucumber file to Buy term insurance
 */
public class StepDef extends InsuranceTestCase {

	String url = "https://www.hdfclife.com/term-insurance-plans";
	WebDriverWait wait;
	WebDriver driver;
	TermInsurancePlan termInsurancePlan;

	@Given("^open browser and get hdfc url to buy$")
	public void open_browser_and_get_hdfc_url_to_buy() throws Throwable {
		driver = Drivers.getChromeDriverOptions();
		termInsurancePlan = new TermInsurancePlan(driver);
		termInsurancePlan.buyOnlineTermInsurance(url);
	}

	@Given("^Switch to basic detail form \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void switch_to_basic_detail_form(String name, String lname,
			String mobNo, String emailId, String cityName, String dob)
			throws Throwable {
		termInsurancePlan.switchToBasicDetailForm().fillBasicDetails(name,
				lname, mobNo, emailId, cityName, dob);
	}

	@When("^choose (\\d+)d hdfclife insurance term plan$")
	public void choose_d_hdfclife_insurance_term_plan(int arg1)
			throws Throwable {
		termInsurancePlan.proceedToTermPlans().choose3DLife().proceed()
				.proceedToBuy();
	}

	@Then("^^complete another details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",,\"([^\"]*)\",\"([^\"]*)\"$")
	public void complete_another_details(String martialStatus,
			String education, String birthPlace, String house, String street,
			String area, String pincode, String ocupation, String idProof)
			throws Throwable {
		termInsurancePlan.completePersonalDetails(martialStatus, education,
				birthPlace, house, street, area, pincode).professionalInfo(
				ocupation, idProof);
	}

	@Then("^fill detail form \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void fill_detail_form(String name, String lname, String mobNo,
			String emailId, String cityName, String dob, String kycId,
			String fatherName, String motherName) throws Throwable {
		termInsurancePlan
				.nomineeDetails(name, lname, mobNo, emailId, cityName, dob)
				.ckycDetails(kycId, fatherName, motherName).submitDocuments();
	}

	@Then("^verify confirm page \"([^\"]*)\"$")
	public void verify_confirm_page(String confirmStr) throws Throwable {
		termInsurancePlan.confirmPage(confirmStr);
	}
}
