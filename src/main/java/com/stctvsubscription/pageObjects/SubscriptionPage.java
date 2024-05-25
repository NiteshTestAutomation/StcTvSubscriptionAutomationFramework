/**
 * 
 */
package com.stctvsubscription.pageObjects;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * 
 */
public class SubscriptionPage {

	WebDriver driver;
	
	//Page Objects
	
		@FindBy(xpath="//a[@id='translation-btn']")
		private WebElement EnglishTranslationBtn ;
		
		@FindBy(xpath="//a[@id='country-btn']")
		private WebElement CountryDropDownBtn ;
		
		@FindBy(xpath="//a[@id='sa']")
		private WebElement CountryOptionSaudiArabia ;

		@FindBy(xpath="//a[@id='bh']")
		private WebElement CountryOptionBahrain ;
		
		@FindBy(xpath="//a[@id='kw']")
		private WebElement CountryOptionKuwait ;
		
		@FindBy(xpath="//div[@id='main']/div/h2")
		private WebElement SubscriptionPageHeaderText ;
		
		@FindBy(xpath="//*[@class='price' and @id = 'currency-lite']")
		private WebElement getLiteSubscriptionPriceText ;
		
		@FindBy(xpath="//*[@class='price' and @id = 'currency-classic']")
		private WebElement getClassicSubscriptionPriceText ;
		
		@FindBy(xpath="//*[@class='price' and @id = 'currency-premium']")
		private WebElement getPremiumSubscriptionPriceText ;
	
		@FindBy(xpath="//*[@id='country-name']")
		private WebElement getcountryName ;
		
		public SubscriptionPage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver,this);
			
		}
		
		//Page Actions
		
		public void changeWebPageLanguage()
		{
			EnglishTranslationBtn.click();
			
		}
		
		public void changeCountryToSaudiArabia()
		{
			CountryDropDownBtn.click();
			
			CountryOptionSaudiArabia.click();
			
		}
		
		public void changeCountryToBahrain()
		{
			CountryDropDownBtn.click();
			
			CountryOptionBahrain.click();
			
		}
		
		public void changeCountryToKuwait()
		{
			CountryDropDownBtn.click();
			
			CountryOptionKuwait.click();
			
		}
		
		public String getSubscriptionPageTitle()
		{
			
			String pageHeader = SubscriptionPageHeaderText.getText();
			
			return pageHeader;
		}
		
		public String getCountryName()
		{
			String country = getcountryName.getText();
			String countryName = country.replaceAll("\\s","");
			return countryName;
		}
		
		
		public String viewPrice(String plan)
		{
	
			String price = null ;
			switch(plan) {
			
			case "LITE" : price = getLiteSubscriptionPriceText.getText();
				break;
			case "CLASSIC": price = getClassicSubscriptionPriceText.getText();
				break;
			case "PREMIUM": price = getPremiumSubscriptionPriceText.getText();
				break;
			}
	
			return price;
		}
		
		public String getCurrency(String SubcriptionPrice)
		{
			String currency ,str;
			String[] parts=SubcriptionPrice.split("/");
			String partOne = parts[0];
			currency = partOne.replaceAll("[^a-zA-Z]","");
			return currency;
		}
		
}
