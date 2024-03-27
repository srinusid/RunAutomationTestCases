package rahulshettyAcademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import LoginPageObjectClass.CartPage;
import LoginPageObjectClass.CheckOutPage;
import LoginPageObjectClass.ConfirmationPage;
import LoginPageObjectClass.LoginPage;
import LoginPageObjectClass.OrderPage;
import LoginPageObjectClass.ProductCatelogue;
import rahulshettyAcademy.TestComponents.BaseTest;

public class StandAloneTest extends BaseTest {
	String productName = "zara coat 3";
	public LoginPage loginPage;

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatelogue productCatelogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products=productCatelogue.getProductList();
		productCatelogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatelogue.goToCart();
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.checkOutButton();

		checkOutPage.sendCountryName("India");

		ConfirmationPage confirmPage = checkOutPage.ClickPlaceHolder();
		String getTitleText = confirmPage.getConfirmationMessage();

		Assert.assertTrue(getTitleText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void verifyOrderMatch() throws InterruptedException {
		ProductCatelogue productCatelogue = loginPage.loginApplication("srinusid830@gmail.com", "Devisrinu123@");
		OrderPage orderPage = productCatelogue.goToOrderHeader();
		Boolean match = orderPage.verifyOrderInList(productName);
		Assert.assertTrue(match);
	}

@DataProvider
public Object[][] getData() throws IOException{
	
//	HashMap<String,String> map=new HashMap();
//	map.put("email", "srinusid830@gmail.com");
//	map.put("password", "Devisrinu123@");
//	map.put("product", "zara coat 3");
//	
//	HashMap<String,String> map1=new HashMap();
//	map1.put("email", "anshika@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
	
	
	List<HashMap<String,String>> data=getHashMapData(System.getProperty("user.dir") + "\\src\\main\\java\\seleniumFramework\\data\\PurchaseJSONData.json");
	Object[][] object={{data.get(0)},{data.get(1)}};
	return  object;
	
}


//@DataProvider
//public Object[][] getData(){
	
//	HashMap<String,String> map=new HashMap();
//	map.put("email", "srinusid830@gmail.com");
//	map.put("password", "Devisrinu123@");
//	map.put("product", "zara coat 3");
//	
//	HashMap<String,String> map1=new HashMap();
//	map1.put("email", "anshika@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
//	Object[][] object={{map},{map1}};
//}


}
