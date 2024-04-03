package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchPage;
	HomePage homePage;

	public SearchTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenapplicationURL(prop.getProperty("browser"));
		homePage = new HomePage(driver);

	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));

		Assert.assertTrue(searchPage.productText_HP_IsDisplayed(), "Product HP is not displayed");

	}

	@Test(priority = 2)
	public void verifySearchWithNonExistingOrInvalidProduct() {

		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		
		Assert.assertEquals(searchPage.getNoProductMessage(), "",
				"No product text is not displayed");

	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {

		searchPage = homePage.searchForAProduct(dataProp.getProperty("noProduct"));
		
		Assert.assertEquals(searchPage.getNoProductMessage(), dataProp.getProperty("noProductSearchMessage"),
				"No product text is not displayed");

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

}
