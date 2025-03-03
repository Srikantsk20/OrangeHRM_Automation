package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminUserMgt extends BasePage {

	public AdminUserMgt(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath ="//span[normalize-space()='Admin'] ") WebElement linkAdmin;
	@FindBy(xpath ="//span[normalize-space()='User Management'] ") WebElement linkUserMgt;
	@FindBy(xpath ="//li[normalize-space()='Users']") WebElement linkUser;
	@FindBy(xpath =" //div[@class='orangehrm-header-container']//button") WebElement btnAdd;
	@FindBy(xpath = "//h6[normalize-space()='Admin']") WebElement getHeader;
	
	public void  clickAdmin()
	{
		linkAdmin.click();
	}
	
	public void clickUserDropDown() throws InterruptedException
	{

		linkUserMgt.click();  // Click to open dropdown
	    Thread.sleep(2000);    // Wait for dropdown to open
	    linkUser.click();      // Click on "Users"
	}
	
	public void clickAddUser()
	{
		btnAdd.click();
	}
	
	public String isMyAdminPageExist()
	{
		try{
			return (getHeader.getText());	
		}
		catch(Exception e)
		{
			return "Page does not exist";
		}
	}
	
}
