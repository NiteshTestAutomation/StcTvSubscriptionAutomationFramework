package com.stctvsubscription.listners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.stctvsubscription.baseClass.BaseClass;
import com.stctvsubscription.utils.Reporting;


public class ListenerClass implements ITestListener	{
	
	ExtentReports extent ;
	ExtentTest extentTest;
	WebDriver driver;
	
	
	
	@Override
	public void onStart(ITestContext context) {
		extent = Reporting.extentReporting();
		//extentTest.createNode(context);
	
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest =  extent.createTest(testName);
		extentTest.log(Status.INFO,testName+" started Execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String testName = result.getName();		
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.PASS, testName+ " Testcase Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
//		System.out.println(testName + "Testcase Failure");
		//extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testName+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	    String testName = result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,testName+" got skipped");
	}

	

	@Override
	public void onFinish(ITestContext context) {
	
		extent.flush();
		
	}

	
}
	
