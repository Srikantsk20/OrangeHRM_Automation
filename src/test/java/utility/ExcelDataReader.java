package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelDataReader {
	static FileInputStream fis;
	static FileOutputStream fos;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell col;
	private static final String filePath = "G:\\Projects\\HRM\\OrangeHRM_Automation\\src\\test\\java\\runnerFiles\\DefaultTestData.xlsx";
	
	public static String[][] getLoginData()
	{
		//String loginData[][] = null;
		
		try
		{			
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet("DefaultData");
			
			//counting total number of rows and cols
			int totalRows = sheet.getLastRowNum();
			int totalCols = sheet.getRow(0).getLastCellNum();

			
			//created for two dimension array which can store username and password
			String loginData [][] = new String[totalRows][totalCols];
			
			for (int i = 1; i <= totalRows; i++) { // Start from row 1 (skip header)
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < totalCols; j++) {
                    XSSFCell cell = row.getCell(j);
                    loginData[i - 1][j] = (cell != null) ? cell.toString() : ""; // Handle empty cells
                }
            }
			workbook.close();
			fis.close();
		return loginData;//returning two dimension array
			
			
		}
		catch(IOException e)
		{
			System.err.println("FIle Not Found" + e.getMessage());
		}
		
		return null;
	}
}
