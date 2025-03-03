package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;


public class Login extends BasePage{
	
	public Login(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath = "//input[@placeholder=\"Username\"]") WebElement txtUsername;
	@FindBy (xpath = "//input[@placeholder=\"Password\"]") WebElement txtPassword;
	@FindBy(xpath = " //button[normalize-space()=\"Login\"]") WebElement btnLogin;


	public void setUsername(String username)
	{
		txtUsername.sendKeys(username);
	}

	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() 
	{
			btnLogin.click();
		}

}
