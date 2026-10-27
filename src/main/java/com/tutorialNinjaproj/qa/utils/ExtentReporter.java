package com.tutorialNinjaproj.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() 
	{
		ExtentReports extentreport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("TN Automation Report");
		spark.config().setReportName("TutorialNinja Automation Test Result Report");
		spark.config().setTimeStampFormat("DD/MM/YYYY HH:MM:SS");
		
		extentreport.attachReporter(spark);
		
		
		Properties prop = new Properties();
		File extentreportfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialNinja\\qa\\config\\config.properties");
		try
		{
		FileInputStream extentreportfis = new FileInputStream(extentreportfile);
		prop.load(extentreportfis);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		extentreport.setSystemInfo("Application URL",prop.getProperty("url"));
		extentreport.setSystemInfo("Browser Name ", prop.getProperty("browserName"));
		extentreport.setSystemInfo("Valid Email Id ","validEmail" );
		extentreport.setSystemInfo("Valid Password","validPassword");
		
		//System.getProperties().list(System.out);
		
	extentreport.setSystemInfo("Operating System ", System.getProperty("os.name"));
	extentreport.setSystemInfo("Java Version ",System.getProperty("java.version"));
	extentreport.setSystemInfo("User Name ", System.getProperty("user.name"));
	
	return extentreport;	
		
	}

}
