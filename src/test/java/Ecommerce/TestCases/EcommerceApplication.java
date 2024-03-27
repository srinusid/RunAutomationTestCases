package Ecommerce.TestCases;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Ecommerce.TestComponents.BaseTest;
import PageObjectClasses.CartPage;
import PageObjectClasses.CheckOutPage;
import PageObjectClasses.ConfirmationPage;
import PageObjectClasses.ProductPage;

public class EcommerceApplication extends BaseTest {
//String[] productList= {"ZARA COAT 3","ADIDAS ORIGINAL"};
	

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException {

		ProductPage ProductPage = loginPage.loginApplication(input.get("email"), input.get("password"));
		ProductPage.getProductList();
		ProductPage.addToCart(input.get("product"));
		CartPage cart = ProductPage.clickOnCartIcon();
		Boolean match = cart.verifyProducts(input.get("product"));
		Assert.assertTrue(match);

		CheckOutPage check = cart.CheckOut();
		ConfirmationPage confirmation = check.sendCountryName("Ind");
		String message = confirmation.verifyMessage();
		System.out.println("Message:::" + message);

		Assert.assertEquals("THANKYOU FOR THE ORDER.", message);
	}
	
	
	@Test(dataProvider="getData" ,groups= {"checkingError"})
	public void errorValidation(HashMap<String,String> input) throws InterruptedException {
		loginPage.loginApplication(input.get("email"), input.get("password"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getHashMapData();
		Object[][] dataSet = { { data.get(0) } };
		return dataSet;

	}

	public List<HashMap<String, String>> getHashMapData() throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\main\\java\\Ecommerce\\data\\InputDataFields.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
}
