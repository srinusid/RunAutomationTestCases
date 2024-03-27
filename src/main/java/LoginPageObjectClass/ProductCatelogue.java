package LoginPageObjectClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponent.AbstractComponent;

public class ProductCatelogue extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".ng-animating")
	WebElement ele;

	public ProductCatelogue(WebDriver driver) {
		super(driver);
		this.driver = driver;

		// pageFactory

		PageFactory.initElements(driver, this);

	}

	// pageFactory(driver.findElement)
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button:last-of-type");

	By toastContainer = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;

	}

	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream().filter(product ->

		product.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(ele);

	}
}
