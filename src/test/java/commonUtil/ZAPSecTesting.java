package commonUtil;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import junit.framework.Assert;

public class ZAPSecTesting {

	

		static final String ZAP_PROXY_ADDRESS = "localhost";
		static final int ZAP_PROXY_PORT = 8080;
		static final String ZAP_API_KEY = "APIKEY";
		
		private WebDriver driver;
		private ClientApi api;
		
		@BeforeMethod
		public void setup() {
			String ProxyServerUrl= ZAP_PROXY_ADDRESS + ":"+ ZAP_PROXY_PORT;
			Proxy proxy = new Proxy();
			proxy.setHttpProxy(ProxyServerUrl);
			proxy.setSslProxy(ProxyServerUrl);
			
			ChromeOptions co = new ChromeOptions();
			co.setAcceptInsecureCerts(true);
			co.setProxy(proxy);
			driver = new ChromeDriver(co);
			api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);

		}
		
		@Test
		public void WebSecurityTest() {
				driver.get("https://amazon.com");
				Assert.assertTrue(driver.getTitle().contains("Amazon"));
	
		}
			
		@AfterMethod
		public void tearDown() {
			String title = "Web Test report";
			String template = "traditional-html";
			String description = "This is web security testing report";
			String reportFileName ="WebSecurityTesting.html";
			String targetFolder = System.getProperty("user.dir");
			
			if(api != null) {
				try {
					ApiResponse response = api.reports.generate(title, template, null, description, null,
							null, null, null, null, reportFileName, null, 
							targetFolder, null);
					System.out.println("Zap report is generated at this location : " + response.toString());
				} catch (ClientApiException e) {
					e.printStackTrace();
				}
			}
			driver.quit();
		}
	
//		https://github.com/zaproxy/zap-api-java   ---- zap api documentation
//		zap-cli quick-scan -s xss,sqli --spider -r -e "some_regex_pattern" http://demo.testfire.net/ && zap-cli report -o abc.html -f html
//		command to generate report using cli
}
