package com.frankbahar.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.frankbahar.utils.BaseClass;

public class HomePage extends BaseClass{
	@FindBy(xpath="//li[text()='Dashboard']")
	public WebElement dashboard;
	@FindBy(xpath="//*[@id=\"menu_pim_viewPimModule\"]/a/span[3]")
	public WebElement PIM;
	@FindBy(xpath="//span[text()='Add Employee']")
	public WebElement addEmployee;
	@FindBy(xpath="//a[@id='menu_pim_viewEmployeeList']")
	public WebElement employeeList;
	@FindBy(css="#employee_name_quick_filter_employee_list_value")
	public WebElement empSearchBox;
	@FindBy(xpath="//a[@ng-click='navbar.search()']")
	public WebElement empSearchBtn;
	@FindBy(xpath="//table[@id='employeeListTable']/tbody/tr")
	public WebElement empRows;
	@FindBy(xpath="//*[@id=\"menu_admin_viewAdminModule\"]/a/span[3]")
	public WebElement adminLink;
	@FindBy(xpath="//*[@id=\"menu_admin_Organization\"]/a/span[3]")
	public WebElement organization;
	@FindBy(xpath="//*[@id=\"menu_admin_viewLocations\"]/span[2]")
	public WebElement locationsLink;
	@FindBy(xpath="//*[@id=\"locationDiv\"]/div/a/i")
	public WebElement locationAdd;
	@FindBy(xpath="//table[@class='highlight bordered']/tbody/tr/td[2]")
	public List<WebElement> locationList;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

}
