package Test.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test.PageObjectModel.CartPage;
import Test.PageObjectModel.orderPage;

public class AbstractComponent {

	By cart = By.cssSelector("[routerlink='/dashboard/cart']");

	static WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitFunctionVisibility(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));

	}

	public void waitFunctionVisibilityElement(WebElement error) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(error));

	}

	public void waitFunctionInVisibility(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ele));

	}

//	

	public void waitFunctionEnabled(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(ele));

	}

	public void waitFunctionEnabledOrDisabled(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(ele));

	}

	public CartPage goToCartPage() {
		
				driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
				CartPage cartPage = new CartPage(driver);
				return cartPage;
			}
		
			public static orderPage orderPageClick() {
				driver.findElement(By.cssSelector("button[routerlink$='/dashboard/myorders']")).click();
				orderPage order = new orderPage(driver);
				return order;
		
			}


}

