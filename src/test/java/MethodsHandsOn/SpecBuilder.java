package MethodsHandsOn;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.RequestBuilder;

public class SpecBuilder {
public static void main(String[] args) {
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
}
