package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	RegisterPage registerPage;
	AccountSuccessPage accSuccessPage;

	public WebDriver driver;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenapplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();

	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		accSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"), 
				Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), prop.getProperty("validPassword"), 
				prop.getProperty("validPassword"));

		Assert.assertEquals(accSuccessPage.getAccountCreationSuccessHeading(), dataProp.getProperty("accountCreationMessage"),
				"Account Success Page is not created");

	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountByProvidingAllFields() {
		
		accSuccessPage = registerPage.registerByEnteringAllFields(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"), 
				Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), prop.getProperty("validPassword"), 
				prop.getProperty("validPassword"));

		Assert.assertEquals(accSuccessPage.getAccountCreationSuccessHeading(), dataProp.getProperty("accountCreationMessage"),
				"Account Success Page is not created");

	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress() {
		
		registerPage.registerWithMandatoryFields(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"), 
				prop.getProperty("validEmail"), dataProp.getProperty("telephone"), prop.getProperty("validPassword"), 
				prop.getProperty("validPassword"));
		
		Assert.assertTrue(registerPage.getWarningTextforDuplicateEmailAndPrivacyUncheck().contains(dataProp.getProperty("emailAlreadyRegisteredWarning")),
				"Warning Message is not displayed for redundant email");

	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {

		registerPage.clickContinueBtn();

		Assert.assertTrue(registerPage.getWarningTextforDuplicateEmailAndPrivacyUncheck().contains(dataProp.getProperty("privacyPolicyWarning")),
				"Privacy Policy Warning Message is not displayed");
		
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("firstNameWarning"), 
				dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"),
				dataProp.getProperty("passwordWarning")), "Warning Messages are not displayed");


	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

}
