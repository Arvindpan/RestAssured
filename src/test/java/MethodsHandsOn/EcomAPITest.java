package MethodsHandsOn;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonParseException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;

public class EcomAPITest {

	public static void main(String[] args) {
		
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
		
		
//		 add product
		RequestSpecification reqProd = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/api/ecom/product/add-product")
				.addHeader(token, "authorization").build();
		RequestSpecification reqAddProduct =given().log().all().spec(reqProd).param("productName", "Laptop").param("productAddedBy", userId).param("productCategory", "fashion")
		.param("productSubCategory", "shirts").param("productPrice", "11500").param("productDescription", "Lenova")
		.param("productFor", "men").multiPart("productImage", new File("C:\\Users\\nutri\\OneDrive\\Desktop\\fix_parsing.txt"));
		
		String prodAddResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		JsonPath  js = new JsonPath(prodAddResponse);
		String prodId =js.get("productId");
		System.out.println(prodId);
		
//		create order
		
		RequestSpecification reqOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader(token, "authorization").setContentType(ContentType.JSON).build();
		
//		RequestSpecification reqSpec =given().log().all().spec(reqOrder).body("")
	}

}
