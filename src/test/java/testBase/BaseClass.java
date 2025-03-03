package testBase;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class BaseClass {
	public static WebDriver driver;
	public Properties properties;
	
		@BeforeTest
		public void setup() throws IOException
		{
			FileReader file = new FileReader("./src//test//java//runnerFiles//config.propeties");
			properties = new Properties();
			properties.load(file);
			driver = new ChromeDriver();
			driver.get(properties.getProperty("appURL"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
			driver.manage().window().maximize();
		}
		
		@AfterTest
		public void tearDown()
		{
			driver.quit();
			System.out.println("Happy Testing......");
		}
		
		//Capture screenshot
		public static void captureScreenshot(String testCaseName)
		{
			try
			{
				if (driver == null) {
		            System.err.println("❌ WebDriver is null. Cannot capture screenshot.");
		            return;
		        }
				
				new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
				
				String timeStamp = new SimpleDateFormat("ddMMyy_HHmmss").format(new Date());
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				
				String screenshotDir = System.getProperty("user.dir") + "\\screenshots\\";
	            File directory = new File(screenshotDir);
	            if (!directory.exists()) {
	                directory.mkdir();
	            }
	            
				File destFile = new File(screenshotDir+testCaseName+ timeStamp+ ".png");
				FileHandler.copy(srcFile, destFile);
				// Attach screenshot to Allure Report
		        Allure.addAttachment(testCaseName, new ByteArrayInputStream(Files.readAllBytes(destFile.toPath())));
	            System.out.println("✅ Screenshot captured: " + destFile.getAbsolutePath());
			}
			catch(IOException e)
			{
				System.err.println("❌ Screenshot Failed");
			}
		}
}
