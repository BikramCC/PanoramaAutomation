package Test.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//reports//report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation result of Panorama Track");
		reporter.config().setDocumentTitle("Panorama Track");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name", "Bikram");
		return extent;
		
	}

}
