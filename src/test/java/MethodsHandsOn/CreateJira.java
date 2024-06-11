package MethodsHandsOn;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

import java.io.File;




public class CreateJira {
public static void main(String[] args) {
	
	RestAssured.baseURI="http://localhost:8080";
//	to fetch the session id and pass to next method
	SessionFilter session = new SessionFilter();
	String response =given().log().all().header("Conetent-type", "application/json").body("{\r\n\""
			+ "\"username\": \"RahulShetty\",\r\n\""
			+ "\r\n"
			+ "\"    \"password\": \"XXXX11\"\r\n\" +\r\n"
			+ "\r\n"
			+ "\"}\"").log().all().filter(session).when().post("/rest/auth/1/session").then().extract().toString();
	
//	 to add the comment in the already created jira
	given().pathParam("key", "10101").header("Conetent-type", "application/json").body("{\r\n"
			+ "    \"body\": \"This is a comment that only administrators can see.\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log()
	.all().assertThat().statusCode(201);
	
//	add an attachment with raised jira
	
	given().header("X-Atlassian-token", "no check").filter(session).pathParam("key", "10101").header("Content-type","multipart/form-data")
	.multiPart("file", new File("AttachTest.txt")).when().post("rest/api/2/issue/{key}/attachments")
.then().log().all().assertThat().statusCode(200);
	
	
}
}
