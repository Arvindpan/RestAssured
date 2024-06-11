package MethodsHandsOn;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.PageFactoryJson;
import PayLoad.PayLoad;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DynamicJson {
	
	@Test(dataProvider="booksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI=("http://216.10.245.166");
		String response = given().log().all().header("content-type","application/json").body(PayLoad.addBook(isbn,aisle))
		.when().post("Library/Addbook.php").then().assertThat().statusCode(200)
		.extract().asString();
		JsonPath js1 = PageFactoryJson.rawToJson(response);
		String idData = js1.get("ID");
		System.out.println(idData);
		
		given().header("content-type","application/json").body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+idData+"\"\r\n"
				+ " \r\n"
				+ "").when().delete("/Library/DeleteBook.php").then().assertThat().statusCode(200);
//		.body("msg", equalTo("book is successfully deleted"));
		
	}
	


	@DataProvider(name="booksData")
	public Object[][] getData() {
		return new Object[][] {{"gghh","7788"},{"iijj","8899"},{"kkll","1133"}};
	}

}
