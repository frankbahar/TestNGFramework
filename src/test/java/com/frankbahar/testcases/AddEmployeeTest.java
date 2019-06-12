package com.frankbahar.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.frankbahar.pages.AddEmployee;
import com.frankbahar.pages.HomePage;
import com.frankbahar.pages.LoginPage;
import com.frankbahar.utils.BaseClass;
import com.frankbahar.utils.CommonMethods;
import com.frankbahar.utils.ConfigsReader;

public class AddEmployeeTest extends BaseClass {
	String expectedEmpID;

	@Test
	public void addEmployeeTest() throws InterruptedException {
		LoginPage login = new LoginPage();
		HomePage home = login.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
		CommonMethods.click(home.PIM);
		CommonMethods.click(home.addEmployee);
		AddEmployee addEmployee = new AddEmployee();
		CommonMethods.sendText(addEmployee.firstName, "Enes");
		CommonMethods.sendText(addEmployee.lastName, "Kanter");
		CommonMethods.click(addEmployee.location);
		// 1 identify list
		// 2 get all children with li tags
		// 3 loop through each li tag and get text
		// 4 if text is matching then we click
		// break
		// this is for dropdown which is not using select tag
		List<WebElement> listLocations = addEmployee.locationList.findElements(By.tagName("li"));

		for (WebElement li : listLocations) {
			String liText = li.getAttribute("innerHTML");
			if (liText.contains("NYC")) {
				li.click();
				break;
			}
		}
		WebDriverWait wait = new WebDriverWait(driver, 50);
		expectedEmpID = addEmployee.empID.getAttribute("value");
		CommonMethods.click(addEmployee.saveEmployee);
		// Thread.sleep(3000);
		// driver.findElement(By.xpath("//a[@ng-click='modal.cancel()']")).click();
		home.driver.navigate().refresh();
		// wait.until(ExpectedConditions.elementToBeClickable(home.employeeList));
		// wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(home.employeeList)));
		CommonMethods.click(home.employeeList);
		CommonMethods.sendText(home.empSearchBox, expectedEmpID);
		wait.until(ExpectedConditions.elementToBeClickable(home.empSearchBtn));
		WebElement table = driver.findElement(By.xpath("//table[@id='employeeListTable']/tbody/tr[1]"));
		CommonMethods.click(home.empSearchBtn);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(table)));
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='employeeListTable']/tbody/tr"));
		Assert.assertNotEquals(rows.size(), 0);
	}
}
