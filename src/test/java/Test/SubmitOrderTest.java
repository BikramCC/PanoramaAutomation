package Test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Test.AbstractComponent.AbstractComponent;
import Test.BaseComponents.BaseComponentsDemo;
import Test.PageObjectModel.CartPage;
import Test.PageObjectModel.CheckoutPage;
import Test.PageObjectModel.LoginPage;
import Test.PageObjectModel.ProductCatalog;
import Test.PageObjectModel.ThankYouPage;
import Test.PageObjectModel.orderPage;

/**
 * Hello world!
 *
 */
public class SubmitOrderTest extends BaseComponentsDemo{
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData", groups = "purchase",retryAnalyzer = Retry.class)
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		WebDriver driver = InitializeDriver();
		LoginPage login = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		login.landingPage();
		ProductCatalog catalogs = login.LoginAction(input.get("email"), input.get("pass"));
		
		driver.manage().window().maximize();
		AbstractComponent reusableMethods = new AbstractComponent(driver);
		By productsLoaded = By.className("col-lg-4");
		reusableMethods.waitFunctionVisibility(productsLoaded);
		List<WebElement> products = catalogs.getProductList();
		catalogs.getProductByName(input.get("product"));
		catalogs.addProductToCart(input.get("product"));
		By toastLoaded = By.id("toast-container");
		reusableMethods.waitFunctionInVisibility(toastLoaded);
		CartPage cartPage = reusableMethods.goToCartPage();
		WebElement cart = cartPage.cartMethods(input.get("product"));
		Assert.assertEquals(input.get("product"), cart.getText());
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.CheckoutAction();
		ThankYouPage ty = checkoutPage.Submit();
		String thankYouMessage = ty.ThankYouPageAction();
		Assert.assertEquals(thankYouMessage, "THANKYOU FOR THE ORDER.");
		
	
	}
	
	@Test(dependsOnMethods = "SubmitOrder", groups = "errorHandling")
	public void orderHistory() throws IOException, InterruptedException {
		WebDriver driver = InitializeDriver();
		LoginPage login = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		login.landingPage();
		ProductCatalog catalogs = login.LoginAction("Bikram@gmail.com", "Qwerty@1234");
		orderPage order=AbstractComponent.orderPageClick();
		boolean result= order.getOrdersList(productName);
		assertTrue(result);
		
}
	
	@DataProvider
	public Object getData() throws IOException {
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//main//java//Test//data//JsonData.Json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
		
	}

	
}