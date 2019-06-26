package com.frankbahar.utils;

import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;


public class Listeners extends BaseClass implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) { // evermethod 
		System.out.println( "On Test started: " + result.getName());
		test = report.createTest(result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	//	System.out.println("Test Case passed: " + result.getName());
		test.pass("Test Case passed: " + result.getName());	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println("Test Case failed: " + result.getName());
		test.fail("Test Case failed: " + result.getName());
		String imagePath = CommonMethods.takeScreenshot(result.getName());
		try {
			test.addScreenCaptureFromPath(imagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Case skipped: " + result.getName());

		test.log(Status.INFO, "Test Case skipped: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) { // on suite level
		System.out.println("onStart  started: " + context.getName());
			
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("onFinshed finished: " + context.getName());
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub		
	}
}
