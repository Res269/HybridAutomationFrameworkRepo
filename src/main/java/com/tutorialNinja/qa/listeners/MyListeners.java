package com.tutorialNinja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialNinjaproj.qa.utils.ExtentReporter;
import com.tutorialNinjaproj.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	ExtentReports extentreport;
	ExtentTest extenttest;
	WebDriver driver;
	String testName ;

	@Override
	public void onStart(ITestContext context)
	{
		 extentreport  = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		testName = result.getName();
		extenttest = extentreport.createTest(testName);
		extenttest.log(Status.INFO, testName+" test started successfully ");
	
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		
		extenttest.log(Status.PASS,testName+" test completed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		
		WebDriver driver = null;
		try 
		{
			 driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException | IllegalAccessException |NoSuchFieldException | SecurityException   e) {
			
			e.printStackTrace();
		} 
		
	    String destfolder= Utilities.captueScreenshot(driver, testName);
		extenttest.addScreenCaptureFromPath(destfolder);
		extenttest.log(Status.INFO,result.getThrowable());
		extenttest.log(Status.FAIL,testName+" test failed ");
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		
		extenttest.log(Status.INFO,result.getThrowable());
		extenttest.log(Status.SKIP, testName+" test skipped");
	
			
	}


	@Override
	public void onFinish(ITestContext context) 
	{
		extentreport.flush();
		
		String extentreportpath = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentreportfiledestpath = new File(extentreportpath);
		try 
		{
			Desktop.getDesktop().browse(extentreportfiledestpath.toURI());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	

}
