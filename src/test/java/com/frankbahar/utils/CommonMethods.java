package com.frankbahar.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends BaseClass {

	/**
	 * @author Frank Bahar This method will click the element specified
	 * @param Select element
	 */
	public static void click(WebElement element) {
		element.click();
	}

	public static void selectList(WebElement element, String text) {
		// this is for dropdown which is not using select tag
		List<WebElement> listLocations = element.findElements(By.tagName("li"));

		for (WebElement li : listLocations) {
			String liText = li.getAttribute("innerHTML");
			if (liText.contains(text)) {
				li.click();
				break;
			}
		}
	}

	/**
	 * @author Frank Bahar This method will select a specified value from a drop
	 *         down by text
	 * @param Select element, String text
	 */
	public static void selectValueFromDD(WebElement element, String text) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		boolean isElementSelected = false;
		for (WebElement option : options) {
			String optionText = option.getText();
			if (optionText.equals(text)) {
				select.selectByVisibleText(text);
				System.out.println("Option with text " + text + " is selected");
				isElementSelected = true;
				break;
			}
		}
		if (!isElementSelected) {
			System.out.println("Option with text " + text + " is not available");
		}
	}

	/**
	 * @author fezac This method will select a specified value from a drop down by
	 *         index
	 * @param Select element, String text
	 */
	public static void selectValueFromDD(WebElement element, int index) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		if (options.size() > index) {
			select.selectByIndex(index);
		} else {
			System.out.println("Invalid index has been passed");
		}
	}

	public static void sendText(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	/**
	 * @author fezac Method will accept alert
	 * @throws NoAlertPresentException if alert is not present
	 */
	public static void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present ");
		}
	}

	/**
	 * @author fezac Method will dismiss alert
	 * @throws NoAlertPresentException if alert is not present
	 */
	public static void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present ");
		}
	}

	/**
	 * @author fezac Method will get text of an alert
	 * @throws NoAlertPresentException if alert is not present
	 * @return String text
	 */
	public static String getAlertText() {
		try {
			Alert alert = driver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present ");
			return null;
		}
	}

	/**
	 * @author fezac Method will switch control to the specified frame by using Id
	 *         or name
	 * @param Id or Name
	 * @throws NoAlertPresentException if frame is not present
	 */
	public static void switchToFrame(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame was not present");
		}
	}

	/**
	 * @author fezac Method will switch control to the specified frame by using
	 *         WebElement
	 * @param WebElement
	 * @throws NoAlertPresentException if frame is not present
	 */
	public static void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame was not present");
		}
	}

	/**
	 * @author fezac Method will switch control to the specified frame by index
	 * @param int index
	 * @throws NoAlertPresentException if frame is not present
	 */
	public static void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame was not present");
		}
	}

	/**
	 * @author Frank Bahar This method will select a specified value from a drop
	 *         down by text
	 * @param Select List<WebElement>, String text
	 */
	public static void selectValueFromRadioButton(List<WebElement> options, String text) {
		boolean isElementSelected = false;
		for (WebElement option : options) {
			if (option.isEnabled()) {
				String value = option.getAttribute("value");
				if (value.equals(text)) {
					option.click();
					isElementSelected = true;
					break;
				}
			}
		}
		if (!isElementSelected) {
			System.out.println("Option with text " + text + " is not available");
		}
	}

	/**
	 * @author Frank Bahar This method will select a specified values from a
	 *         checkbox
	 * @param Select <List<WebElement>, List<String> text
	 */
	public static void selectValueFromCheckbox(List<WebElement> options, List<String> texts) {
		boolean isElementSelected = false;
		for (String text : texts) {
			for (WebElement option : options) {
				if (option.isEnabled()) {
					String value = option.getAttribute("value");
					if (value.equals(text)) {
						option.click();
						isElementSelected = true;
					}
				}
			}
		}
		if (!isElementSelected) {
			System.out.println("No option is available with any text requested could not select");
		}
	}

	/**
	 * @author Frank Bahar Method that will wait for element to be visible
	 * 
	 * @param WebElement element, int time
	 */
	public static void waitForElementBeVisible(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * @author Frank Bahar Method that will wait for element to be visible
	 * 
	 * @param By locator, int time
	 */
	public static void waitForElementBeVisible(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * @author Frank Bahar Method that will wait for element to be clickable
	 * 
	 * @param WebElement element, int time
	 */
	public static void waitForElementBeClickable(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * @author Frank Bahar Method that will wait for element to be clickable
	 * 
	 * @param By locator, int time
	 */
	public static void waitForElementBeClickable(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * @author Frank Bahar This method will take a screenshot
	 * 
	 * @param String filePath
	 */
	public static String takeScreenshot(String filePath) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File upload = ts.getScreenshotAs(OutputType.FILE);
		String dest= System.getProperty("user.dir") + "/target/screenshots/" + filePath + ".png";
		try {
			FileUtils.copyFile(upload, new File(dest));
		} catch (IOException e) {
			System.out.println("Screenshot could not captured");
			e.printStackTrace();
		}
		return dest;
	}

	/**
	 * @author Frank Bahar This method will scroll down to given pixel
	 * 
	 * @param int pixels
	 */
	public static void scrollDown(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixels + ")");
	}

	/**
	 * @author Frank Bahar This method will scroll up to given pixel
	 * 
	 * @param int pixels
	 */
	public static void scrollUp(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-" + pixels + ")");
	}

	/**
	 * @author Frank Bahar This method will scroll up to given element visibility
	 * 
	 * @param WebElement element
	 */
	public static void scrollIntoView(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * @author Frank Bahar This method will click element by Javascript
	 * 
	 *         WebElement element
	 */
	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

	/**
	 * @author Frank Bahar return title of the page by Javascript
	 * 
	 * @return String title
	 */
	public static String getTitleByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}
}