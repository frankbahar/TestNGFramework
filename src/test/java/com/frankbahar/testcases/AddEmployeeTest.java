package com.frankbahar.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.frankbahar.pages.AddEmployee;
import com.frankbahar.pages.HomePage;
import com.frankbahar.pages.LoginPage;
import com.frankbahar.utils.BaseClass;
import com.frankbahar.utils.CommonMethods;
import com.frankbahar.utils.ConfigsReader;
import com.frankbahar.utils.Constants;
import com.frankbahar.utils.ExcelUtility;

public class AddEmployeeTest extends BaseClass {
	String expectedEmpID;
	
	@Test(dataProvider="Employee Data", groups= {"regression"})
	public void addEmployeeTest(String fName, String mName, String lName, String loc) throws InterruptedException {
		LoginPage login = new LoginPage();
		//Login OrangeHRM website
		HomePage home = login.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
		CommonMethods.click(home.PIM);
		CommonMethods.click(home.addEmployee);
		AddEmployee addEmployee = new AddEmployee();
		//Enter employee details
		CommonMethods.sendText(addEmployee.firstName, fName);
		CommonMethods.sendText(addEmployee.middleName, mName);
		CommonMethods.sendText(addEmployee.lastName, lName);
	
		CommonMethods.click(addEmployee.location);
		// 1 identify list
		// 2 get all children with li tags
		// 3 loop through each li tag and get text
		// 4 if text is matching then we click
		// break
		CommonMethods.selectList(addEmployee.locationList, loc);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		expectedEmpID = addEmployee.empID.getAttribute("value");
		CommonMethods.click(addEmployee.saveEmployee);
		//Verify employee added
		home.driver.navigate().refresh();
		CommonMethods.click(home.employeeList);
		CommonMethods.sendText(home.empSearchBox, expectedEmpID);
		wait.until(ExpectedConditions.elementToBeClickable(home.empSearchBtn));
		WebElement table = driver.findElement(By.xpath("//table[@id='employeeListTable']/tbody/tr[1]"));
		CommonMethods.click(home.empSearchBtn);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(table)));
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='employeeListTable']/tbody/tr"));
		Assert.assertNotEquals(rows.size(), 0);
	}
	
	@DataProvider(name="employee details")
	public Object[][] getData(){
		Object[][] data = new Object[3][4];
		
		data[0][0] = "John";
		data[0][1] = "Snow";
		data[0][2] = "White";
		data[0][3] = "NYC";
		
		data[1][0] = "Jack";
		data[1][1] = "Snow";
		data[1][2] = "White";
		data[1][3] = "NYC";
	
		data[2][0] = "John";
		data[2][1] = "Spring";
		data[2][2] = "White";
		data[2][3] = "NYC";
	
		return data;
	}
	
	@DataProvider(name="Employee Data")
	public Object[][] getEmpData(){
		ExcelUtility obj = new ExcelUtility();
		obj.openExcel(Constants.EXCEL_FILEPATH, "EmployeeDetails");
		int rows = obj.getRowNum();
		int cols = obj.getColNum(0);
		Object[][] data = new Object[rows-1][cols];
		for(int i=1; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				data[i-1][j]=obj.getCellData(i, j);
			}
		}
		obj.closeExcel();
		return data;
	}
}
