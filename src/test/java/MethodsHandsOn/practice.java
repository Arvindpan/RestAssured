package MethodsHandsOn;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;

public class practice {

	public static void main(String[] args) {
		String Response =given().log().all().queryParam("key", "qaclick123").header("content-type", "application/json")
				.body(jsonData("Arvind", "test123")).when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

	}
	
	static String jsonData(String Name, String passwd) {
		return "{\r\n\""
				+ "\"username\": \""+Name+"\",\r\n\""
				+ "\r\n"
				+ "\"    \"password\": \""+passwd+"\"\r\n\" +\r\n"
				+ "\r\n"
				+ "\"}\"";
	}
	
		@DataProvider(name="ArvindsData")
		public Object[][] getData(){
			return new Object[][] {{"saras","mishra"},{"aaakash", "shukla"},{"raman","deep"}};
		}

}
