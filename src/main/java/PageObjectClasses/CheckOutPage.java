package PageObjectClasses;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement sendcountry;

	@FindBy(xpath = "//*[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	By wait = By.cssSelector(".ta-results");
	@FindBy(css = "a[class*='action__submit']")
	WebElement placeOrder;

	public ConfirmationPage sendCountryName(String countryName) {
		sendcountry.sendKeys(countryName);
		waitForElementToAppear(wait);
		selectCountry.click();
		

		placeOrder.click();

		return new ConfirmationPage(driver);
	}

}
