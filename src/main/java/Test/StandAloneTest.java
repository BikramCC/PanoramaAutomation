package Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Test.PageObjectModel.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class StandAloneTest {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World!");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
     	LoginPage login= new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String productName = "ZARA COAT 3";
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("Bikram@gmail.com");
		driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("Qwerty@1234");
		driver.findElement(By.cssSelector("input[id='login']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("col-lg-4")));
		List<WebElement> products = driver.findElements(By.className("col-lg-4"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("h5")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".btn.w-10")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-tns-c31-0.ng-star-inserted"))));
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		List<WebElement> cartList = driver.findElements(By.cssSelector("div>div>h3"));
		WebElement cart = cartList.stream().filter(c -> c.getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		System.out.println(cart.getText());
		Assert.assertEquals(productName, cart.getText());
		driver.findElement(By.cssSelector("div>ul>li>button")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countryList = driver.findElements(By.cssSelector("button>.ng-star-inserted"));
		WebElement country = countryList.stream().filter(c -> c.getText().equalsIgnoreCase("India")).findFirst()
				.orElse(null);
		country.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scroll(0,300)");
		
		driver.findElement(By.cssSelector("a[class='btnn action__submit ng-star-inserted']")).click();
		String thankYouMessage= driver.findElement(By.className("hero-primary")).getText();
		Assert.assertEquals(thankYouMessage, "THANKYOU FOR THE ORDER.");
		String orderId=driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
		System.out.println(orderId);
		
	}
}
