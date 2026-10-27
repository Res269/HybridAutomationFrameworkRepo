package com.tutorialNinjaproj.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialNinjaproj.qa.utils.Utilities;

public class Base {
	WebDriver driver ;
	public Properties prop ;
	public Properties dataprop ;
	
	public Base()
	{
		prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialNinja\\qa\\config\\config.properties");
	    
		
		dataprop = new Properties();
	    File datafile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialNinja\\qa\\testdata\\testdata.properties");
		
	    
	    try {
			FileInputStream datafis = new FileInputStream(datafile);
			dataprop.load(datafis);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	    
	    try
	    {
		    FileInputStream fis = new FileInputStream(file);
	        prop.load(fis);
	    }
	    catch(Throwable e)
	    {
		    e.printStackTrace();
	    }
	
	
	}
	
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName)
	{	
	if(browserName.equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
	}
	else if(browserName.equalsIgnoreCase("friefox"))
	{
		driver = new FirefoxDriver();
	}else if(browserName.equalsIgnoreCase("edge"))
	{
		driver=new EdgeDriver();
	}
		
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_Wait_Time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.Page_Load_Time));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
