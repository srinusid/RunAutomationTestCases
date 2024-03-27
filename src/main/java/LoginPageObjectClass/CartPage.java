package LoginPageObjectClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// pageFactory

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cartSection h3")
	 List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkButton;

	By waitForPageLoad = By.cssSelector(".totalRow button");

	public boolean verifyProductDisplay(String productName) throws InterruptedException {
		Thread.sleep(3000);
		boolean cart = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return cart;

	}

	public CheckOutPage checkOutButton() throws InterruptedException {
		Thread.sleep(2000);
		checkButton.click();

		return new CheckOutPage(driver);
	}

}
