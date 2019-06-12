package com.frankbahar.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.frankbahar.utils.BaseClass;
import com.frankbahar.utils.CommonMethods;
import com.frankbahar.utils.ConfigsReader;

public class LoginPage extends BaseClass{
	//locating WebElements using @FindBy annotation
	@FindBy(id="txtUsername")
	public WebElement username;
	@FindBy(name=("txtPassword"))
	public WebElement password;
	@FindBy(id="btnLogin")
	public WebElement btnLogin;
	@FindBy(css="img[src*='logo.png']")
	public WebElement logo;
	@FindBy(xpath="//*[@id='toast-container']/div/div")
	public WebElement loginError;
	
	//initialize all of our variables
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String uname, String pwd) {
		CommonMethods.sendText(username, uname);
		CommonMethods.sendText(password, pwd);
		CommonMethods.click(btnLogin);
		return new HomePage();
	}
}
