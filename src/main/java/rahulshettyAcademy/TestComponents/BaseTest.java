package rahulshettyAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import LoginPageObjectClass.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver iniatializeDriver() throws IOException {
		Properties property = new Properties();
		FileInputStream file = new FileInputStream(
	System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalProperties.properties");
		property.load(file);

		String browserName = property.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\srinu\\Downloads\\chromedriver122-win64\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		return driver;

	}
	
	public List<HashMap<String, String>> getHashMapData(String filePath) throws IOException {
		// read and convert json to string

		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			
		// convert String to HashMAp using

		// String to HashMap- Jackson Datbind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		iniatializeDriver();
		 loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
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