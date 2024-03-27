package rahulshettyAcademy.TestComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import LoginPageObjectClass.LoginPage;
import resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	WebDriver driver;
	ExtentReports	extent=ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
       
       try {
		driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
       String filepath = null;
       try {
		 filepath=getScreenShot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	@Override
	public WebDriver iniatializeDriver() throws IOException {
		// TODO Auto-generated method stub
		return super.iniatializeDriver();
	}

	@Override
	public List<HashMap<String, String>> getHashMapData(String filePath) throws IOException {
		// TODO Auto-generated method stub
		return super.getHashMapData(filePath);
	}

	@Override
	public LoginPage launchApplication() throws IOException {
		// TODO Auto-generated method stub
		return super.launchApplication();
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	@Override
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		// TODO Auto-generated method stub
		return super.getScreenShot(testCaseName, driver);
	}

}
