package com.frankbahar.testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.frankbahar.pages.HomePage;
import com.frankbahar.pages.LoginPage;
import com.frankbahar.utils.BaseClass;
import com.frankbahar.utils.CommonMethods;
import com.frankbahar.utils.ConfigsReader;

public class LoginPageTest extends BaseClass {

	@Test(enabled=false)
	public void loginToOrangeHRM() {
		LoginPage login = new LoginPage();
		CommonMethods.sendText(login.username, "Admin");
		CommonMethods.sendText(login.password, "yMZW@aB17i");
		CommonMethods.click(login.btnLogin);
	}

	@Test
	public void doLogin() {
		LoginPage login = new LoginPage();
		CommonMethods.sendText(login.username, ConfigsReader.getProperty("username"));
		CommonMethods.sendText(login.password, ConfigsReader.getProperty("password"));
		CommonMethods.click(login.btnLogin);
		HomePage home = new HomePage();
		Assert.assertTrue(home.dashboard.isDisplayed());
	}
	
	@Test
	public void invalidLogin() {
		LoginPage login = new LoginPage();
		CommonMethods.sendText(login.username, ConfigsReader.getProperty("username"));
		CommonMethods.sendText(login.password, ConfigsReader.getProperty("invalidpassword"));
		CommonMethods.click(login.btnLogin);
		Assert.assertTrue(login.loginError.isDisplayed());
	}
}
