package com.frankbahar.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.frankbahar.pages.AddLocation;
import com.frankbahar.pages.HomePage;
import com.frankbahar.pages.LoginPage;
import com.frankbahar.utils.BaseClass;
import com.frankbahar.utils.CommonMethods;
import com.frankbahar.utils.ConfigsReader;
import com.frankbahar.utils.Constants;

public class AddLocationTest extends BaseClass {

	@Test(dataProvider = "locations", groups="regression")
	public void addLocationTest(String name, String country, String city, String zip) {
		LoginPage login = new LoginPage();
		// Login OrangeHRM website
		HomePage home = login.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
		CommonMethods.click(home.adminLink);
		CommonMethods.click(home.organization);
		CommonMethods.click(home.locationsLink);
		CommonMethods.click(home.locationAdd);
		AddLocation location = new AddLocation();
		// enter location info
		CommonMethods.sendText(location.name, name);
		CommonMethods.click(location.country);
		CommonMethods.selectList(location.countryList, country);
		CommonMethods.sendText(location.city, city);
		CommonMethods.sendText(location.zip, zip);
		CommonMethods.click(location.saveLocationBtn);
		home.driver.navigate().refresh();
		boolean locAdded = false;
		for (WebElement we : home.locationList) {
			String actualname = we.getText();
			if (actualname.equals(name)) {
				locAdded = true;
				break;
			}
		}
		
		Assert.assertTrue(locAdded, name + " could not added");
	}

	@DataProvider(name = "locations")
	public Object[][] getData() {
		FileInputStream fis = null;
		;
		XSSFWorkbook workbook = null;

		try {
			fis = new FileInputStream(Constants.EXCEL_FILEPATH);
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet("Locations");
		int rowNum = sheet.getPhysicalNumberOfRows();
		int colNum = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowNum - 1][colNum];
		for (int i = 1; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				XSSFCell cell = sheet.getRow(i).getCell(j);
				if (cell != null) {
					data[i - 1][j] = cell.toString();
				} else {
					data[i - 1][j] = "";
				}
			}
		}
		try {
			workbook.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
