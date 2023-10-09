package Test.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Test.AbstractComponent.AbstractComponent;
import io.opentelemetry.sdk.metrics.internal.view.SumAggregation;

public class ProductCatalog extends AbstractComponent {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "col-lg-4")
	List<WebElement> products;
	
	By productsBy= By.className("col-lg-4");
	By addToCart= By.cssSelector(".btn.w-10");
	
	
	public List<WebElement> getProductList() {
		waitFunctionVisibility(productsBy);
		return products;
		
	}
	public WebElement getProductByName(String productName) {
		waitFunctionVisibility(productsBy);
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("h5")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
		
	}
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		Thread.sleep(2000);
		
		
	}
	
	

}
