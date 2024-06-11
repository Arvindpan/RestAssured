package BaseSetup;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BaseSetup {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ExtentSparkReporter htmlReports;
	public static ExtentReports extent ;
	public static ExtentTest test;
	
	public WebDriver initialiseDriver() {
	try {
	FileInputStream fis = new FileInputStream("C:\\Users\\nutri\\RestAssured\\RestAPI\\src\\main\\java\\TestData\\testdatasit.properties");
	Properties prop = new Properties();
	prop.load(fis);
	String browser = prop.getProperty("browser");
	if(browser.equals("chrome")) {
		driver = new ChromeDriver();
		driver.get(prop.getProperty("url"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(prop.getProperty("userId"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(prop.getProperty("passwd"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().window().maximize();
	}
	else if(browser.equals("firefox")) {
		driver = new FirefoxDriver();
		driver.get(prop.getProperty("url"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(prop.getProperty("userId"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(prop.getProperty("passwd"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().window().maximize();
	}else if(browser.equals("edge")) {
		driver= new EdgeDriver();
		driver.get(prop.getProperty("url"));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(prop.getProperty("userId"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(prop.getProperty("passwd"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().window().maximize();
	}
	} catch(Exception e){
		e.getMessage();
	}
		return driver;
}
	public static String failed(WebDriver driver, String screenshotname) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		Screenshot logoImageScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		String path=System.getProperty("user.dir") + "\\FailedScreenShots\\TestFail"+dateName+".PNG";
		try {
			
			//ImageIO.write(logoImageScreenShot.getImage(), "PNG",
					//new File(System.getProperty("user.dir") + "\\ScreenShot\\TestFail.PNG"));
			ImageIO.write(logoImageScreenShot.getImage(), "PNG",
					new File(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
