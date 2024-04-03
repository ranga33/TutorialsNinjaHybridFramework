package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstnameField;

	@FindBy(id = "input-lastname")
	private WebElement lastnameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(name = "newsletter")
	private WebElement newsLetterRadioBtn;

	@FindBy(name = "agree")
	private WebElement agreeCheckBox;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueBtn;

	

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement warningTextforDuplicateEmailAndPrivacyUncheck;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameEmptyWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameEmptyWarning;
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailEmptyWarning;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneEmptyWarning;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordEmptyWarning;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailName, String telephone, String validPassword, String confirmPassword) {
		
		firstnameField.sendKeys(firstNameText);
		lastnameField.sendKeys(lastNameText);
		emailField.sendKeys(emailName);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(validPassword);
		confirmPasswordField.sendKeys(confirmPassword);
		agreeCheckBox.click();
		continueBtn.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerByEnteringAllFields(String firstNameText, String lastNameText, String emailName, String telephone, String validPassword, String confirmPassword) {
		
		firstnameField.sendKeys(firstNameText);
		lastnameField.sendKeys(lastNameText);
		emailField.sendKeys(emailName);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(validPassword);
		confirmPasswordField.sendKeys(confirmPassword);
		newsLetterRadioBtn.click();
		agreeCheckBox.click();
		continueBtn.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public void clickContinueBtn() {
		
		continueBtn.click();
		
	}


	public String getWarningTextforDuplicateEmailAndPrivacyUncheck() {

		String warningTextDuplicateEmailAndPrivacyUncheck = warningTextforDuplicateEmailAndPrivacyUncheck.getText();
		return warningTextDuplicateEmailAndPrivacyUncheck;

	}

	public String getFirstNameEmptyWarningText() {

		String fnEmptyWarningText = firstNameEmptyWarning.getText();
		return fnEmptyWarningText;
	}

	public String getLastNameEmptyWarningText() {

		String lnEmptyWarningText = lastNameEmptyWarning.getText();
		return lnEmptyWarningText;
	}
	
	public String getEmailEmptyWarningText() {

		String emailEmptyWarningText = emailEmptyWarning.getText();
		return emailEmptyWarningText;
	}
	
	public String getTelephoneEmptyWarningText() {

		String telephoneEmptyWarningText = telephoneEmptyWarning.getText();
		return telephoneEmptyWarningText;
	}
	
	public String getPasswordEmptyWarningText() {

		String pwdEmptyWarningText = passwordEmptyWarning.getText();
		return pwdEmptyWarningText;
	}
	
	public boolean displayStatusOfWarningMessages(String eFNWarning, String eLNWarning, String eEmailWarning, String eTeleWarning, String ePwdWarning) {
		
		boolean fnWarningStatus = firstNameEmptyWarning.getText().equals(eFNWarning);
		boolean lnWarningStatus = lastNameEmptyWarning.getText().equals(eLNWarning);
		boolean emailWarningStatus = emailEmptyWarning.getText().equals(eEmailWarning);
		boolean teleWarningStatus = telephoneEmptyWarning.getText().equals(eTeleWarning);
		boolean pwdWarningStatus = passwordEmptyWarning.getText().equals(ePwdWarning);
		
		return fnWarningStatus && lnWarningStatus && emailWarningStatus && teleWarningStatus && pwdWarningStatus;
		
	}

}
