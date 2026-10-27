package com.tutorialNinjaproj.qa.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.SearchPage;
import com.tutorialNinjaproj.qa.Base.Base;

public class SearchTest extends Base{

	public WebDriver driver ;
	SearchPage searchpage;
	SearchTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp()
	{
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}
	
@AfterMethod
public void tearDown()
{
	driver.quit();
}

@Test(priority=1)
public void verifySearchUsingValidInput()
{   HomePage homepage = new HomePage(driver);
	homepage.searchfieldtextentry(dataprop.getProperty("validSearchData"));
	searchpage=homepage.clickOnSearchField();
	Assert.assertTrue(searchpage.hpcontentisdisplayed(),"HP product Not Displayed");
}

@Test(priority=2)
public void verifySearchUsingInvalidInput()
{ HomePage homepage = new HomePage(driver);
	
	homepage.searchfieldtextentry(dataprop.getProperty("invalidSearchData"));
	searchpage=homepage.clickOnSearchField();
	String ErrorMessage =searchpage.retrieveinvalidProductID();
	//Assert.assertEquals(ErrorMessage, dataprop.getProperty("searchAlertMessage"),"Invalid Product Error message Not Displayed");
	Assert.assertEquals(ErrorMessage, "abcd","Invalid Product Error message Not Displayed");
}
@Test(priority=3,dependsOnMethods= {"verifySearchUsingValidInput","verifySearchUsingInvalidInput"})
public void verifySearchWithoutAnyProduct()
{ 
	HomePage homepage = new HomePage(driver);
    searchpage=homepage.clickOnSearchField();
	String ErrorMessage =searchpage.retrieveinvalidProductID();
	Assert.assertEquals(ErrorMessage, dataprop.getProperty("searchAlertMessage"),"Invalid Product Error message Not Displayed");
	
	
}
}

