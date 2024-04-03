package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	// Objects
	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement LoginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;

	// Initialize Objects
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public AccountPage login(String emailText, String passwordText) {
		
		emailField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		LoginButton.click();
		return new AccountPage(driver);
		
	}
	
	public String retrieveEmailPasswordNotMatchingWarningText() {
		
		String epWarningText = emailPasswordNotMatchingWarning.getText();
		return epWarningText;
		
	}

}
