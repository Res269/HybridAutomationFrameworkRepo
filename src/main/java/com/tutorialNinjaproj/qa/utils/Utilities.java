package com.tutorialNinjaproj.qa.utils;

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
	
	public static final long Implicit_Wait_Time=10;
	public static final long Page_Load_Time=10;
	
	public static String generateEmailWithTimeStamp()
	{
		Date date = new Date();;
	    String dateText = date.toString().replace(" ", "_").replace(":", "_");
	    return "reshma"+dateText+"@gmail.com";
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName)
	{
		XSSFWorkbook book=null;
		File fileExcel = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialNinja\\qa\\testdata\\TutotialNinjaTestData.xlsx");
		try
		{
		FileInputStream fisExcel = new FileInputStream(fileExcel);
		book = new XSSFWorkbook(fisExcel);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		XSSFSheet sheet = book.getSheet(sheetName);
		
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			 XSSFRow row = sheet.getRow(i+1);
			 for(int j=0;j<cols;j++)
			 {
				 XSSFCell cell = row.getCell(j);
				 CellType celltype=cell.getCellType();
				 
				 switch(celltype) 
				 {
				 case STRING :
					 data[i][j]=cell.getStringCellValue();
					 break;
				 
				 case NUMERIC:
					 data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					 break;
					 
				 case BOOLEAN:
					 data[i][j]=cell.getBooleanCellValue();
				     break;
				 }
			}
		}
		return data;
	}
	

	public static String captueScreenshot(WebDriver driver , String testname )
	{
		File srcsreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destfolder = System.getProperty("user.dir")+"\\Screenshot\\"+testname+".png";
		try 
		{
			FileHandler.copy(srcsreenshot, new File(destfolder));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return destfolder;
	}
	
}
