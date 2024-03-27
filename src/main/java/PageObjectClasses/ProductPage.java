package PageObjectClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class ProductPage extends AbstractComponent{
WebDriver driver;
	public ProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3");
	
	@FindBy(css = ".ng-animating")
	WebElement waitEleToDisappear;
	
	By productsLoad=By.cssSelector(".mb-3");
	By toastContainer=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;

	}
	
	public WebElement getProductByName(String prod) {
	WebElement pro=	products.stream().filter(p->p.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(prod)).findFirst().orElse(null);
	return pro;
	}
	
	public void addToCart(String prod) {
		WebElement pro=getProductByName(prod);
		pro.findElement(By.cssSelector("button:last-of-type")).click();
		waitForElementToAppear(toastContainer);
		waitForWebElementToDisappear(waitEleToDisappear);
		
	}

}
