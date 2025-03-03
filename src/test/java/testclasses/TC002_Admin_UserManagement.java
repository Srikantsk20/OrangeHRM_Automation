package testclasses;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pageObjects.AdminUserMgt;
import pageObjects.Login;
import testBase.BaseClass;
import utility.ExcelUtility;

@Epic("Admin")
@Feature("Users")
public class TC002_Admin_UserManagement extends BaseClass
{	
	private List<String> runnableTests;
	@BeforeMethod
	public void loadRunnableTest()
	{
		runnableTests = ExcelUtility.getRunnableTest("Valid Admin Module");
	}

	@Description("Validate Users in Admin/UserManagement")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Admin Module")
	@Step("Execute Add User test")
	@Test
	public void validateUsrMgt_users() throws InterruptedException
	{
		int testCaseId =2;
		
		//login before validating Admin User Management
		Login login = new Login(driver);
		Thread.sleep(2000);
		login.setUsername("Admin");
		login.setPassword("admin123");
		login.clickLogin();
		
		AdminUserMgt admin = new AdminUserMgt(driver);
		if(runnableTests.contains("Validate Admin Module"))
		{
			try
			{
				
				admin.clickAdmin();
                captureScreenshot("Validate_Admin_UserManagement");

                admin.clickUserDropDown();
                captureScreenshot("UserManagement_DropDown");

                admin.clickAddUser();
                captureScreenshot("Add_User");
				Thread.sleep(2000);
				
				admin.clickAddUser();
				captureScreenshot("Add User");
				
				String targetPage = admin.isMyAdminPageExist();
				if ("Dashboard".equalsIgnoreCase(targetPage)) {
                    ExcelUtility.writeTestResult(testCaseId, "Pass");
                    Allure.step("Test Passed");
                    System.out.println("Test Passed Successfully!");
                    Assert.assertTrue(true);
                } else {
                    ExcelUtility.writeTestResult(testCaseId, "Fail");
                    Allure.step("Test Failed");
                    System.err.println("Navigation Failed: Expected 'Dashboard', but found '" + targetPage + "'");
                    Assert.fail("Navigation Error: Admin Page did not load as expected.");
                }
				
			}
			catch(Exception e)
			{
				ExcelUtility.writeTestResult(testCaseId, "Fail");
				captureScreenshot("Validate Admin/User Management Error");				
				Allure.step("Test Fail");
				Assert.fail("Test case Failed");
				System.err.println(e.getStackTrace());
			}
		}
		else
		{
			System.out.println("Skipping test. 'Validate Admin Module' is not marked as runnable.");
		}
	}
	
}
