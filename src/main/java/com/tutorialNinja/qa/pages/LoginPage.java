package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement EmailTextField;
	
	@FindBy(id="input-password")
	private WebElement PasswordTextField;
	
	@FindBy(css="[value='Login']")
	private WebElement LoginButtonField;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement noMatchEmailOrPasswordMessage;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//Action Methods
	public void enterEmailTextField(String emaildata)
	{
		EmailTextField.sendKeys(emaildata);
	}
	
	public void enterPasswordTextField(String passworddata)
	{
		PasswordTextField.sendKeys(passworddata);
	}
	
	public String retrieveEmailOrPasswordNoMatchMessage()
	{
		String WarningText =noMatchEmailOrPasswordMessage.getText();
		return WarningText;
	}
	
	
	
	
	
	
	
	
	
	public AccountPage loginclick()
	{
		LoginButtonField.click();
	    return new AccountPage(driver);
	}

}
