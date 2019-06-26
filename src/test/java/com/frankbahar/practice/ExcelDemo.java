package com.frankbahar.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDemo {

	@Test
	public void readExcel() throws IOException {
		String xlPath = "src/test/resources/testdata/OrangeHRMData.xlsx";
		FileInputStream fis = new FileInputStream(xlPath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("EmployeeDetails");
		// accessing 2 row 4 column
		String value = sheet.getRow(1).getCell(3).toString();
		System.out.println(value);
		// access 5 row and 1 column
		String value2 = sheet.getRow(4).getCell(0).toString();
		System.out.println(value2);
		// how to find number of rows
		int rowNum = sheet.getPhysicalNumberOfRows();
		// how to find nummber of cells
		int colNum = sheet.getRow(0).getLastCellNum();
		System.out.println("Row count :" + rowNum);
		System.out.println("Column count : " + colNum);
		// retrieve all data
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				XSSFCell cell = sheet.getRow(i).getCell(j);
				if (cell != null)
					System.out.print(cell.toString() + " ");
			}
			System.out.println();
		}
		workbook.close();
		fis.close();
	}

	@Test(dataProvider = "locations")
	public void writeExcel(String name, String country, String city, String zip) throws IOException {
		String[] value = new String[4];
		value[0]=name;
		value[1]=country;
		value[2]=city;
		value[3]=zip;
		String xlPath = "src/test/resources/testdata/OrangeHRMData.xlsx";
		FileInputStream fis = new FileInputStream(xlPath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Locations");
		// how to find number of rows
		int rowNum = sheet.getPhysicalNumberOfRows();
		// how to find nummber of cells
		int colNum = sheet.getRow(0).getLastCellNum();
		XSSFRow newRow = sheet.createRow(rowNum + 1);
		// retrieve all data
		for (int j = 0; j < colNum; j++) {
			XSSFCell cell = newRow.createCell(j);
			cell.setCellValue(value[j]);
		}
		fis.close();
		FileOutputStream fos = new FileOutputStream(xlPath);
		workbook.write(fos);
		workbook.close();
		fos.close();
		
	}

	@DataProvider(name = "locations")
	public Object[][] getData() {
		Object[][] data = new Object[5][4];

		data[0][0] = "Asia HQ";
		data[0][1] = "Japan";
		data[0][2] = "Tokyo";
		data[0][3] = "12345";

		data[1][0] = "Africa HQ";
		data[1][1] = "South Africa";
		data[1][2] = "Johannesburg";
		data[1][3] = "12345";

		data[2][0] = "Balkans HQ";
		data[2][1] = "Albania";
		data[2][2] = "Tiran";
		data[2][3] = "12345";

		data[3][0] = "Russia HQ";
		data[3][1] = "Azerbaijan";
		data[3][2] = "Baku";
		data[3][3] = "12345";

		data[4][0] = "South America HQ";
		data[4][1] = "Brazil";
		data[4][2] = "Rio De Janeiro";
		data[4][3] = "12345";

		return data;
	}
}
