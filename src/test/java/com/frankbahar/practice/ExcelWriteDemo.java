package com.frankbahar.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelWriteDemo {
	
	@Test
	public void writeExcel() throws IOException {
		String xlPath = "src/test/resources/testdata/OrangeHRMData.xlsx";
		FileInputStream fis = new FileInputStream(xlPath);
		//open workbook & sheet
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("EmployeeDetails");
		sheet.getRow(0).createCell(4).setCellValue("Result");
		sheet.getRow(1).createCell(4).setCellValue("Pass");
		sheet.getRow(1).getCell(4).setCellValue("Fail");
		sheet.createRow(10).createCell(0).setCellValue("Mehmet");
		//write to Excel
		FileOutputStream fos = new FileOutputStream(xlPath);
		workbook.write(fos);
		fos.close();
		workbook.close();
		fis.close();
	}
}
