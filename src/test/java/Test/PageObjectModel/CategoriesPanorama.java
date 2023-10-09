package Test.PageObjectModel;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test.AbstractComponent.AbstractComponent;

public class CategoriesPanorama extends AbstractComponent {

	public WebDriver driver;
	// By Locators
	AbstractComponent reusableMethods = new AbstractComponent(driver);
	By profileIcon = By.cssSelector(".MuiSvgIcon-root.MuiSvgIcon-fontSizeMedium.MuiAvatar-fallback.css-13y7ul3");
	By categoriesElement = By.xpath("//p[text()='Categories']");
	By SubmitButton = By.xpath("//button[text()='Submit']");
	By DeleteButton = By.xpath("//button[text()='Delete']");
	By resetButton = By.xpath("//button[text()='Reset Filter']");
	By categoryAddButton = By.xpath("//button[text()='Add New']");
	By filterButton = By.xpath("//div[@id='select-status']");
	By categoryList = By.cssSelector(".MuiBox-root.css-p38jk0");
	By resetFilter = By.xpath("//button[text()='Reset Filter']");
	By searchCategory = By.cssSelector("input[placeholder='Search Category']");
	By actionButton = By.xpath("//div[@role='cell']//button[@type='button']//*[name()='svg']");
	By options = By.xpath("//li[@role='menuitem']");
	By editButton = By.xpath("//li[text()='Edit']");
	By deleteButton = By.xpath("//li[text()='Delete']");
	By editBox = By.tagName("input");
	By deleteBox = By.cssSelector("input[name='inputText']");
	By selectStatus = By.cssSelector("div[aria-labelledby='status-select']");
	By pageCount = By.xpath("(//div[@aria-haspopup='listbox'])[2]");
	Random random = new Random();
	int randomNumberNew = random.nextInt(10000);
	String categoryNameNew = "test" + randomNumberNew;
	int randomNumberUpdated = random.nextInt(10000);
	String categoryNameUpdated = "test" + randomNumberUpdated;

	// Constructor to initialize driver
	public CategoriesPanorama(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Open category method
	public void opencategory() throws InterruptedException {
		System.out.println("New " + categoryNameNew + "Updated" + categoryNameUpdated);
		driver.findElement(By.xpath("//p[text()='Control Center']")).click();
		WebElement category = driver.findElement(By.xpath("//p[text()='Categories']"));
		Thread.sleep(1000);
		category.click();

	}

	// Adding a category and then searching the same
	public boolean addAndSearchCategory() throws InterruptedException {

		// Adding a category
		WebElement addButton = driver.findElement(By.xpath("//button[text()='Add New']"));
		addButton.click();
		WebElement categoryTextbox = driver.findElement(By.xpath("//input[@placeholder='Please enter category name']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(categoryTextbox));
		categoryTextbox.sendKeys(categoryNameNew);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SubmitButton));
		driver.findElement(SubmitButton).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		driver.navigate().refresh();
		// Searching the same category
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		wait.until(ExpectedConditions.elementToBeClickable(searchCategory));
		String searchedCategoryXpath = "//p[text()='" + categoryNameNew + "']";
		driver.findElement(searchCategory).sendKeys(categoryNameNew);
		By bySearchResult = By.xpath(searchedCategoryXpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchResult));
		String searchString = driver.findElement(By.xpath(searchedCategoryXpath)).getText();
		boolean searchResult;
		if (searchString.equals(categoryNameNew)) {
			searchResult = true;
		} else {
			searchResult = false;
		}
		return searchResult;

	}

	public boolean editAndDeletecategory() throws InterruptedException {

		// Editing a category
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(actionButton));
		driver.findElement(By.xpath("//div[@role='cell']//button[@type='button']//*[name()='svg']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
		driver.findElement(By.xpath("//li[text()='Edit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
		Thread.sleep(5000);
		Actions actions = new Actions(driver);
		WebElement categoryTextbox = driver.findElement(By.xpath("//input[@placeholder='Please enter category name']"));
		actions.click(categoryTextbox).keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).keyDown(Keys.DELETE)
				.perform();
		Thread.sleep(2000);
		categoryTextbox.sendKeys(categoryNameUpdated);
		wait.until(ExpectedConditions.elementToBeClickable(selectStatus));
		driver.findElement(selectStatus).click();
		driver.findElement(By.xpath("//li[text()='Inactive']")).click();
		driver.findElement(SubmitButton).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		wait.until(ExpectedConditions.elementToBeClickable(searchCategory));
		String searchedCategoryXpath = "//p[text()='" + categoryNameUpdated + "']";
		driver.findElement(searchCategory).sendKeys(categoryNameUpdated);
		By bySearchResult = By.xpath(searchedCategoryXpath);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchResult));
		String searchString = driver.findElement(By.xpath(searchedCategoryXpath)).getText();
		boolean searchResult;
		if (searchString.contains(categoryNameUpdated)) {
			searchResult = true;
		} else {
			searchResult = false;
		}

		// Deleting a category
		wait.until(ExpectedConditions.elementToBeClickable(actionButton));
		driver.findElement(By.xpath("//div[@role='cell']//button[@type='button']//*[name()='svg']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
		driver.findElement(deleteButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(deleteBox));
		driver.findElement(deleteBox).sendKeys("DELETE");
		wait.until(ExpectedConditions.elementToBeClickable(DeleteButton));
		driver.findElement(DeleteButton).click();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		wait.until(ExpectedConditions.elementToBeClickable(searchCategory));
		driver.findElement(searchCategory).sendKeys(categoryNameUpdated);
		By bySearchResultUpdated = By.xpath("//div[contains(text(),'No rows')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchResultUpdated));
		String searchStringUpdated = driver.findElement(bySearchResultUpdated).getText();
		System.out.println(searchStringUpdated);

		return searchResult;

	}

	// Reset Filter
	public void resetFilter() {
		driver.findElement(resetFilter).click();
	}

	public boolean activeFilterResult() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(pageCount));
		driver.findElement(pageCount).click();
		// Seting the page count to 50
		WebElement count = driver.findElement(By.xpath("//li[text()='50']"));
		wait.until(ExpectedConditions.visibilityOf(count));
		count.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		WebElement selectStatus = driver.findElement(By.cssSelector("#select-status"));
		selectStatus.click();
		driver.findElement(By.xpath("//li[text()='Active']")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		List<WebElement> activeList = driver.findElements(By.xpath("//span[text()='Active']"));
		Iterator<WebElement> iterator = activeList.iterator();
		boolean result = true;

		while (iterator.hasNext()) {
			WebElement element = iterator.next();
			if (element.getText().equals("Active")) {
				result = true;
			} else {
				result = false;
			}

		}
		return result;
		// new line 

	}

	public boolean inactiveFilterResult() throws InterruptedException {

		WebElement selectStatus = driver.findElement(By.cssSelector("#select-status"));
		selectStatus.click();
		driver.findElement(By.xpath("//li[text()='Inactive']")).click();
		List<WebElement> activeList = driver.findElements(By.xpath("//span[text()='Inactive']"));
		Iterator<WebElement> iterator = activeList.iterator();
		boolean result = true;
		while (iterator.hasNext()) {
			WebElement element = iterator.next();
			if (element.getText().equals("Inactive")) {
				result = true;
			} else {
				result = false;
			}
			// Perform actions on the WebElement (e.g., click, getText, etc.)
		}
		return result;

	}

	public void pagignation() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		resetFilter();
		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
		WebElement nextPageButton = driver
				.findElement(By.xpath("// button[@title='Go to next page']//*[name()='svg']"));
		boolean isEnabled = true;
		WebElement value = driver.findElement(By.cssSelector(".MuiTablePagination-displayedRows.css-1vlt1u6"));
		String currentPage = value.getText();
		String[] extractedNumbers = new String[3]; // Assuming you expect up to 3 numbers
		int index = 0;
		String nextPageValue = currentPage.split("of")[1].trim();
		System.out.println("Nextpagevalue " + nextPageValue);
		Pattern pattern = Pattern.compile("\\d+");
		// Create a matcher for the input string
		Matcher matcher = pattern.matcher(currentPage);
		String[] currentPageArray = new String[3];
		// Find and store all matched numbers
		while (matcher.find()) {
			String extractedNumber = matcher.group();
			System.out.println("Extracted number: " + extractedNumber);
			currentPageArray[index] = extractedNumber;
			index++;

		}
		String currentPageValue = currentPageArray[1];
		System.out.println(currentPageValue);
		int nextPageValueInt = Integer.parseInt(nextPageValue);
		int currentPageValueInt = Integer.parseInt(currentPageValue);

		// Check if the button is clickable (enabled) using ExpectedConditions

//        boolean isClickable = wait.until(ExpectedConditions.elementToBeClickable(nextPage)) != null;
//
//        while (isClickable) {
//           
//            nextPage.click();
//
//            // Wait for a brief moment (you can adjust the sleep time as needed)
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            // Scroll down to load more content
//            js.executeScript("window.scrollBy(0, 500)");
//
//            // Check if the new "Next Page" button is clickable
//            WebElement newNextPage = driver.findElement(By.xpath("//button[@aria-label='Go to next page']"));
//            isClickable = wait.until(ExpectedConditions.elementToBeClickable(newNextPage)) != null;
//            
//            if (isClickable) {
//                System.out.println("The button is clickable. Continuing to the next page.");
//            } else {
//                System.out.println("The button is not clickable. End of pagination.");
//            }
//        }

//		while (isEnabled) {
//			nextPage.click();
//			Thread.sleep(3000);
//			nextPage = driver.findElement(By.xpath("// button[@title='Go to next page']//*[name()='svg']"));
//			js.executeScript("window.scrollBy(0, 500);");
//			System.out.println("Pagignation" + nextPage.isEnabled());
//			String disabledAttribute = nextPage.getAttribute("disabled");
//			isEnabled = disabledAttribute == null || !disabledAttribute.equals("true");
//		}

//		boolean clickable = false;
//		if (clickableElement != null) {
//
//			clickable = true;
//		} else {
//			clickable = false;
//		}
//
//		while (clickable = true) {
//
//			System.out.println("The element is clickable or not . " + clickable + "");
//
//			nextPage.click();
//			Thread.sleep(3000);
//			js.executeScript("window.scroll(0,1000)");
//
//			if (clickableElement != null) {
//
//				clickable = true;
//			} else {
//				clickable = false;
//			}
//
//		}

//		driver.findElement(pageCount).click();
//		//Verifying with 10 data
//		
//		driver.findElement(By.xpath("//li[text()='10']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
//		String resultOf10=driver.findElement(By.cssSelector(".MuiTablePagination-displayedRows.css-1vlt1u6")).getText();
//		System.out.println("Results Of 10 "+resultOf10);
//		driver.findElement(pageCount).click();
//		driver.findElement(By.xpath("//li[text()='10']")).click();

//		resetFilter();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
//		wait.until(ExpectedConditions.elementToBeClickable(pageCount));
//		driver.findElement(pageCount).click();
//		//Verifying with 30 data
//		driver.findElement(By.xpath("//li[text()='30']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
//		js.executeScript("window.scroll(0,800)");
//		String resultOf30=driver.findElement(By.cssSelector(".MuiTablePagination-displayedRows.css-1vlt1u6")).getText();
//		resetFilter();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
//		wait.until(ExpectedConditions.elementToBeClickable(pageCount));
//		driver.findElement(pageCount).click();
//		//Verifying with 30 data
//		driver.findElement(By.xpath("//li[text()='50']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
//		js.executeScript("window.scroll(0,2000)");
//		wait.until(ExpectedConditions.elementToBeClickable(pageCount));
//		String resultOf50=driver.findElement(By.cssSelector(".MuiTablePagination-displayedRows.css-1vlt1u6")).getText();
//		System.out.println("Results Of 10 "+resultOf10+"Results of 30 "+resultOf30+"Results of 50 "+resultOf50);
//		
	}

}
