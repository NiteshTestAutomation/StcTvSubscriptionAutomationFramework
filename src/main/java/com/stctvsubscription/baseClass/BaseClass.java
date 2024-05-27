/**
 * 
 */
package com.stctvsubscription.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.stctvsubscription.utils.Utilities;


public class BaseClass {
	
	WebDriver driver;
	
	public Properties property;

	public WebDriver setupBrowserAndOpenWebApplication(String browser)
	{
		if(driver == null) {
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
       	}else if(browser.equalsIgnoreCase("ie")) {
       		driver = new InternetExplorerDriver();
       	}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicitWaitTime));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageLoadWaitTime));
		
		driver.get(property.getProperty("url"));
		
		return driver;
	}
	
	@BeforeTest
	public void loadConfigPropertiesFile()
	{
		FileInputStream fis;
		
		property = new Properties();
		
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuaration\\Config.properties");
		
		try {
			fis = new FileInputStream(file);
			
			property.load(fis);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	 
	 @AfterTest
	 public void tearDown()
	 {
		getScreenshot(driver);
		driver.quit();
	 }
	
	
	 public void getScreenshot(WebDriver driver)
		{
			try {
				//driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (Exception e)
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String destinationScreenshotPath = System.getProperty("user.dir")+"\\screenshot\\"+System.currentTimeMillis()+"screnshot.png";
		
			try {
				FileHandler.copy(screenshot, new File(System.getProperty("user.dir")+"\\screenshot\\"+System.currentTimeMillis()+"screnshot.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
