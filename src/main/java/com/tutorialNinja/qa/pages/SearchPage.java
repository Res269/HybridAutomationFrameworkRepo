package com.tutorialNinja.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	
	
	@FindBy(linkText="HP LP3065")
	private WebElement hpcontent;
	
	@FindBy(xpath="//div[@id='content']//descendant::p[2]")
	private WebElement invalidProductID;
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
	public boolean hpcontentisdisplayed()
	{
		return hpcontent.isDisplayed();
	}
	
	public String retrieveinvalidProductID()
	{
		String ivalidprodmessage =invalidProductID.getText();
		return ivalidprodmessage;
	}
	
	
}
