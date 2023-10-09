package Test.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Test.AbstractComponent.AbstractComponent;

public class LogoutPagePanorama {
	WebDriver driver;
	

	
	public LogoutPagePanorama(WebDriver driver) {
		this.driver=driver;

	}
	AbstractComponent reusableMethods = new AbstractComponent(driver);
	@FindBy(css = ".MuiSvgIcon-root.MuiSvgIcon-fontSizeMedium.MuiAvatar-fallback.css-13y7ul3")
	WebElement profileIcon;
	
	@FindBy(xpath = "//li[@role='menuitem'][2]")
	WebElement loginButton;
	
	
	public void logout() {
		profileIcon.click();
		reusableMethods.waitFunctionVisibilityElement(profileIcon);
		loginButton.click();
		
	}
}
