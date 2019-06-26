package com.frankbahar.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReview {
	
	@Test
	public void testExcelReader() throws IOException {
		String xlPath="src/test/resources/testdata/OrangeHRMData.xlsx";
		
		FileInputStream fis = new FileInputStream(xlPath);
		//open workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		//open specified sheet
		XSSFSheet sheet = workbook.getSheet("EmployeeDetails");
		//reach value of specified cell
		String value = sheet.getRow(6).getCell(0).toString();
		System.out.println(value);
		//get num of rows and cols
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();
		System.out.println("Number of rows " + rows + " cols " + cols);
		for(int a=0; a<rows; a++) {
			for(int b=0; b<cols; b++) {
				if(sheet.getRow(a).getCell(b) != null) {
					String cellValue = sheet.getRow(a).getCell(b).toString();	
					System.out.print(cellValue+ " ");
				}
			}
			System.out.println();
		}
		workbook.close();
		fis.close();
	}

}
