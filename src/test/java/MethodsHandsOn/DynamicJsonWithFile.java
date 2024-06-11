package MethodsHandsOn;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import com.google.common.io.Files;

public class DynamicJsonWithFile {
	public static void main(String[] args) {
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

}
