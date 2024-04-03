package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement productText_HP;
	
	@FindBy(xpath="//div[@id='content']/p[contains(text(), 'There is')]")
	private WebElement noProductsMessage;

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public boolean productText_HP_IsDisplayed() {

		return productText_HP.isDisplayed();

	}

	public String getNoProductMessage() {

		return noProductsMessage.getText();

	}

}
