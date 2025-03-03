package testclasses;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pageObjects.AdminDashBoard;
import pageObjects.Login;
import testBase.BaseClass;
import utility.ExcelDataReader;
import utility.ExcelUtility;


@Epic("Login Module")
@Feature("Login Test Case")
public class TC001_Login_page extends BaseClass
{
	private List<String> runnableTests;
	@BeforeMethod
	public void loadRunnableTest()
	{
		runnableTests = ExcelUtility.getRunnableTest();
	}
	
	String[][] loginData = ExcelDataReader.getLoginData();
	String[][] className = ExcelDataReader.getLoginData();
		@Test
		@Description("Validate user login functionality")
		@Severity(SeverityLevel.CRITICAL)
		@Story("User Login")
		@Step("Execute Login test")
	public void validateLogin()
	{
		int testCaseId =1;
		Login login = new Login(driver);
		
		
		if(runnableTests.contains("Validate Login functionality"))
		{
			for(String data[]:loginData)
			{
				String testName = data[0];
				
				// Check if className is "Login_Page"
				 if (!testName.equalsIgnoreCase("Login_Page")) {
				        System.out.println("Skipping test as class is not Login_Page");
				        return;
				    }
				 else
				 {
					 String username = data[1];
			         String password = data[2];
			         String expectedOutcome = data[3];
//			         String status = data[5];

						Allure.step("Executing Test : " + "Validate Login functionality");
						System.out.println("Executing Login Test");
						
						
						try
						{
							login.setUsername(username);
							login.setPassword(password);
							login.clickLogin();
							
							AdminDashBoard dashboard = new AdminDashBoard(driver);
							
							//dashboard.isMyAccountPageExist();
							
							Thread.sleep(2000);
							
//					          String targetPage = dashboard.isMyAccountPageExist();
//					          boolean isLoginSuccessfull = targetPage.equalsIgnoreCase("Dashboard");
					          
//							System.out.println("📌 Checking if Dashboard is visible");
//			                String targetPage = "";
			                
			               String  targetPage = dashboard.isMyAccountPageExist();
			                boolean isLoginSuccessful = "Dashboard".equalsIgnoreCase(targetPage);

					          
					          if(expectedOutcome.equalsIgnoreCase("Valid"))
					          {
					        	  if(isLoginSuccessful)
					        	  {
						        	  captureScreenshot("validateLogin");
							          ExcelUtility.writeTestResult(testCaseId, "Pass");
//							          ExcelDataReader.writeTestData("Pass");
							          Allure.step("Test Passed ");
							          Assert.assertTrue(true);
							          dashboard.clickLogout();
						       }
						       else
						          {
						    	   ExcelUtility.writeTestResult(testCaseId, "Fail");
//						    	   ExcelDataReader.writeTestData("Fail");
						            captureScreenshot("validateLogin_error");
						            Assert.fail("Login failes but expected success");
						            Allure.step("Login failes but expected success");
						          }
					          }
					          else if(expectedOutcome.equalsIgnoreCase("Invalid"))
//					          else
					          {
					        	  if(!isLoginSuccessful)
						          {
						        	  captureScreenshot("validateLogin");
							          ExcelUtility.writeTestResult(testCaseId, "Pass");
//							          ExcelDataReader.writeTestData("Pass");
							          Allure.step("Test Passed : Invalid credential rejected");
							          Assert.assertTrue(true);
						       }
						       else
						          {
						    	   ExcelUtility.writeTestResult(testCaseId, "Fail");
//						    	   ExcelDataReader.writeTestData("Fail");
						            captureScreenshot("validateLogin_error");
						            Assert.fail("Test Case failed: Invalid login accepted");
						            Allure.step("Test Case failed: Invalid login accepted");
						          }
					          }
					         
					          
						}
						catch(Exception e)
						{
							System.out.println("This is catch section...");
							ExcelUtility.writeTestResult(testCaseId, "Fail");
//							ExcelDataReader.writeTestData("Fail");
				            captureScreenshot("validateLogin");
				            Assert.fail("Test Case Failed: " + e.getMessage());
				            Allure.step("Test Failed: " + e.getMessage());
						}
				 }
				
			}
		
		}
			else
			{
				System.out.println("Skipped Login Test");
				Allure.step("Login Test Skipped ");
			}
		}
}
