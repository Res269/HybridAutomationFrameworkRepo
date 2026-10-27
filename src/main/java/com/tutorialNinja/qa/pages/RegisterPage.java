package com.tutorialNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver ;
	
	@FindBy(id="input-firstname")
	private WebElement firstname;

	@FindBy(id="input-lastname")
	private WebElement lastname;
	
	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-telephone")
	private WebElement telephone;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(id="input-confirm")
	private WebElement confirmpassword;
	
	@FindBy(xpath="//input[@type='checkbox']")
	private WebElement checkbox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continuebutton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newpapersubscribe;
	
	@FindBy(css="[class='alert alert-danger alert-dismissible']")
	private WebElement existingEmailAlertMessage;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacypolicymessage;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnamealertmessage;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailalertmessage;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephonealertmessage ;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastnamealertmessage;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passalertmessage1;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
	    PageFactory.initElements(driver,this);
	}
	
	
	//Actions 
	public void firstnametext(String fn)
	{
		firstname.sendKeys(fn);
	}
	
	public void lastnametext(String ln)
	{
		lastname.sendKeys(ln);
	}
	
	public void emailtext(String mailid)
	{
		email.sendKeys(mailid);
	}
	
	public void telephonetext(String num)
	{
		telephone.sendKeys(num);
	}
	
	public void passwordtext(String pw)
	{
		password.sendKeys(pw);
	}
	
	public void confirmpasswordtext(String cpw)
	{
		confirmpassword.sendKeys(cpw);
	}
	
	public void clickonCheckBox()
	{
		checkbox.click();
	}
	
	public AccountSuccessPage clickonContinueButton()
	{
		continuebutton.click();	
		return new AccountSuccessPage(driver);
	}
	
	
	
	public void clickonnewpapersubscribe()
	{
		newpapersubscribe.click();
	}
	
	public String retrieveexistingEmailAlertMessage()
	{
		String alertmessage =existingEmailAlertMessage.getText();
		return alertmessage;
	}
	
	public String retrieveprivacypolicymessage()
	{
		String ppmes = privacypolicymessage.getText();
		return ppmes;
	}
	
	public String retrievefirstnamealertmessage()
	{
		String fnmes = firstnamealertmessage.getText();
		return fnmes;
	}
	
	public String retrievelastnamealertmessage()
	{
		String lnmes = lastnamealertmessage.getText();
		return lnmes;
	}
	
	public String retrieveemailalertmessage()
	{
		String emes = emailalertmessage.getText();
		return emes;
	}
	
	public String retrievetelephonealertmessage()
	{
		String tmes = telephonealertmessage.getText();
		return tmes;
	}
	
	//passalertmessage1
	public String retrievepassalertmessage()
	{
		String pmes = passalertmessage1.getText();
		return pmes;
	}
	
	
	
	
}
