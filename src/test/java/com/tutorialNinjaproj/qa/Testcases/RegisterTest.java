package com.tutorialNinjaproj.qa.Testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.qa.pages.AccountSuccessPage;
import com.tutorialNinja.qa.pages.HomePage;
import com.tutorialNinja.qa.pages.RegisterPage;
import com.tutorialNinjaproj.qa.Base.Base;
import com.tutorialNinjaproj.qa.utils.Utilities;

public class RegisterTest extends Base {
	public WebDriver driver;
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	RegisterTest()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setUp()
	{   
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		homepage.clickonMyAccount();
		registerpage=homepage.clickonRegisterOption();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();	
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountUsingMandatoryFields()
	{
		
		registerpage.firstnametext(dataprop.getProperty("firstname"));
		registerpage.lastnametext(dataprop.getProperty("lastname"));
		registerpage.emailtext(Utilities.generateEmailWithTimeStamp());
		registerpage.telephonetext(dataprop.getProperty("telephone"));
		registerpage.passwordtext(dataprop.getProperty("password"));
		registerpage.confirmpasswordtext(dataprop.getProperty("cpassword"));
		registerpage.clickonCheckBox();
		accountsuccesspage= registerpage.clickonContinueButton();
		AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String actualSuccessMessage = accountsuccesspage.retrieveAccountCreatedSuccessMessage();
		Assert.assertEquals(actualSuccessMessage,dataprop.getProperty("accountCreatedSuccessMessage"),"Account Success Page is not displayed");
		
	}
	
	
	@Test(priority=2)
	public void verifyRegisteringAnAccountProvidingAllFields()
	{
	
		
		registerpage.firstnametext(dataprop.getProperty("firstname"));
		registerpage.lastnametext(dataprop.getProperty("lastname"));
		registerpage.emailtext(Utilities.generateEmailWithTimeStamp());
		registerpage.telephonetext(dataprop.getProperty("telephone"));
		registerpage.passwordtext(dataprop.getProperty("password"));
		registerpage.confirmpasswordtext(dataprop.getProperty("cpassword"));
		registerpage.clickonnewpapersubscribe();
		registerpage.clickonCheckBox();
		accountsuccesspage=registerpage.clickonContinueButton();
		
		String actualSuccessMessage = accountsuccesspage.retrieveAccountCreatedSuccessMessage();
		Assert.assertEquals(actualSuccessMessage,dataprop.getProperty("accountCreatedSuccessMessage"),"Account Success Page is not displayed");
		
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountUsingExistingEmail()
	{
	
		
		registerpage.firstnametext(dataprop.getProperty("firstname"));
		registerpage.lastnametext(dataprop.getProperty("lastname"));
		registerpage.emailtext(dataprop.getProperty("email"));
		registerpage.telephonetext(dataprop.getProperty("telephone"));
		registerpage.passwordtext(dataprop.getProperty("password"));
		registerpage.confirmpasswordtext(dataprop.getProperty("cpassword"));
		registerpage.clickonnewpapersubscribe();
		registerpage.clickonCheckBox();
		registerpage.clickonContinueButton();
		String actualWarningMessage = registerpage.retrieveexistingEmailAlertMessage();
		Assert.assertEquals(actualWarningMessage,dataprop.getProperty("existingEmailAlertMessage"),"Alert Message Mismatch");
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
		
		registerpage.clickonContinueButton();
		
		String actualPrivacyPolicyMessage = registerpage.retrieveprivacypolicymessage();
		Assert.assertTrue(actualPrivacyPolicyMessage.contains(dataprop.getProperty("privacyPolicyAlertMessage")),"Privacy policy not Displayed");
		
		String firstNameAlertMessage = registerpage.retrievefirstnamealertmessage();
		Assert.assertTrue(firstNameAlertMessage.contains(dataprop.getProperty("firstNameAlertMessage")),"FirstName not Displayed");
		
		String lastNameAlertMessage = registerpage.retrievelastnamealertmessage();
		Assert.assertTrue(lastNameAlertMessage.contains(dataprop.getProperty("lastNameAlertMessage")),"LastName Not Displayed");
		
		String emailAlertMessage = registerpage.retrieveemailalertmessage();
		Assert.assertTrue(emailAlertMessage.contains(dataprop.getProperty("emailAlertMessage")),"Email Field Not Displayed");
		
		String telephoneAlertMessage = registerpage.retrievetelephonealertmessage();
		Assert.assertTrue(telephoneAlertMessage.contains(dataprop.getProperty("telephoneAlertMessage")),"Telephone Not Displayed");
		
		String passwordAlertMessage = registerpage.retrievepassalertmessage();
		Assert.assertTrue(passwordAlertMessage.contains(dataprop.getProperty("passwordAlertMessage")),"Password Nor Displayed");
	
	}
	
	
	

}
