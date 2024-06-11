package commonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import PageObject.PageObjectUIAPI;

public class ExtentReportNG extends PageObjectUIAPI {
	
	public ExtentReportNG(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static ExtentReports getReportObject() {
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyy HH-mm-ss");
		Date date = new Date();
		String actualdate = format.format(date);
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/ExecutionsReport_"+actualdate+".html");
		spark.config().setReportName("Web Automation Results");
		spark.config().setDocumentTitle("Test Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		spark.config().setDocumentTitle("UI API Automation Execution Report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("UI API Test Automation Execution");
		extent.setSystemInfo("Tester", "Automation QA");
		System.out.println("extent report details");
		return extent;
	}

}
