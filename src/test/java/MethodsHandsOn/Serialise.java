package MethodsHandsOn;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialise {
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
	String response = given().queryParam("key", "qaclick123").header("Content-type", "application/json").body(a)
	.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
	System.out.println(response);
}
}
