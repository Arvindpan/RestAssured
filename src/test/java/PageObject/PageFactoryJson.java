package PageObject;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import commonUtil.ObjectRepoConfig;
import commonUtil.TestDataSIT;
import io.restassured.path.json.JsonPath;

public class PageFactoryJson {
	public static FileInputStream fis;
	public static Properties prop;
	public static WebDriver driver;
	
	public static JsonPath rawToJson(String response) {
		JsonPath js1 = new JsonPath(response);
		return js1;
	}

	public static void textBox(String usernNameloc, String userNameData) {
		driver.findElement(By.xpath(ObjectRepoConfig.property.getProperty(usernNameloc))).sendKeys(TestDataSIT.property.getProperty(userNameData));;	
	}
	
	public static void dropdown(String drodpwonXpath, String ItemToSelect) {
		Select drpCountry = new Select(driver.findElement(By.xpath(ObjectRepoConfig.property.getProperty(drodpwonXpath))));
		drpCountry.selectByVisibleText(TestDataSIT.property.getProperty(ItemToSelect));
	}
	
	public static void button(String UserRole ) {
		driver.findElement(By.xpath(ObjectRepoConfig.property.getProperty(UserRole))).click();
	}

	public static void menuItem(String PIMMenu) {
		driver.findElement(By.xpath(ObjectRepoConfig.property.getProperty(PIMMenu))).click();
	}
}
