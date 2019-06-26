package com.frankbahar.practice;

import org.testng.annotations.Test;

import com.frankbahar.utils.Constants;
import com.frankbahar.utils.ExcelUtility;

public class ExcelFunctionsTest {
	@Test
	public void excelTest() {
		ExcelUtility obj = new ExcelUtility();
		obj.openExcel(Constants.EXCEL_FILEPATH, "EmployeeDetails");
		//String value = obj.getCellData(2, 3);
	//	System.out.println(value);
		//retrieve all values from xl and store in to dataProvider(2D Object arr
		int row = obj.getRowNum();
		int col = obj.getColNum(0);
		Object[][] data = new Object[row-1][col];
		for(int i=1; i<row; i++) {
			for(int j=0; j<col;j++) {
				data[i-1][j]=obj.getCellData(i, j);
			}
		}
		System.out.println(data.length);
		for(int i=0; i<row-1; i++) {
			for(int j=0; j<col;j++) {
				System.out.print(data[i][j]+ " ");
			}
			System.out.println();
		}
		obj.closeExcel();
	}
}
