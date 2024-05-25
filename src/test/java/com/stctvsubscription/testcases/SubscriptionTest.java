package com.stctvsubscription.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.stctvsubscription.baseClass.BaseClass;
import com.stctvsubscription.pageObjects.SubscriptionPage;
import com.stctvsubscription.utils.ExtentReport;
import com.stctvsubscription.utils.Utilities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionTest extends BaseClass{

	public WebDriver driver ;
	public ExtentReport report;
	SubscriptionPage subscriptionPage;

	
	@BeforeMethod
	public void openSTCWebApplication()
	{
		driver = setupBrowserAndOpenWebApplication(property.getProperty("browserName"));
		subscriptionPage = new SubscriptionPage(driver);
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	

	
	@Test(priority = 1,dataProvider = "ksaSubscriptionTypeTestData")
	public void verifyStcTVSubscriptionTypesforKSA(String SubscriptionType,String SubcriptionPrice) {
		subscriptionPage = new SubscriptionPage(driver);
		subscriptionPage.changeWebPageLanguage();
	   
		subscriptionPage.changeCountryToSaudiArabia();

		Assert.assertEquals(subscriptionPage.getSubscriptionPageTitle(), "Choose Your Plan");
	
		Assert.assertTrue(Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice)).contains("Saudi"));
		
		verifySubscriptionPriceTypes(SubscriptionType,SubcriptionPrice);
	}
	

	
	@DataProvider(name="ksaSubscriptionTypeTestData")
	public Object[][] ksaSubsciptionTestData(){
		
		Object[][] data = Utilities.readExcelFileTestData("SaudiArabia");
		return data;
	}


	
	@Test(priority = 2 , dataProvider = "bahrainSubscriptionTypeTestData")
	public void verifyStcTVSubscriptionTypesforBahrain(String SubscriptionType,String SubcriptionPrice) {

		subscriptionPage = new SubscriptionPage(driver);
		
		subscriptionPage.changeWebPageLanguage();
		
		subscriptionPage.changeCountryToBahrain();
		
		Assert.assertEquals(subscriptionPage.getSubscriptionPageTitle(), "Choose Your Plan");
		
		Assert.assertTrue(Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice)).contains(subscriptionPage.getCountryName()));
		
		verifySubscriptionPriceTypes(SubscriptionType,SubcriptionPrice);
	}
	
	
	@DataProvider(name="bahrainSubscriptionTypeTestData")
	public Object[][] bahrainSubsciptionTestData(){
		
		Object[][] data = Utilities.readExcelFileTestData("Bahrain");
		return data;
	}


	@Test(priority = 3 , dataProvider = "kuwaitSubscriptionTypeTestData")
	public void verifyStcTVSubscriptionTypesforKuwait(String SubscriptionType,String SubcriptionPrice) {

		subscriptionPage = new SubscriptionPage(driver);
		
		subscriptionPage.changeWebPageLanguage();
		
		subscriptionPage.changeCountryToKuwait();
		
		Assert.assertEquals(subscriptionPage.getSubscriptionPageTitle(), "Choose Your Plan");
		
		Assert.assertTrue(Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice)).contains(subscriptionPage.getCountryName()));
		
		verifySubscriptionPriceTypes(SubscriptionType,SubcriptionPrice);
	}
	
	@DataProvider(name="kuwaitSubscriptionTypeTestData")
	public Object[][] kuwaitSubsciptionTestData(){
		
		Object[][] data = Utilities.readExcelFileTestData("Kuwait");
		return data;
	}


	
	public void verifySubscriptionPriceTypes(String SubscriptionType,String ExpectedSubcriptionPrice)
	{
		String givenPrice;
		 switch (SubscriptionType){

		 case "LITE" : givenPrice = subscriptionPage.viewPrice(SubscriptionType);
		              	 Assert.assertTrue(ExpectedSubcriptionPrice.equalsIgnoreCase(givenPrice));	               
			break;
		 case "CLASSIC": givenPrice = subscriptionPage.viewPrice(SubscriptionType);
		                 Assert.assertTrue(ExpectedSubcriptionPrice.equalsIgnoreCase(givenPrice));	      
		    break;
		 case "PREMIUM": givenPrice = subscriptionPage.viewPrice(SubscriptionType);
		                 Assert.assertTrue(ExpectedSubcriptionPrice.equalsIgnoreCase(givenPrice));	      
		    break;
		}
		
	}
	
}
