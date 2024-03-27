package LoginPageObjectClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// pageFactory

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatelogue loginApplication(String email, String passWord) throws InterruptedException {
		userEmail.sendKeys(email);
		Thread.sleep(3000);		
		userPassword.sendKeys(passWord);
		login.click();
		ProductCatelogue productCatelogue = new ProductCatelogue(driver);
		return productCatelogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
