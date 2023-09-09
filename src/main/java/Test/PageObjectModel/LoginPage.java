package Test.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Test.AbstractComponent.AbstractComponent;
import io.opentelemetry.sdk.metrics.internal.view.SumAggregation;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
}
	@FindBy(css = "input[type='email']")
	WebElement userEmail;

	@FindBy(css = "input[id='userPassword']")
	WebElement userPass;
	
	@FindBy(css = "input[id='login']")   
	WebElement submit;
	
	@FindBy(xpath  = "//div[@aria-label='Incorrect email or password.']")   
	WebElement error;
	
	
	public ProductCatalog LoginAction(String email , String pass) {
		userEmail.sendKeys(email);
		userPass.sendKeys(pass);
		submit.click();	
		ProductCatalog catalogs = new ProductCatalog(driver);
		return catalogs;
	}
	public void landingPage() {
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	public String errorMessage() {
	waitFunctionVisibilityElement( error);
	return error.getText();
	
	

	
		
	}
	
	
}

	
	
	


