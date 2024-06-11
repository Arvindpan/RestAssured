package PageObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MethodsHandsOn.LoginCLassPOJO;
import MethodsHandsOn.LoginResponsePayload;
import MethodsHandsOn.SerialisePOJO;
import MethodsHandsOn.getCource;
import MethodsHandsOn.miniSubCourses;
import MethodsHandsOn.miniSubCoursesapi;
import MethodsHandsOn.sublocation;
import PageObject.PageFactoryJson;
import groovy.json.JsonParser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import PayLoad.PayLoad;
import commonUtil.CustomListener;
import commonUtil.ObjectRepoConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
@Listeners(CustomListener.class)
public class PageObjectUIAPI extends PageFactoryJson {
	public static WebDriver driver;
	public static ExtentReports extent ;
	public static ExtentTest test;
	static Logger log = LogManager.getLogger("PageObjectUIAPI");
	
	
	public PageObjectUIAPI(WebDriver driver)  {
		PageObjectUIAPI.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static void UIValidate() {
		
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
	
	public static void ComplexJson() {
		
		Logger log = LogManager.getLogger("ComplexJson");
		JsonPath js = new JsonPath(PayLoad.coursePrice());
		int courseNum = js.getInt("courses.size()");
		System.out.println("number of courses are " + courseNum);
		int purchasAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println("purchase amount is " + purchasAmt);
		String fristCourseTitle = js.getString("courses[0].title");
		System.out.println("first course title is " + fristCourseTitle);
		for (int i = 0; i <= courseNum; i++) {
		String courseName = js.get("courses["+i+"].title");
		System.out.println(courseName);	
		if(courseName.equalsIgnoreCase("RPA")) {
			int copycount = js.get("courses["+i+"].copies");
			System.out.println("number of copies sold by rpa is " + copycount);
			break;
		}
		}
		int totalcount =0;	
		for (int i = 0; i < courseNum ; i++) {
			int price = js.getInt("courses["+i+"].price");
			int qty = js.getInt("courses["+i+"].copies");
			int counttotal = price * qty;
			System.out.println(counttotal);
			totalcount = totalcount + counttotal;	
		}
		System.out.println(totalcount);
		
		if(totalcount == purchasAmt) {
			System.out.println(totalcount + " is matching with " + purchasAmt);
		}
		
		test.log(Status.INFO, totalcount + " is the calculated purchase amount ");
	}
	
	public static void CreateJira() {
		
			
			Logger log = LogManager.getLogger("CreateJira");
			
			RestAssured.baseURI="http://localhost:8080";
//			to fetch the session id and pass to next method
			SessionFilter session = new SessionFilter();
			String response =given().log().all().header("Conetent-type", "application/json").body("{\r\n\""
					+ "\"username\": \"RahulShetty\",\r\n\""
					+ "\r\n"
					+ "\"    \"password\": \"XXXX11\"\r\n\" +\r\n"
					+ "\r\n"
					+ "\"}\"").log().all().filter(session).when().post("/rest/auth/1/session").then().extract().toString();
			test.log(Status.INFO, " Session token has been saved ");
			
//			 to add the comment in the already created jira
			given().pathParam("key", "10101").header("Conetent-type", "application/json").body("{\r\n"
					+ "    \"body\": \"This is a comment that only administrators can see.\",\r\n"
					+ "    \"visibility\": {\r\n"
					+ "        \"type\": \"role\",\r\n"
					+ "        \"value\": \"Administrators\"\r\n"
					+ "    }\r\n"
					+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log()
			.all().assertThat().statusCode(201);
			test.log(Status.INFO, " Cooment has been added to the jira ");
			
//			add an attachment with raised jira
			
			given().header("X-Atlassian-token", "no check").filter(session).pathParam("key", "10101").header("Content-type","multipart/form-data")
			.multiPart("file", new File("AttachTest.txt")).when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);	
			test.log(Status.INFO, " Given file attached with the jira ");
		}
	
	public static void DynamicJsonWithFile() {
			File jsonData = new File("C:\\Users\\nutri\\OneDrive\\Desktop\\addPlace.json");
			RestAssured.baseURI="https://rahulshettyacademy.com/";
			String Response =given().log().all().queryParam("key", "qaclick123").header("content-type", "application/json")
			.body(jsonData).when().post("/maps/api/place/add/json")
			.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
			System.out.println(Response);
			JsonPath js = new JsonPath(Response);
			String placeId = js.getString("place_id");
			System.out.println("Generated place id is " + placeId);
		}
	
	public static void EcomAPITest() {

			RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
					.setContentType(ContentType.JSON).build();
			LoginCLassPOJO login = new LoginCLassPOJO();
			login.setUserEmail("rahulshetty@gmail.com");
			login.setUserPassword("Iamking@000");
			RequestSpecification reqLogin =given().log().all().spec(req).body(login);
			LoginResponsePayload loginResp = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract()
					.response().as(LoginResponsePayload.class);	
			String token = loginResp.getToken();
			String userId = loginResp.getUserId();
			System.out.println(token);
			
//			 add product
			RequestSpecification reqProd = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/api/ecom/product/add-product")
					.addHeader(token, "authorization").build();
			RequestSpecification reqAddProduct =given().log().all().spec(reqProd).param("productName", "Laptop").param("productAddedBy", userId).param("productCategory", "fashion")
			.param("productSubCategory", "shirts").param("productPrice", "11500").param("productDescription", "Lenova")
			.param("productFor", "men").multiPart("productImage", new File("C:\\Users\\nutri\\OneDrive\\Desktop\\fix_parsing.txt"));
			
			String prodAddResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
			JsonPath  js = new JsonPath(prodAddResponse);
			String prodId =js.get("productId");
			System.out.println(prodId);
}
	
	public static void FirstMethod() {
			RestAssured.baseURI="https://rahulshettyacademy.com/";
			String Response =given().log().all().queryParam("key", "qaclick123").header("content-type", "application/json")
			.body(PayLoad.AddPalce()).when().post("/maps/api/place/add/json")
			.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
			System.out.println(Response);
			JsonPath js = new JsonPath(Response);
			String placeId = js.getString("place_id");
			System.out.println("Generated place id is " + placeId);
			
			 String newAddress = "Summer Walk, Africa";
			 
			 given().log().all().queryParam("key", "qaclick123").header("content-type","application/json")
			 .body("{\r\n"
			 		+ "\"place_id\":\""+placeId+"\",\r\n"
			 		+ "\"address\":\""+newAddress+"\",\r\n"
			 		+ "\"key\":\"qaclick123\"\r\n"
			 		+ "}\r\n"
			 		+ "").when().put("/maps/api/place/update/json").then().assertThat().statusCode(200)
			 .body("msg", equalTo("Address successfully updated"));
			
			String getResponse =  given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
			 .when().get("/maps/api/place/get/json").then().assertThat().statusCode(200)
			 .extract().response().asString();
			System.out.println(getResponse);
//			JsonPath js1 = new JsonPath(getResponse);
			JsonPath js1 =PageFactoryJson.rawToJson(getResponse);
			String actAddress = js1.getString("address");
			Assert.assertEquals(actAddress, newAddress);
			System.out.println(actAddress);
	
		}
	
	public static void Oauth2TestData() {

			String[] courseTit = {"Selenium Webdriver Java", "Cypress", "Protractor"};
			String response = given().formParams("client_id",
							"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
							.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
							.formParams("grant_type", "client_credentials")
							.formParams("scope", "trust")
							.when().log().all()
							.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
			System.out.println(response);
			JsonPath jsonPath = new JsonPath(response);
			String accessToken = jsonPath.getString("access_token");
			System.out.println(accessToken);
			
			
			getCource gc = given()
					.queryParams("access_token", accessToken)
					.when().log().all()
					.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(getCource.class);
					
			System.out.println(gc.getLinkedIn());
			System.out.println(gc.getInstructor());
			System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
			List<miniSubCoursesapi>apiCources = gc.getCourses().getApi();
			for (int i = 0; i < apiCources.size(); i++) {
				if(apiCources.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
					System.out.println(apiCources.get(i).getPrice());
				}			
			}
			
			List<miniSubCourses>courseTitleData=gc.getCourses().getWebAutomation();
			for (int i = 0; i < courseTitleData.size(); i++) {
				System.out.println(courseTitleData.get(i).getCourseTitle());
				System.out.println("expected course is " + courseTit[i]);
				String actualCourseTit = courseTitleData.get(i).getCourseTitle();
				Assert.assertEquals(courseTit[i], actualCourseTit);
			}
		}
	
	public static void Serialise() {
	
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		SerialisePOJO a = new SerialisePOJO();
		sublocation loc = new sublocation();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);
		a.setAccuracy(50);
		List<String>myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		a.setName("Frontline house");
		a.setPhone_number("+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		String response = given().queryParam("key", "qaclick123").header("Content-type", "application/json").body(a)
		.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
	}
	
	public static void SpecBuilder() throws FileNotFoundException {
		PrintStream logData = new PrintStream(new FileOutputStream("logging.text"));
			RestAssured.baseURI="https://rahulshettyacademy.com/";
			SerialisePOJO a = new SerialisePOJO();
			sublocation loc = new sublocation();
			loc.setLat(-38.383494);
			loc.setLng(33.427362);
			a.setLocation(loc);
			a.setAccuracy(50);
			List<String>myList = new ArrayList<String>();
			myList.add("shoe park");
			myList.add("shop");
			a.setName("Frontline house");
			a.setPhone_number("+91) 983 893 3937");
			a.setAddress("29, side layout, cohen 09");
			a.setWebsite("http://google.com");
			a.setLanguage("French-IN");
			
			RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(logData)).addFilter(ResponseLoggingFilter.logResponseTo(logData)).setContentType(ContentType.JSON).build();
			
			ResponseSpecification respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			
			RequestSpecification res = given().spec(req).body(a);
			
			Response responseData =res.when().post("/maps/api/place/add/json").then().spec(respec).extract().response();
			String resString = responseData.toString();
			System.out.println(resString);
		}
	
	public static void RequestSpec() {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		SerialisePOJO a = new SerialisePOJO();
		sublocation loc = new sublocation();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);
		a.setAccuracy(50);
		List<String>myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		a.setName("Frontline house");
		a.setPhone_number("+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		
	}
	
	public static void ResponseSpec() {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		SerialisePOJO a = new SerialisePOJO();
		sublocation loc = new sublocation();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);
		a.setAccuracy(50);
		List<String>myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		a.setName("Frontline house");
		a.setPhone_number("+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		ResponseSpecification respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req).body(a);
		
		Response responseData =res.when().post("/maps/api/place/add/json").then().spec(respec).extract().response();
		String resString = responseData.toString();
		System.out.println(resString);
		
	}
	public static void StatusCodeVerify() {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		SerialisePOJO a = new SerialisePOJO();
		sublocation loc = new sublocation();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);
		a.setAccuracy(50);
		List<String>myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		a.setName("Frontline house");
		a.setPhone_number("+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		ResponseSpecification respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req).body(a);
		
		Response responseData =res.when().post("/maps/api/place/add/json").then().spec(respec).extract().response();
		String resString = responseData.toString();
		System.out.println(resString);
		
	}
	
	public static void StatusVerify() {
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		SerialisePOJO a = new SerialisePOJO();
		sublocation loc = new sublocation();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);
		a.setAccuracy(50);
		List<String>myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		a.setName("Frontline house");
		a.setPhone_number("+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		ResponseSpecification respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req).body(a);
		
		Response responseData =res.when().post("/maps/api/place/add/json").then().spec(respec).extract().response();
		String resString = responseData.toString();
		System.out.println(resString);
		
	}
	
	public static void StudentData() {

			JsonPath js = new JsonPath(PayLoad.StudData());
			String nameData = js.getString("name[0]");
			System.out.println(nameData);
		}

}


