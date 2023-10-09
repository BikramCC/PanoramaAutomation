package Test.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Test.AbstractComponent.AbstractComponent;

public class ThankYouPage extends AbstractComponent {
	WebDriver driver;

	public ThankYouPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "hero-primary")
	WebElement message;

	@FindBy(css = "label[class='ng-star-inserted']")
	WebElement orderIdele;

	public String ThankYouPageAction() {
		String thankYouMessage = message.getText();
		Assert.assertEquals(thankYouMessage, "THANKYOU FOR THE ORDER.");
		String orderId = orderIdele.getText();
		System.out.println(orderId);
		return thankYouMessage;
		
	}

}
