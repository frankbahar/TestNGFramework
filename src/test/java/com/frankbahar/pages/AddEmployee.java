package com.frankbahar.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.frankbahar.utils.BaseClass;

public class AddEmployee extends BaseClass {
	@FindBy(id = "firstName")
	public WebElement firstName;
	@FindBy(css = "#middleName")
	public WebElement middleName;
	@FindBy(css = "#lastName")
	public WebElement lastName;
	@FindBy(css = "#systemUserSaveBtn")
	public WebElement saveEmployee;
	@FindBy(xpath = "//div[@id='location_inputfileddiv']//input")
	public WebElement location;
	@FindBy(xpath = "//div[@id='location_inputfileddiv']//ul")
	public WebElement locationList;
	@FindBy(css="#employeeId")
	public WebElement empID;

	public AddEmployee() {
		PageFactory.initElements(driver, this);
	}
}
