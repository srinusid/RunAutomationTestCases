package LoginPageObjectClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// pageFactory

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//*[contains(@class,'ta-item')][2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	@FindBy(css = "[class='btnn action__submit ng-star-inserted']")
	WebElement submit;

	


	public void sendCountryName(String countryName) {
		country.sendKeys(countryName);
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage ClickPlaceHolder() {

		submit.click();
		return new ConfirmationPage(driver);
	}
}
