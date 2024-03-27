package Cucumber.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import LoginPageObjectClass.CartPage;
import LoginPageObjectClass.CheckOutPage;
import LoginPageObjectClass.ConfirmationPage;
import LoginPageObjectClass.LoginPage;
import LoginPageObjectClass.ProductCatelogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyAcademy.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	public LoginPage loginPage;
	public ProductCatelogue productCatelogue;
	public ConfirmationPage confirmPage;
	public CartPage cartPage;
	public CheckOutPage checkOutPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		loginPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_Username_and_Password(String userName, String password) throws InterruptedException {
		productCatelogue = loginPage.loginApplication(userName, password);
	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) {
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addProductToCart(productName);
	}

	@When("^CheckOut (.+) and submit the order$")
	public void checkOut_submitOrder(String productName) throws InterruptedException {
		cartPage = productCatelogue.goToCart();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		checkOutPage = cartPage.checkOutButton();

		checkOutPage.sendCountryName("India");

		confirmPage = checkOutPage.ClickPlaceHolder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void verify_the_Confirmation_Message(String message) throws InterruptedException {
		String getTitleText = confirmPage.getConfirmationMessage();

		Assert.assertTrue(getTitleText.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" error message is displayed$")
	public void something_message_is_displayed(String message) {
		Assert.assertEquals(message, loginPage.getErrorMessage());
		driver.close();
	}

}
