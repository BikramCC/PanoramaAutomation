package Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Test.BaseComponents.BaseComponentsDemo;
import Test.PageObjectModel.LoginPage;
import Test.PageObjectModel.ProductCatalog;

/**
 * Hello world!
 *
 */
public class ErrorValidations extends BaseComponentsDemo{
	
	
	
	@Test(groups = {"errorHandling"}, retryAnalyzer = Retry.class)
	public void errorValidation() throws IOException, InterruptedException {
		WebDriver driver = InitializeDriver();
		LoginPage login = new LoginPage(driver);
		login.landingPage();
		ProductCatalog catalogs = login.LoginAction("Bikram@gmail.com", "Qwerty@123");
		String errorMessage=login.errorMessage();
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
		driver.quit();
	}
	@Test
	public void demo() {
		System.out.println("I should not run as I am not added to the group");
	}
	
}
