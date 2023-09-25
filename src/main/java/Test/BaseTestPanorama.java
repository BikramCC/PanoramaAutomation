package Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Test.AbstractComponent.AbstractComponent;
import Test.BaseComponents.BaseComponentsDemo;
import Test.PageObjectModel.CategoriesPanorama;
import Test.PageObjectModel.Dashboard;
import Test.PageObjectModel.LoginPagePanorama;
import Test.PageObjectModel.LogoutPagePanorama;
import Test.PageObjectModel.RolesAndPermissionsPanorama;

public class BaseTestPanorama extends BaseComponentsDemo

{

	public BaseTestPanorama() {
		// Default constructor
	}

	WebDriver driver = InitializeDriver();
	LoginPagePanorama login = new LoginPagePanorama(driver);
	LogoutPagePanorama logout = new LogoutPagePanorama(driver);
	AbstractComponent reusableMethods = new AbstractComponent(driver);

	// By reference variables
	By loginButton = By.cssSelector("button[type='submit']");
	By profileIcon = By.cssSelector(".MuiSvgIcon-root.MuiSvgIcon-fontSizeMedium.MuiAvatar-fallback.css-13y7ul3");
	By categoryList = By.cssSelector(".MuiBox-root.css-p38jk0");
	// Extent Report
	private ExtentReports extent;
	private ExtentTest test;

//	@Test(priority = 1)
//	public void LoginWithInvalidCredentials() throws InterruptedException {
//
//		login.LandingPage();
//		reusableMethods.waitFunctionVisibility(loginButton);
//		String errorMessage = login.LoginWithInValidData("team@codeclouds.biz", "M@7EtR%@TkQdG!m");
//
//		Assert.assertEquals(errorMessage, "Invalid Email/Password.");
//
//	}
//
//	@Test(priority = 2)
//	public void LoginWithvalidCredentials() throws InterruptedException {
//		login.LandingPage();
//		reusableMethods.waitFunctionVisibility(loginButton);
//		Dashboard dash = login.LoginWithValidData("team@codeclouds.biz", "Qwer!12345");
//		reusableMethods.waitFunctionVisibility(profileIcon);
//		String nameOnMessage = dash.greetingMessage();
//		String nameBelowIcon = driver.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-body1.css-z4422z"))
//				.getText();
//		Assert.assertEquals(nameOnMessage, nameBelowIcon);
//		driver.navigate().refresh();
//		reusableMethods.waitFunctionVisibility(profileIcon);
//		dash.logout();
//
//	}
//
//	@Test(priority = 3)
//	public void MerchantLoginWithValidCredentials() throws InterruptedException {
//		Dashboard dash = new Dashboard(driver);
//
//		login.MerchantLogin("test@cc.com", "Qwer!12345");
//		reusableMethods.waitFunctionVisibility(profileIcon);
//		String nameOnMessage = dash.greetingMessage();
//		String nameBelowIcon = driver.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-body1.css-z4422z"))
//				.getText();
//		Assert.assertEquals(nameOnMessage, nameBelowIcon);
//		driver.navigate().refresh();
//		reusableMethods.waitFunctionVisibility(profileIcon);
//		dash.logout();
//	}

	@Test(priority = 4)
	public void andAndSearchCategories() throws InterruptedException {
		login.LandingPage();
		reusableMethods.waitFunctionVisibility(loginButton);
		Dashboard dash = login.LoginWithValidData("team@codeclouds.biz", "Qwer!12345");
		reusableMethods.waitFunctionVisibility(profileIcon);
		CategoriesPanorama category = new CategoriesPanorama(driver);
		category.opencategory();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		boolean activeResult = category.activeFilterResult();
		System.out.println(activeResult);
		assertTrue(activeResult);
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		Thread.sleep(3000);
		boolean inactiveResult = category.inactiveFilterResult();
		System.out.println(inactiveResult);
		assertTrue(inactiveResult);
		Thread.sleep(3000);
		boolean search = category.addAndSearchCategory();
		assertTrue(search);

	}

	@Test(priority = 5)
	public void editAndDeleteCAtegory() throws InterruptedException {

		CategoriesPanorama category = new CategoriesPanorama(driver);
		boolean searchResult = category.editAndDeletecategory();
		assertTrue(searchResult);

	}

	@Test(priority = 6)
	public void categoryPagignation() throws InterruptedException {

		CategoriesPanorama category = new CategoriesPanorama(driver);

		category.pagignation();

	}

//	@Test(priority = 4)
//	public void RolesAndPermissionsSearchUserByName() throws InterruptedException {
//		RolesAndPermissionsPanorama rolesObject = new RolesAndPermissionsPanorama(driver);
//		rolesObject.openRoles();
//		boolean resultOfName = rolesObject.SearchByUserName("Super Admin");
//		System.out.println("found or not found"+ resultOfName);
//		assertTrue(resultOfName);
//
//	}
//
//	@Test(priority = 5)
//	public void RolesAndPermissionsSearchUserByEmail() throws InterruptedException {
//		RolesAndPermissionsPanorama rolesObject = new RolesAndPermissionsPanorama(driver);
//		boolean resultOfEmail = rolesObject.SearchByUserEmail("brian@panoramatrack.com");
//		System.out.println("Email found or not " + resultOfEmail);
//		Assert.assertTrue(resultOfEmail);
//
//	}
//	@Test(priority=6)
//	public void Categories() throws InterruptedException {
//		CategoriesPanorama category= new CategoriesPanorama(driver);
//		category.opencategory();
//		category.addCategory();
//		
//	}
//	@Test(priority = 1)
//	public void LoginLimiter() throws InterruptedException {
//		test = extent.createTest("Test Example");
//		login.LandingPage();
//		reusableMethods.waitFunctionVisibility(loginButton);
//		String errorMessage = login.LoginLimit("team@codeclouds.biz", "M@7EtR%@TkQdG!m");
//
//		Assert.assertEquals(errorMessage, "Please try after 1 minutes");
//		extent.flush();
//
//	}

}
