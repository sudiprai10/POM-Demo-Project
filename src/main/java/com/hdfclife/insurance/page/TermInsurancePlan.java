package com.hdfclife.insurance.page;

import java.util.Iterator;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hdfclife.insurance.utility.Utilities;

/* TermInsurancePlan is class where all methods and object are
 * available to buy 3D life term insurance
 */
public class TermInsurancePlan {
	WebDriver driver = null;
	Actions action = null;
	WebDriverWait wait = null;
	JavascriptExecutor jse = null;
	/*
	 * xpath of date used while adding basic details like date of birth
	 */
	String dateXpath1 = "//div/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[";
	String dateXpath2 = "]/div[";
	String dateXpath3 = "]/div/div[1]";
	String mon = "//span[contains(@class,'dw-cal-month')]";
	String preMon = "//div[contains(@aria-label,'Previous Month')]";
	String postMon = "//div[contains(@aria-label,'Next Month')]";
	String year = "//span[@class='dw-cal-year']";
	String preYear = "//div[contains(@aria-label,'Previous Year')]";
	String postYear = "//div[contains(@aria-label,'Next Year')]";

	public TermInsurancePlan(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10000);
		jse = (JavascriptExecutor) driver;
	}

	/*
	 * buyOnlineTermInsurance method takes argument driver instance and hdfclife
	 * term insurance url actions covered in this methods: open browser enter
	 * hdfclife term insurance url click on 'Buy Online' button
	 */
	public TermInsurancePlan buyOnlineTermInsurance(String url) {
		Utilities.openUrl(driver, url);
		WebElement planFlag = driver.findElement(By.xpath("//div[@class='plan-tag']"));
		Utilities.scrollToElement(jse, planFlag);
		WebElement buyOnlineButton = driver
				.findElement(By.xpath("//a[@role='button' and contains(text(),'BUY ONLINE')]"));
		buyOnlineButton.click();
		return this;
	}

	/*
	 * switchToBasicDetailForm method accepts no arguments actions covered in this
	 * methods: Switches to another window/tab
	 */
	public TermInsurancePlan switchToBasicDetailForm() {
		Set<String> obj = driver.getWindowHandles();
		Iterator<String> itr = obj.iterator();
		itr.next();
		driver.switchTo().window(itr.next());
		return this;
	}

	/*
	 * fillBasicDetails method accepts no arguments actions covered in this methods:
	 * selected gender Entered FirstName Entered LastName Entered dob Entered
	 * MobileNumber Entered email Select Smoker/non smoker option Select City and
	 * state
	 */
	public TermInsurancePlan fillBasicDetails(String name, String lname, String mobNo, String emailId, String cityName,
			String dob) {
		Utilities.WaitForPageToLoad(driver, By.xpath("//div[@class='dvTcell']/div[1]/label"));
		WebElement genderMale = driver.findElement(By.xpath("//div[@class='dvTcell']/div[1]/label"));
		Utilities.elementClickableWait(wait, genderMale);
		genderMale.click();
		WebElement firstName = driver.findElement(By.xpath("//div[@id='Nri_No']/ul/li[1]/div/input"));
		firstName.sendKeys(name);
		WebElement lastName = driver.findElement(By.xpath("//div[@id='Nri_No']/ul/li[2]/div/input"));
		lastName.sendKeys(lname);
		WebElement mobileNumber = driver.findElement(By.xpath("//div[@id='Nri_No']/ul/li[3]/div/span[1]/input"));
		mobileNumber.sendKeys(mobNo);
		WebElement email = driver.findElement(By.xpath("//div[@id='Nri_No']/ul/li[4]/div/input"));
		email.sendKeys(emailId);
		WebElement nonSmoker = driver.findElement(By.xpath("//div[@id='Nri_No']/ul/li[6]/div/div[2]/div[2]/label"));
		nonSmoker.click();
		WebElement city = driver.findElement(By.xpath("//div[@id='Nri_No']/ul/li[7]/div/span/input"));
		city.sendKeys(cityName);
		driver.findElement(By.xpath("//div/div[1]/div[2]/div/div[1]/div/ul/li[5]/div[1]/div/div/input")).click();
		Utilities.setCalendar(driver, wait, dob, dateXpath1, dateXpath2, dateXpath3, mon, preMon, postMon, year,
				preYear, postYear);
		return this;
	}

	/*
	 * proceedToTermPlans method accepts no arguments actions covered in this
	 * methods: proceed to next page
	 */
	public TermInsurancePlan proceedToTermPlans() {
		WebElement proceed = driver.findElement(By.xpath("//button[@class='redBtn']/span"));
		Utilities.elementClickableWait(wait, proceed);
		proceed.click();
		Utilities.elementStalenessWait(wait, proceed);
		return this;
	}

	/*
	 * choose3DLife method accepts no arguments actions covered in this methods:
	 * scroll window in such a way to choose 3Dlife term plan
	 */
	public TermInsurancePlan choose3DLife() throws InterruptedException {
		WebElement saIconPlus = driver.findElement(By.xpath("//a[contains(@class,'saIconPlus')]"));
		Utilities.elementClickableWait(wait, saIconPlus);
		Utilities.WaitForPageToLoad(driver, By.xpath("//div[@class='customCheckbox small illnesses']"));
		WebElement customCheckbox = driver.findElement(By.xpath("//div[@class='customCheckbox small illnesses']"));
		Utilities.scrollToElement(jse, customCheckbox);
		WebElement taxes = driver.findElement(By.xpath("//td/span/span[@class='taxes']"));
		Utilities.scrollToElement(jse, taxes);
		WebElement lnkPlan3DLife = driver.findElement(By.xpath("//a[contains(@class,'lnkPlan3 3D-Life')]"));
		Utilities.elementClickableWait(wait, lnkPlan3DLife);
		lnkPlan3DLife.click();
		return this;
	}

	/*
	 * proceed method accepts no arguments actions covered in this methods: scroll
	 * to element and click on proceed button
	 */
	public TermInsurancePlan proceed() throws InterruptedException {
		WebElement getCover = driver.findElement(By.xpath("//label[@for='getCover']"));
		Utilities.scrollToElement(jse, getCover);
		WebElement proceed = driver.findElement(By.xpath("//button[contains(@class,'btnArrowDown')]/span"));
		Utilities.elementClickableWait(wait, proceed);
		Utilities.WaitForPageToLoad(driver, By.xpath("//button[contains(@class,'btnArrowDown')]/span"));
		proceed.click();
		return this;
	}

	/*
	 * proceedToBuy method accepts no arguments actions covered in this methods:
	 * scroll element in such a way to proceed to Buy
	 */
	public TermInsurancePlan proceedToBuy() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement getCover = driver.findElement(By.xpath("//label[@for='getCover']"));
		Utilities.scrollToElement(jse, getCover);
		WebElement proceedBuy = driver.findElement(By.xpath("//button[contains(@class,'redBtn')]/span"));
		Utilities.elementClickableWait(wait, proceedBuy);
		proceedBuy.click();
		return this;
	}

	/*
	 * completePersonalDetails method accepts no arguments actions covered in this
	 * methods: Select Martial Select Qualification Select place of birth NRI option
	 * PEP option Criminal option
	 */
	public TermInsurancePlan completePersonalDetails(String martialStatus, String education, String birthPlace,
			String house, String street, String area, String pincode) throws InterruptedException {
		Utilities.WaitForPageToLoad(driver, By.xpath("//select[@id='martialStatus']"));
		WebElement status = driver.findElement(By.xpath("//select[@id='martialStatus']"));
		Utilities.elementClickableWait(wait, status);
		Utilities.selectByVisibleText(status, martialStatus);
		WebElement qualification = driver.findElement(By.id("eduQualification"));
		Utilities.selectByVisibleText(qualification, education);
		WebElement placeofBirth = driver.findElement(By.id("placeofBirth"));
		Utilities.enterIntoTextBox(placeofBirth, birthPlace);
		WebElement nri = driver.findElement(By.xpath("//label[@class='no' and @for='noPersonal-1']"));
		Utilities.selectRadioButton(wait, nri);
		WebElement pep = driver.findElement(By.xpath("//label[@class='no' and @for='noPep']"));
		Utilities.selectRadioButton(wait, pep);
		WebElement noconviction = driver.findElement(By.xpath("//label[contains(@for,'noconviction')]"));
		Utilities.selectRadioButton(wait, noconviction);
		WebElement next = driver.findElement(By.xpath("//a[@id='personalBtn']"));
		Utilities.elementClickableWait(wait, next);
		next.click();
		WebElement checkAddManual = driver.findElement(By.xpath("//label[@for='checkAddManual']"));
		Utilities.elementClickableWait(wait, checkAddManual);
		checkAddManual.click();
		Utilities.scrollToElement(jse, checkAddManual);
		WebElement houseNo = driver.findElement(By.id("houseNo"));
		Utilities.enterIntoTextBox(houseNo, house);
		WebElement streetName = driver.findElement(By.id("streetName"));
		Utilities.enterIntoTextBox(streetName, street);
		WebElement pArea = driver.findElement(By.id("pArea"));
		Utilities.enterIntoTextBox(pArea, area);
		WebElement pPincode = driver.findElement(By.id("pPincode"));
		Utilities.enterIntoTextBox(pPincode, pincode);
		WebElement yesBtn = driver.findElement(By.xpath("//label[@for='layes']"));
		Utilities.selectRadioButton(wait, yesBtn);
		WebElement nextAgain = driver.findElement(By.id("personalBtn"));
		Utilities.elementClickableWait(wait, nextAgain);
		nextAgain.click();
		return this;
	}

	/*
	 * professionalInfo method accepts no arguments actions covered in this methods:
	 * enter occupation details,PAN card,etc and click on next button
	 */
	public TermInsurancePlan professionalInfo(String ocupation, String idProof) throws InterruptedException {
		Utilities.WaitForPageToLoad(driver, By.xpath("//select[@id='occupationalDetails']"));
		WebElement status = driver.findElement(By.xpath("//select[@id='occupationalDetails']"));
		Utilities.elementClickableWait(wait, status);
		Utilities.selectByVisibleText(status, ocupation);
		WebElement PAN = driver.findElement(By.id("PAN"));
		Utilities.enterIntoTextBox(PAN, idProof);
		WebElement nextAgain = driver.findElement(By.id("professionalBtn"));
		Utilities.elementClickableWait(wait, nextAgain);
		nextAgain.click();
		return this;
	}

	/*
	 * nomineeDetails method accepts arguments actions covered in this methods:
	 * enter occupation details,PAN card,etc and click on next button
	 */
	public TermInsurancePlan nomineeDetails(String fname, String lname, String dob, String maritalStatus,
			String relation, String cover) throws InterruptedException {
		Utilities.WaitForPageToLoad(driver,
				By.xpath("//input[contains(@name,'body.nominee[0].personalinfo.firstnm')]"));
		WebElement firstName = driver
				.findElement(By.xpath("//input[contains(@name,'body.nominee[0].personalinfo.firstnm')]"));
		firstName.sendKeys(fname);
		WebElement lastName = driver
				.findElement(By.xpath("//input[contains(@name,'body.nominee[0].personalinfo.lastnm')]"));
		lastName.sendKeys(lname);
		WebElement date = driver.findElement(By.xpath("//input[@name='body.nominee[0].personalinfo.dob']"));
		Utilities.elementClickableWait(wait, date);
		Utilities.setDateValue(jse, date, dob);
		WebElement genderMale = driver.findElement(By.xpath("//label[@for='NOMINEE_0-male']"));
		Utilities.elementClickableWait(wait, genderMale);
		genderMale.click();
		WebElement status = driver.findElement(By.xpath("//select[contains(@id,'1MaritalStatus')]"));
		Utilities.selectByVisibleText(status, maritalStatus);
		WebElement relationship = driver.findElement(By.xpath("//select[contains(@id,'1Relationship')]"));
		Utilities.selectByVisibleText(relationship, relation);
		WebElement nomYes = driver.findElement(By.xpath("//label[@for='nom-yes-0']"));
		Utilities.selectRadioButton(wait, nomYes);

		WebElement relationship1_1 = driver.findElement(By.xpath("//input[@id='relationship1_1']"));
		relationship1_1.sendKeys(cover);
		WebElement nextAgain = driver.findElement(By.xpath("//a[contains(@class,'continueBtn nextBtn appSameasLa')]"));
		Utilities.elementClickableWait(wait, nextAgain);
		nextAgain.click();
		return this;
	}

	/*
	 * ckycDetails method accepts arguments kycId fatherName motherName,in this
	 * methods: entered kycId,fatherName,motherName,etc to complete KYC details and
	 * clicked on next button
	 */
	public TermInsurancePlan ckycDetails(String kycId, String fatherName, String motherName)
			throws InterruptedException {
		Utilities.WaitForPageToLoad(driver, By.xpath("//input[contains(@name,'ckycnum')]"));
		WebElement ckycnum = driver.findElement(By.xpath("//input[contains(@name,'ckycnum')]"));
		ckycnum.sendKeys(kycId);
		WebElement fafname = driver.findElement(By.id("fafname"));
		fafname.sendKeys(fatherName);
		WebElement mofname = driver.findElement(By.id("mofname"));
		mofname.sendKeys(motherName);
		WebElement nextAgain = driver.findElement(By.xpath("//a[@class='continueBtn nextBtn']"));
		Utilities.elementClickableWait(wait, nextAgain);
		nextAgain.click();
		return this;
	}

	/*
	 * submitDocuments method accepts no argument,in this methods: details of all
	 * identify proof and their document details are mentioned
	 */
	public TermInsurancePlan submitDocuments() throws InterruptedException {
		Utilities.WaitForPageToLoad(driver, By.xpath("//select[@name='la-age-proof']"));
		WebElement ageProof = driver.findElement(By.xpath("//select[@name='la-age-proof']"));
		Utilities.selectByVisibleText(ageProof, "Aadhar Card");
		WebElement ageDoc = driver.findElement(By.xpath("//label[@for='emailDoc_REQ_001']"));
		Utilities.selectRadioButton(wait, ageDoc);
		WebElement incomeProof = driver.findElement(By.xpath("//select[@name='la-income-proof']"));
		Utilities.selectByVisibleText(incomeProof, "Audited Firm Accounts");
		WebElement incomeDoc = driver.findElement(By.xpath("//label[@for='emailDoc_REQ_219']"));
		Utilities.selectRadioButton(wait, incomeDoc);
		WebElement residenceProof = driver.findElement(By.xpath("//select[@name='la-residence-proof']"));
		Utilities.selectByVisibleText(residenceProof, "Aadhar Card");
		WebElement residenceDoc = driver.findElement(By.xpath("//label[@for='emailDoc_REQ_5051']"));
		Utilities.selectRadioButton(wait, residenceDoc);
		WebElement identityProof = driver.findElement(By.xpath("//select[@name='la-identity-proof']"));
		Utilities.selectByVisibleText(identityProof, "Aadhar Card");
		WebElement identityDoc = driver.findElement(By.xpath("//label[@for='emailDoc_REQ_002']"));
		Utilities.selectRadioButton(wait, identityDoc);
		WebElement photo = driver.findElement(By.xpath("//select[@name='la-photo']"));
		Utilities.selectByVisibleText(photo, "Photo");
		WebElement photoDoc = driver.findElement(By.xpath("//label[@for='emailDoc_REQ_003']"));
		Utilities.selectRadioButton(wait, photoDoc);
		WebElement docCondition = driver.findElement(By.xpath("//label[@for='docCondition']"));
		Utilities.scrollToElement(jse, docCondition);
		WebElement docUploadBtn = driver.findElement(By.xpath("//button[@class='procBtn docUploadBtn']"));
		docUploadBtn.click();
		WebElement dematNo = driver.findElement(By.xpath("//label[@for='dematNo']"));
		Utilities.selectRadioButton(wait, dematNo);
		WebElement procBtn = driver.findElement(By.xpath("//button[@class='procBtn']"));
		procBtn.click();
		WebElement next = driver.findElement(By.xpath("//a[@class='continueBtn nextBtn docUploadBtn']"));
		next.click();
		return this;
	}

	/*
	 * confirmPage method accepts expected Text to validate Confirm proposal page
	 * with actual
	 */
	public void confirmPage(String expectedString) throws InterruptedException {
		Utilities.WaitForPageToLoad(driver,
				By.xpath("//a[@href='javascript:void(0)' and contains(text(),'Confirm Proposal')]"));
		try {
			WebElement link = driver
					.findElement(By.xpath("//a[@href='javascript:void(0)' and contains(text(),'Confirm Proposal')]"));
			Assert.assertEquals(expectedString, link.getText());
		} finally {
			Utilities.closeBrowser(driver);
		}

	}
}
