package Test.PageObjectModel;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Test.AbstractComponent.AbstractComponent;

public class CategoriesPanorama extends AbstractComponent {
	WebDriver driver;
	AbstractComponent reusableMethods = new AbstractComponent(driver);
	By profileIcon = By.cssSelector(".MuiSvgIcon-root.MuiSvgIcon-fontSizeMedium.MuiAvatar-fallback.css-13y7ul3");
	By categoriesElement = By.xpath("//p[text()='Categories']");
	By SubmitButton= By.xpath("//button[text()='Submit']");
	By resetButton=By.xpath("//button[text()='Reset Filter']");
	public CategoriesPanorama(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	public void opencategory() throws InterruptedException {
		driver.findElement(By.xpath("//p[text()='Categories']")).click();
		
		
	}
	public void addCategory() throws InterruptedException {
		
		WebElement addButton = driver.findElement(By.xpath("//button[text()='Add New']"));
		waitFunctionEnabledOrDisabled(By.xpath("//button[text()='Add New']"));
		addButton.click();
		boolean uniqeOrNot=true;
		String categoryName= "test";
		int number=0;
		WebElement categoryTextbox= driver.findElement(By.cssSelector("input[id=':raj:']"));
		waitFunctionVisibilityElement(categoryTextbox);
		while(uniqeOrNot) {
			
			driver.findElement(By.cssSelector("input[id=':raj:']")).sendKeys(categoryName);
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			waitFunctionEnabledOrDisabled(SubmitButton);
			try {
	            WebElement errorMessage = driver.findElement(By.xpath("//p[text()='Category name must be unique.']"));
	            uniqeOrNot=true;
	            System.out.println(errorMessage);
	            categoryName= categoryName+number;
	            number++;
	            categoryTextbox.sendKeys(Keys.COMMAND, "a");
	            categoryTextbox.sendKeys(Keys.DELETE);
	            
	        } catch (NoSuchElementException e) {
	            // Handle the exception when the element is not found
	            System.out.println("Element not found: " + e.getMessage());
	            // You can add more specific handling here if needed
	            uniqeOrNot=false;
		}
	}
}
}
