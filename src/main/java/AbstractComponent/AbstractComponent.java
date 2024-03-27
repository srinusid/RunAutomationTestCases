package AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectClasses.CartPage;

public class AbstractComponent {
WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement clickonCartIcon;
	
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToAppear(By toast) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(toast));
	}
	
	public void waitForWebElementToDisappear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementToDisappear(By ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(ele));
	}
	
	
	public CartPage clickOnCartIcon() {
		clickonCartIcon.click();
		//waitForWebElementToAppear(waitForProductsToLoadInCartPage);
		return new CartPage(driver);
		
	}

}
