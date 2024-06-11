package MethodsHandsOn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

import org.testng.Assert;

import PageObject.PageFactoryJson;
import PayLoad.PayLoad;
import groovy.json.JsonParser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class FirstMethod {
	public static void main(String[] args) {
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
//		JsonPath js1 = new JsonPath(getResponse);
		JsonPath js1 =PageFactoryJson.rawToJson(getResponse);
		String actAddress = js1.getString("address");
		Assert.assertEquals(actAddress, newAddress);
		System.out.println(actAddress);
		
		
		
	}

}
