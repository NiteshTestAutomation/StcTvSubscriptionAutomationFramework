package com.stctvsubscription.testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.stctvsubscription.baseClass.BaseClass;
import com.stctvsubscription.pageObjects.SubscriptionPage;
import com.stctvsubscription.utils.Utilities;

import jdk.internal.org.jline.utils.Log;

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
	SubscriptionPage subscriptionPage;
	
	
	@BeforeTest
	public void openSTCWebApplication()
	{
		driver = setupBrowserAndOpenWebApplication(property.getProperty("browserName"));
		subscriptionPage = new SubscriptionPage(driver);
	}
	

	
	@Test(priority = 1,dataProvider = "ksaSubscriptionTypeTestData")
	public void verifyStcTVSubscriptionTypesforKSA(String SubscriptionType,String SubcriptionPrice) {
		
		Reporter.log("Changing country to Saudi Arabia from header");
		subscriptionPage.changeCountryToSaudiArabia();
		
		Reporter.log("Assert on Subscription Page");
		Assert.assertEquals(subscriptionPage.getSubscriptionPageTitle(), "Choose Your Plan");
	
		Reporter.log("Assert country<b> "+subscriptionPage.getCountryName()+"</b> has currency code<b>: "+subscriptionPage.getCurrency(SubcriptionPrice)+"</b> and currency name<b>: "+Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice))+"</b>");
		Assert.assertTrue(Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice)).contains("Saudi"));
		
		Reporter.log("Assert Subscription Type<b>: "+SubscriptionType+"</b> and Subcription Price<b>: "+SubcriptionPrice+"</b> matches testdata");
		verifySubscriptionPriceTypes(SubscriptionType,SubcriptionPrice);
	}
	

	
	@DataProvider(name="ksaSubscriptionTypeTestData")
	public Object[][] ksaSubsciptionTestData(){
		
		Object[][] data = Utilities.readExcelFileTestData("SaudiArabia");
		return data;
	}


	
	@Test(priority = 2 , dataProvider = "bahrainSubscriptionTypeTestData")
	public void verifyStcTVSubscriptionTypesforBahrain(String SubscriptionType,String SubcriptionPrice) {

		
		Reporter.log("Changing country to Bahrain from header");
		subscriptionPage.changeCountryToBahrain();
		
		Reporter.log("Assert on Subscription Page");
		Assert.assertEquals(subscriptionPage.getSubscriptionPageTitle(), "Choose Your Plan");
		
		Reporter.log("Assert country<b> "+subscriptionPage.getCountryName()+"</b> has currency code<b>: "+subscriptionPage.getCurrency(SubcriptionPrice)+"</b> and currency name<b>: "+Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice))+"</b>");
		Assert.assertTrue(Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice)).contains(subscriptionPage.getCountryName()));
		
		Reporter.log("Assert Subscription Type<b>: "+SubscriptionType+"</b> and Subcription Price<b>: "+SubcriptionPrice+"</b> matches testdata");
		verifySubscriptionPriceTypes(SubscriptionType,SubcriptionPrice);
	}
	
	
	@DataProvider(name="bahrainSubscriptionTypeTestData")
	public Object[][] bahrainSubsciptionTestData(){
		
		Object[][] data = Utilities.readExcelFileTestData("Bahrain");
		return data;
	}


	@Test(priority = 3 , dataProvider = "kuwaitSubscriptionTypeTestData")
	public void verifyStcTVSubscriptionTypesforKuwait(String SubscriptionType,String SubcriptionPrice) {
		
		Reporter.log("Changing country to Kuwait from header");
   		subscriptionPage.changeCountryToKuwait();
		
   		Reporter.log("Assert on Subscription Page");
		Assert.assertEquals(subscriptionPage.getSubscriptionPageTitle(), "Choose Your Plan");
	
		Reporter.log("Assert country<b> "+subscriptionPage.getCountryName()+"</b> has currency code<b>: "+subscriptionPage.getCurrency(SubcriptionPrice)+"</b> and currency name<b>: "+Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice))+"</b>");
		Assert.assertTrue(Utilities.getCurrencyName(subscriptionPage.getCurrency(SubcriptionPrice)).contains(subscriptionPage.getCountryName()));
		
		Reporter.log("Assert Subscription Type<b>: "+SubscriptionType+"</b> and Subcription Price<b>: "+SubcriptionPrice+"</b> matches testdata");
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
		//Subscription Types 
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
