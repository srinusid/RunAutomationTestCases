package Ecommerce.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObjectClasses.LoginPage;

public class BaseTest {
	

	public WebDriver driver;

	public LoginPage loginPage;

	public  WebDriver iniatializeDriver() throws IOException {
		Properties property = new Properties();
		FileInputStream file = new FileInputStream(
	System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalProperties.properties");
		property.load(file);

		String browserName = property.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\srinu\\Downloads\\chromedriver122-win64\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage loginApplication() throws IOException {
		driver=iniatializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	

	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.close();
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {

		TakesScreenshot screenShot=(TakesScreenshot)driver;
		File source=screenShot.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		}

}
