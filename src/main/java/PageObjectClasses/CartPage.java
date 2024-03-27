package PageObjectClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> waitForProductsToLoadInCartPage;
    @FindBy(css="li[class='totalRow'] button")
	WebElement checkOut;
	public boolean verifyProducts(String productName) {
		boolean match = waitForProductsToLoadInCartPage.stream()
				.anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage CheckOut() {
		checkOut.click();
		return new CheckOutPage(driver);

	}
}
