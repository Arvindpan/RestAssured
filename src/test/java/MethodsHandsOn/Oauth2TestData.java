package MethodsHandsOn;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.Assert;

public class Oauth2TestData {

	public static void main(String[] args) {
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
}
