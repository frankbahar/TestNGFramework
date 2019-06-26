package com.frankbahar.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.frankbahar.pages.HomePage;
import com.frankbahar.pages.LoginPage;
import com.frankbahar.pages.LoginPageWithoutFactory;
import com.frankbahar.utils.BaseClass;
import com.frankbahar.utils.CommonMethods;
import com.frankbahar.utils.ConfigsReader;

public class LoginPageTest extends BaseClass {

	@Test(groups="smoke")
	public void loginToOrangeHRM() {
		LoginPageWithoutFactory login = new LoginPageWithoutFactory();
		test.info("Logging in wiht valid credentials");
		CommonMethods.sendText(login.username, "Admin");
		CommonMethods.sendText(login.password, "yMZW@aB17i");
		CommonMethods.click(login.btnLogin);
		test.info("Verifying dashboard text is displayed");
		HomePage home = new HomePage();
		boolean isDisplayed =home.dashboard.isDisplayed(); 
		Assert.assertTrue(isDisplayed);
		test.info("Successfuly logged in");
	}

	@Test(groups={"smoke"})
	public void doLogin() {
		LoginPage login = new LoginPage();
		CommonMethods.sendText(login.username, ConfigsReader.getProperty("username"));
		CommonMethods.sendText(login.password, ConfigsReader.getProperty("password"));
		CommonMethods.click(login.btnLogin);
		HomePage home = new HomePage();
	//	Assert.assertTrue(home.dashboard.isDisplayed());
		Assert.assertTrue(false);
	//	test.pass("login verification passed");
	}
	
	@Test(groups= {"regression"})
	public void invalidLogin() {
		LoginPage login = new LoginPage();
		CommonMethods.sendText(login.username, ConfigsReader.getProperty("username"));
		CommonMethods.sendText(login.password, ConfigsReader.getProperty("invalidpassword"));
		CommonMethods.click(login.btnLogin);
		Assert.assertTrue(login.loginError.isDisplayed());
	}
}
