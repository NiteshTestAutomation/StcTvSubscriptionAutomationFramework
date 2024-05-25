/**
 * 
 */
package com.stctvsubscription.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * 
 */
public class ExtentReport {
	
		
		public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();	
			
		File extentReportFile = new File(System.getProperty("user.dir")+"test-output\\Extent-Report\\extentReport.html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		
		sparkReporter.config().setReportName("stc TV Subscription Automation Test Report");
		
		sparkReporter.config().setDocumentTitle("stc Test Automation Report");
		
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProperty = new Properties();
		
		File configFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuaration\\Config.properties");

		try {
			FileInputStream fis = new FileInputStream(configFile);
			configProperty.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application Url",configProperty.getProperty("url"));
		
		extentReport.setSystemInfo("BrowserName ",configProperty.getProperty("browserName"));
		
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
		
		extentReport.setSystemInfo("User Name",System.getProperty("user.name"));
		
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentReport;
		}
		
}
