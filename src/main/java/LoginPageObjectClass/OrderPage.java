package LoginPageObjectClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;



public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderList;
	

	
	public Boolean verifyOrderInList(String productName) throws InterruptedException {
		Thread.sleep(1000);
		Boolean match=orderList.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
	return match;
	}

}
