package commonUtil;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

import junit.framework.Assert;



public class AccessibilityTest {
	WebDriver driver;
	private static final URL scripturl = AccessibilityTest.class.getResource("/axe.min.js");
	
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.get("https://amazon.com");
	}
	
	@Test
	public void WEbAllyTest() {
	JSONObject jsonobject = new AXE.Builder(driver, scripturl).analyze();
	JSONArray violations = jsonobject.getJSONArray("violations");
	
	if(violations.length() == 0) {
		System.out.println("no errors");
	}else {
		AXE.writeResults("WEbAllyTest", jsonobject);
		Assert.assertTrue(AXE.report(violations), false);
	}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
