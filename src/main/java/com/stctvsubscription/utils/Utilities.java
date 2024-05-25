/**
 * 
 */
package com.stctvsubscription.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Currency;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 */
public class Utilities {
	
	public static final int implicitWaitTime = 15;
	public static final int pageLoadWaitTime = 35;
	
	
	public static String getCurrencyName(String code)
	{
		 Currency c1 = Currency.getInstance(code);
		 String CurrencyName = c1.getDisplayName();
		 return CurrencyName;
	}
	
	
	public static Object[][] readExcelFileTestData(String sheetName)
	{
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\TestData\\STCSubscriptionTestData.xlsx");
		XSSFWorkbook workBook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workBook = new XSSFWorkbook(fisExcel);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		XSSFSheet sheet = workBook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING: //to read string value
					data[i][j]= cell.getStringCellValue();
					break;
					
				case NUMERIC:  //to read numeric value
					data[i][j]= Integer.toString((int)cell.getNumericCellValue());
					break;
				}
			}
		}
       return data;		
	}
}
