package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminDashBoard extends BasePage {

	public AdminDashBoard(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h6[normalize-space()='Dashboard']") WebElement getHeader;
	@FindBy(xpath = "//p[@class=\"oxd-userdropdown-name\"]") WebElement activeUser;
	@FindBy(xpath = "//a[normalize-space()=\"Logout\"]") WebElement btnLogout;
	
	public String isMyAccountPageExist()
	{
		try{
			return (getHeader.getText());	
		}
		catch(Exception e)
		{
			return "Login Failed";
		}
	}

	public void clickLogout()
	{
		activeUser.click();
		btnLogout.click();
	}
}
