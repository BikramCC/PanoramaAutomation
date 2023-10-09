package Test.BaseComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Test.PageObjectModel.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseComponentsDemo {
	public WebDriver driver;
	Properties prop = new Properties();

	public WebDriver InitializeDriver() {

//		WebDriverManager.chromedriver().setup();
//	
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		driver = new ChromeDriver(options);
		driver = new ChromeDriver();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String screenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(
				"/Users/codeclouds-bikramb/Downloads/eclipse-workspace/Frameworks/reports/" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return "/Users/codeclouds-bikramb/Downloads/eclipse-workspace/Frameworks/reports/" + testCaseName + ".png";
	}

}
