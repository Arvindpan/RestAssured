package commonUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import BaseSetup.BaseSetup;

public class CustomListener extends BaseSetup implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log((Status.PASS),"Test case:"+ result.getMethod().getMethodName()+ "is passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Failed Method");
		failed(driver, result.getName());
		test.log((Status.FAIL),"Test case:"+ result.getMethod().getMethodName()+ "is failed");
		test.log(Status.FAIL, result.getThrowable());
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyy HH-mm-ss");
		Date date = new Date();
		String actualdate = format.format(date);
		String screenshotPath = System.getProperty("user.dir") + "\\target\\screenShots_"+actualdate+".PNG";
		File dst = new File(screenshotPath);
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshotPath, "Test screen Failed screenshot");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentReportNG.getReportObject();
	
		
	}

	@Override
	public void onFinish(ITestContext context) {
		//close extent
		extent.flush();
		
		
		
	}

}
