package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 5;

	public static String generateEmailWithTimeStamp() {

		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "xyz" + timestamp + "@gmail.com";

	}

	public static Object[][] getTestDataFromExcel(String sheetName) {

		File excelFile = new File("../TutorialsNinjaProj/src/main/java/com/tutorialsninja/qa/testdata/testdata_02.xlsx");
		XSSFWorkbook workbook = null;

		try {

			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);

		} catch (Throwable e) {
			
			e.printStackTrace();
			
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];
		for (int i = 0; i < rows; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = cell.getStringCellValue();
					break;

				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
					
				default:
					break;

				}
			}
		}
		
		return data;

	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destScreenshotPath = ("../TutorialsNinjaProj/screenshots/"+ testName +".png");
		try {
			
			FileHandler.copy(srcScreenshot, new File(destScreenshotPath));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		return destScreenshotPath;
	}

}
