package Stepdefination;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import BaseSetup.BaseSetup;
import PageObject.PageObjectUIAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UIAPITest extends BaseSetup {
	static WebDriver driver;
	PageObjectUIAPI cap = PageFactory.initElements(driver, PageObjectUIAPI.class);
	
	
	@Test
	@Given("Add the place Payload and verify user from website")
	public void add_the_place_payload_and_verify_user_from_website() throws FileNotFoundException{
		
	  driver = initialiseDriver();
	  cap.UIValidate();
	  cap.Oauth2TestData();
	  cap.SpecBuilder();
	}

	@When("user call {string} with POST http request")
	public void user_call_with_post_http_request(String string) {
	   cap.ResponseSpec();
	}

	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
	   cap.StatusCodeVerify();
	}

	@Then("{string} in response code is {string}")
	public void in_response_code_is(String Expkey, String Expvalue) {
	   cap.StatusVerify();
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
	   
	}


}
