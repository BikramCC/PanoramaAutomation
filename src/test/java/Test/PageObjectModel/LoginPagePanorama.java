package Test.PageObjectModel;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Test.AbstractComponent.AbstractComponent;

public class LoginPagePanorama extends AbstractComponent {

	WebDriver driver;

	public LoginPagePanorama(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	AbstractComponent reusableMethods = new AbstractComponent(driver);

	@FindBy(css = "input[id=':r0:']")
	WebElement userEmail;

	@FindBy(css = "input[id='auth-login-password']")
	WebElement userPass;

	@FindBy(css = "button[type='Submit']")
	WebElement submitButton;
	
	
	@FindBy(xpath  = "//li[2]//div[2]//button[1]")
	WebElement merchantButton;
	
	@FindBy(xpath  = "//input[@id='auth-login-password']")
	WebElement passwordField;
	
	

	By error = By.cssSelector("div[role='status']");
	By button = By.cssSelector("button[type='Submit']");
	By loginButton = By.cssSelector("button[type='Submit']");

	public void LandingPage() {
		driver.manage().window().maximize();
		driver.get("https://staging.panoramatrack.com/");

	}

	public Dashboard LoginWithValidData(String email, String pass) throws InterruptedException {

		userEmail.sendKeys(email);
		submitButton.click();
		reusableMethods.waitFunctionEnabledOrDisabled(button);
		userPass.sendKeys(pass);
		submitButton.click();
		Dashboard dash = new Dashboard(driver);
		return dash;

	}

	public String LoginWithInValidData(String email, String pass) throws InterruptedException {
		reusableMethods.waitFunctionVisibilityElement(userEmail);
		userEmail.sendKeys(email);
		submitButton.click();
		reusableMethods.waitFunctionEnabledOrDisabled(button);
		userPass.sendKeys(pass);
		submitButton.click();
		waitFunctionVisibility(error);
		String errorMessage = driver.findElement(By.cssSelector("div[role='status']")).getText();

		System.out.println("Hi");
		return errorMessage;

	}

	public String LoginLimit(String email, String pass) throws InterruptedException {
		userEmail.sendKeys(email);
		userPass.sendKeys(pass);
		for (int i = 0; i < 6; i++) {
			submitButton.click();
			waitFunctionEnabledOrDisabled(button);

		}
		String errorMessageLimiter = driver.findElement(error).getText();
		return errorMessageLimiter;

	}

	public void MerchantLogin(String email, String pass) throws InterruptedException {
		reusableMethods.waitFunctionVisibilityElement(userEmail);
		userEmail.sendKeys(email);
		submitButton.click();
		reusableMethods.waitFunctionVisibilityElement(merchantButton);
		merchantButton.click();
		reusableMethods.waitFunctionVisibilityElement(passwordField);
		passwordField.sendKeys(pass);
		submitButton.click();

	}

}
