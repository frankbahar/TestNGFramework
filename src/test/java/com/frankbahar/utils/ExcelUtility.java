package com.frankbahar.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	/**
	 * method wwill open excel file
	 * 
	 * @param String
	 */
	public void openExcel(String xlPath, String sheetName) {

		try {
			fis = new FileInputStream(xlPath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method return value of the cell
	 * 
	 * @param int rowNum, int colNum
	 * @return String
	 */

	public String getCellData(int row, int col) {
		if (sheet.getRow(row).getCell(col) != null) {
			return sheet.getRow(row).getCell(col).toString();

		} else {
			return "";
		}
	}

	/**
	 * method return number of actual used rows
	 * 
	 * @return int
	 */
	public int getRowNum() {
		return sheet.getPhysicalNumberOfRows();
	}
	/**
	 * method return number of actual used rows
	 * 
	 * @return int
	 */
	
	public int getColNum(int rowIndex) {
		return sheet.getRow(rowIndex).getLastCellNum();
	}
	
	public void closeExcel() {
		try {
			workbook.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
