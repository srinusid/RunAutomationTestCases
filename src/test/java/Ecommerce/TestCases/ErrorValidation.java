package Ecommerce.TestCases;

import java.util.HashMap;

import org.testng.annotations.Test;

import Ecommerce.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest{

	@Test(dataProvider="getData")
	public void errorValidation(HashMap<String,String> input) throws InterruptedException {
		loginPage.loginApplication(input.get("email"), input.get("password"));
	}

}
