package Test.PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Test.AbstractComponent.AbstractComponent;

public class Dashboard extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".MuiTypography-root.MuiTypography-body1.css-z4422z")
	WebElement userNameBeloIcon;

	@FindBy(css = ".MuiSvgIcon-root.MuiSvgIcon-fontSizeMedium.MuiAvatar-fallback.css-13y7ul3")
	WebElement profileIcon;
	@FindBy(css = ".MuiTypography-root.MuiTypography-h6.css-1grpydv")
	WebElement userNameOnMessage;

	public Dashboard(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = "//li[@role='menuitem'][2]")
	WebElement logoutButton;

	public String greetingMessage() throws InterruptedException {

		profileIcon.click();
		String nameBelowIcon = userNameBeloIcon.getText();

		System.out.println("nameBelowIcon" + nameBelowIcon);
		String nameOnMessage = userNameOnMessage.getText().split("Welcome")[1].split("🥳")[0].split("!")[0].trim();

		System.out.println("name On Message " + nameOnMessage);
		return nameOnMessage;
	}
	public void logout() {

		profileIcon.click();
		logoutButton.click();
		
	}

}
