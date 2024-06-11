package Cucumber.Option;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import BaseSetup.BaseSetup;
import PageObject.PageObjectUIAPI;
import commonUtil.CustomListener;
import commonUtil.ExtentReportNG;
import commonUtil.ObjectRepoConfig;
import commonUtil.TestDataSIT;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.http.Method;

//@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources/features/UIFunctionality.feature",	glue= {"Stepdefination"}, plugin = { "pretty",
"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, publish = true, dryRun = false, monochrome = true)
@Listeners(CustomListener.class)
public class TestRunner extends AbstractTestNGCucumberTests{
	static WebDriver driver = null;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	
	@BeforeClass
	public static void beforeClass() {
		ObjectRepoConfig.initializeObjectRepofile();

	}

	@BeforeClass
	public static void beforeClass1() {
		TestDataSIT.initializePropertyfile();

	}
		
	@BeforeClass
	public static void beforeClassExtent() {
		ExtentReportNG.getReportObject();

	}

//	@BeforeClass
//	public static void before() {
//		PageFactory.initElements(driver, PageObjectUIAPI.class);
//
//	}
//	@BeforeMethod
//    public void beforemethod(Method m) {
//		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyy HH-mm-ss");
//		Date date = new Date();
//		String actualdate = format.format(date);
//		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/ExecutionsReport_"+actualdate+".html");
//		System.out.println("Test Case Name : "+m.name()); // enter testName, desc....
//    }
	
	@AfterMethod
	public static void failedScreenshot(ITestResult result) throws IOException {

		if (ITestResult.FAILURE == result.getStatus()) {
			test.log(Status.FAIL, "Test Case Failed is "+result.getName());
			test.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());
			String screenshotPath = BaseSetup.failed(driver, result.getName());
			test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(screenshotPath));
			//String screenshotPath = ExtentReportsClass.getScreenshot(driver, result.getName());
		}
		else if (ITestResult.SKIP == result.getStatus()) {
			test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName());
		}

		}


	@AfterMethod
	public void afterMethod() {
	ExtentReports extent = new ExtentReports();{
	    extent.flush();
	}

}

	
}
