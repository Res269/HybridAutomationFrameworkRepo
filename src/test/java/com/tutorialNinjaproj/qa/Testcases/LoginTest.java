package com.tutorialNinjaproj.qa.Testcases;

import java.sql.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.pages.AccountPage;
import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.LoginPage;
import com.tutorialNinjaproj.qa.Base.Base;
import com.tutorialNinjaproj.qa.utils.Utilities;

public class LoginTest extends Base{
	public WebDriver driver;
	LoginPage loginpage;
	AccountPage accountpage;
	LoginTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp()
	{  
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		homepage.clickonMyAccount();
		loginpage=homepage.clickonLoginOption();
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();	
	}
	
	@Test(priority=1,dataProvider="validCredentialSupplyData")
	public void verifyLoginWithValidCredentials(String Email,String Password)
	{
		
		
		loginpage.enterEmailTextField(Email);
		loginpage.enterPasswordTextField(Password);
		accountpage=loginpage.loginclick();
		Assert.assertTrue(accountpage.checkEditYoutAccountInformationIsDisplayed(),"Edit You Account Information Not Displayed");
		
	}
	
	@DataProvider(name ="validCredentialSupplyData")
	public Object[][] supplyTestData()
	{
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{
		
		AccountPage accountpage = new AccountPage(driver);
		loginpage.enterEmailTextField(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPasswordTextField(dataprop.getProperty("invalidPassword"));
		loginpage.loginclick();
		String actualErrorMessage=loginpage.retrieveEmailOrPasswordNoMatchMessage();
	
		Assert.assertEquals(actualErrorMessage, dataprop.getProperty("noMatchWarningMessage"),"Error message Not Matched");
	
	}

	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{   
	    loginpage.enterEmailTextField(Utilities.generateEmailWithTimeStamp());
		loginpage.enterPasswordTextField(prop.getProperty("validPassword"));
	    loginpage.loginclick();
	    String actualErrorMessage=loginpage.retrieveEmailOrPasswordNoMatchMessage();
	    Assert.assertEquals(actualErrorMessage, dataprop.getProperty("noMatchWarningMessage"),"Error message Not Matched");

	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndvInValidPassword()
	{
		
		loginpage.enterEmailTextField(prop.getProperty("validEmail"));
		loginpage.enterPasswordTextField(dataprop.getProperty("invalidPassword"));
		loginpage.loginclick();
		String actualErrorMessage=loginpage.retrieveEmailOrPasswordNoMatchMessage();
		Assert.assertEquals(actualErrorMessage, dataprop.getProperty("noMatchWarningMessage"),"Error message Not Matched");
		
	}
	
	@Test(priority=5)
	public void VerifyLoginWithoutProvidingCredentials()
	{
		
		loginpage.loginclick();
		String actualErrorMessage=loginpage.retrieveEmailOrPasswordNoMatchMessage();
		Assert.assertEquals(actualErrorMessage, dataprop.getProperty("noMatchWarningMessage"),"Error message Not Matched");
		
		
		
	}

}
