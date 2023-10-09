package Test.PageObjectModel;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Test.AbstractComponent.AbstractComponent;

public class RolesAndPermissionsPanorama extends AbstractComponent {

	WebDriver driver;
	By rolesElement = By.xpath("//p[text()='Roles']");
	By addUserButtonElement = By.xpath("//button[text()='Add Role']");
	By searchOutputRoles= By.cssSelector("a[href='/controls/roles/#']");
	
	public RolesAndPermissionsPanorama(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void openRoles() {
		driver.findElement(By.xpath("//p[text()='Control Center']")).click();
		waitFunctionVisibility(rolesElement);
		driver.findElement(By.xpath("//p[text()='Roles']")).click();
		waitFunctionEnabledOrDisabled(By.xpath("//button[text()='Reset Filter']"));
		waitFunctionEnabledOrDisabled(By.xpath("//button[text()='Reset Filter']"));

	}
	

	public boolean SearchByUserName(String searchQuery) throws InterruptedException {
		WebElement input = driver.findElement(By.cssSelector("input[placeholder='Search Roles']"));
		driver.findElement(By.cssSelector("input[placeholder='Search Roles']")).sendKeys(searchQuery);
		Thread.sleep(2000);
		waitFunctionEnabledOrDisabled(By.xpath("//button[text()='Reset Filter']"));
		
		waitFunctionVisibility(searchOutputRoles);
		System.out.println(driver.findElement(searchOutputRoles).getText());
		if(driver.findElement(searchOutputRoles).getText().contains(searchQuery)) {
     		waitFunctionEnabledOrDisabled(By.xpath("//button[text()='Reset Filter']"));
			return true;
		}
//		List<WebElement> searchResultsByName = driver.findElements(By.cssSelector("a[href='/controls/roles/#]"));
//		Iterator<WebElement> searcheResults= searchResultsByName.iterator();
//		while(searcheResults.hasNext()) {
//		WebElement ele= searcheResults.next();
//		System.out.println("results "+ele.getText());
//			if(ele.getText().contains(searchQuery)) {
//				WebElement input = driver.findElement(By.cssSelector("input[placeholder='Search User']"));
//				input.sendKeys(Keys.COMMAND, "a");
//				input.sendKeys(Keys.DELETE);
//
//				return true;
//			}
		
		return false;	
		
		
	}

	public boolean SearchByUserEmail(String searchQuery) throws InterruptedException {
		
		Thread.sleep(1000);
		By searchOutputAccounts= By.xpath("//p[text()='"+searchQuery+"']");
		driver.findElement(By.xpath("//p[text()='Accounts']")).click();
		
		Thread.sleep(1000);
		WebElement input = driver.findElement(By.cssSelector("input[placeholder='Search User']"));
		waitFunctionVisibilityElement(input);
		driver.findElement(By.cssSelector("input[placeholder='Search User']")).sendKeys(searchQuery);
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		waitFunctionEnabledOrDisabled(By.xpath("//button[text()='Reset Filter']"));
		waitFunctionVisibility(searchOutputAccounts);
		System.out.println(driver.findElement(searchOutputAccounts).getText());
		if(driver.findElement(searchOutputAccounts).getText().contains(searchQuery)) {
			return true;
		}
		return false;
	}

}
