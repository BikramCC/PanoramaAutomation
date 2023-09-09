package Test.BaseComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Test.resources.ExtentReportNG;

public class Listners extends BaseComponentsDemo implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> threadSafe = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		test = extent.createTest(testName, "Test Description");
		threadSafe.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threadSafe.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Error logs and reason
		threadSafe.get().log(Status.FAIL, "Test Failed");
		threadSafe.get().fail(result.getThrowable());
		try {
			 driver =(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String testCaseName = result.getMethod().getMethodName();
		String FinalPath = null;

		try {
			FinalPath = screenShot(testCaseName, driver);
			System.out.println("inside try " + FinalPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Screenshot

		threadSafe.get().addScreenCaptureFromPath(FinalPath);

	}

	@Test
	public void Faliure() {

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
