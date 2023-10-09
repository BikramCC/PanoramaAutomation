package Test.PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Test.AbstractComponent.AbstractComponent;
import io.opentelemetry.sdk.metrics.internal.view.SumAggregation;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = "button>.ng-star-inserted")
	List<WebElement> countryListElement;

	@FindBy(css = "a[class='btnn action__submit ng-star-inserted']")
	WebElement submit;

	public void CheckoutAction() {
		country.sendKeys("ind");
		List<WebElement> countryList = countryListElement;
		WebElement country = countryList.stream().filter(c -> c.getText().equalsIgnoreCase("India")).findFirst()
				.orElse(null);
		country.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,300)");

	}

	public ThankYouPage Submit() {
		submit.click();
		ThankYouPage ty = new ThankYouPage(driver);
		return ty;
	}

}
