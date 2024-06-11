package MethodsHandsOn;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import commonUtil.ObjectRepoConfig;
import junit.framework.Assert;

public class UIValidate {
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties prop;
	public static void main(String[] args) {
		try {
		fis = new FileInputStream("C:\\Users\\nutri\\RestAssured\\RestAPI\\src\\main\\java\\TestData\\testdatasit.properties");
		prop.load(fis);
		String nameExpected = prop.getProperty("UserNameDataLogin");
		driver = new ChromeDriver();
		WebElement nameEmp = driver.findElement(By.xpath(ObjectRepoConfig.property.getProperty("EmpProfileXpath")));
		String nameEmpData = nameEmp.getText();
		Assert.assertEquals(nameEmpData, nameExpected);
		System.out.println("Emp logged in is " + nameEmpData);
		
		}catch(Exception e) {
			e.getMessage();
		}
		

	}

}
