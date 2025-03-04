package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtility {
	
	//static final String FILE_PATH ="G:\\Projects\\HRM\\OrangeHRM_Automation\\src\\test\\java\\runnerFiles\\TestCases.xlsx";
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFCell cell;
	static XSSFRow row;
	
	public static List<String> getRunnableTest()
	{
		List<String> runnableTests = new ArrayList<>();
		try {
//			FileInputStream fis = new FileInputStream("G:\\Projects\\HRM\\OrangeHRM_Automation\\src\\test\\java\\runnerFiles\\TestCases.xlsx");
			String filePath = System.getProperty("user.dir") + "/src/test/resources/runnerFiles/TestCases.xlsx";
            File file = new File(filePath);
            
            if (!file.exists()) {
                throw new RuntimeException("File not found at: " + filePath);
            }

            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet("TestCases");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    String testName = row.getCell(1).getStringCellValue();
                    String runFlag = row.getCell(3).getStringCellValue();
                    System.out.println("TestName : " + testName + "Run Flag : " + runFlag);
                    if ("Yes".equalsIgnoreCase(runFlag)) {
                        runnableTests.add(testName);
                    }
                }
                workbook.close();
               }
            fis.close();
           }
		catch(Exception e)
		{
			System.err.println("File Not Found" + e.getMessage());
		}
		return runnableTests;
	}
	
	//Write result to result excel file
	    public static void writeTestResult(int testCaseID, String status) 
	    {
	    	String FILE_PATH = "G:\\Project Based Learning\\Orange_HRM\\src\\test\\resources\\runnerFiles\\TestCaseToExecute.xlsx";
	        try 
	        {
	        	FileInputStream fis = new FileInputStream(FILE_PATH);
        		workbook = new XSSFWorkbook(fis);
	        		sheet = workbook.getSheet("Sheet1");

	        		boolean found = false;
	        		for (Row row : sheet) 
	        		{
	        			Cell cell = row.getCell(0); // TestCaseID column
	        			if (cell != null && cell.getCellType() == CellType.NUMERIC && (int) cell.getNumericCellValue() == testCaseID) 
	        			{
	        				row.createCell(2, CellType.STRING).setCellValue(status); // TestResult column
	        				found = true;
	        				break;
	        			}
	        		}

	            if (!found)
	            {
	                System.out.println("⚠ TestCaseID " + testCaseID + " not found.");
	            }
	            FileOutputStream fos = new FileOutputStream(FILE_PATH);
	            workbook.write(fos);
	            System.out.println("✅ Test result updated successfully for TestCaseID : " + testCaseID);
	            workbook.close();
	            fis.close();
	            fos.close();
	        } 
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	  }
	    
	    
}
