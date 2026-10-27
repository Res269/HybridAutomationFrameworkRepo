package com.tutorialNinja.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	@FindBy(css="[title='My Account']")
	private WebElement MyAccountdropdown;
	
	@FindBy(linkText="Login")
	private WebElement LoginOption;
	
	@FindBy(linkText="Register")
	private WebElement RegisterOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchboxfield;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//Actions 
	public void clickonMyAccount()
	{
		MyAccountdropdown.click();
	}
	
	public LoginPage clickonLoginOption()
	{
		LoginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickonRegisterOption()
	{
		RegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public void searchfieldtextentry(String data)
	{
		searchboxfield.sendKeys(data);
	}
	public SearchPage clickOnSearchField()
	{
		searchboxfield.sendKeys(Keys.ENTER);
	    return new SearchPage(driver);
	}
	

}
