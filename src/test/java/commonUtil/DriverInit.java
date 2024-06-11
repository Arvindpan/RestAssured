package commonUtil;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverInit {
	public WebDriver driver;
    private static DriverInit driverInit = null;

    public static DriverInit getInstance() {
        if (driverInit == null) {
            driverInit = new DriverInit();
        }
        return driverInit;
    }

    private DriverInit() {
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        this.driver.get("******");
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
