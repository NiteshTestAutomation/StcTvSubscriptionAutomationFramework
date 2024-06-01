package com.stctvsubscription.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting {

	public static ExtentReports extentReporting()
	{
		
		ExtentReports extentReport = new ExtentReports();
		
		File fp = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fp);
		
		sparkReporter.config().setTheme(Theme.DARK);
		
		sparkReporter.config().setReportName("STC Automation Report");
		
		sparkReporter.config().setDocumentTitle("STC Test Automation");
		
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		extentReport.setSystemInfo("Application Url ","test");
		
		extentReport.setSystemInfo("Browser Name ","chrome");
		
		extentReport.setSystemInfo("Tester Name ","Nitesh");
		return extentReport;
		
		//extentReport.setSystemInfo("","");
		
	}
}
