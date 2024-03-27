package rahulshettyAcademy.tests;

import java.io.IOException;

import org.testng.Assert;

import org.testng.annotations.Test;

import LoginPageObjectClass.CartPage;
import LoginPageObjectClass.ProductCatelogue;
import rahulshettyAcademy.TestComponents.BaseTest;


public class ErrorValidationsTest extends BaseTest {
	
@Test(groups= {"ErrorHandling"})
public void LoginError() throws InterruptedException, IOException {
	

		String productName = "zara coat 3";
		
		loginPage.loginApplication("srinusid830@gmail.com", "Devisrinu23@");
		System.out.println(loginPage.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
		

	}
@Test
public void productMatchError() throws InterruptedException {
	String productName = "zara coat 3";
	
	ProductCatelogue productCatelogue = loginPage.loginApplication("srinusid830@gmail.com", "Devisrinu123@");
	productCatelogue.getProductList();
	productCatelogue.addProductToCart(productName);

	CartPage cartPage = productCatelogue.goToCart();
	boolean match=cartPage.verifyProductDisplay(productName);
	Assert.assertTrue(match);
}
}

