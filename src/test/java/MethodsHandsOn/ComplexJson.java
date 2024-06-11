package MethodsHandsOn;

import PayLoad.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJson {
	public static void main(String[] args) {
		
	
	JsonPath js = new JsonPath(PayLoad.coursePrice());
	int courseNum = js.getInt("courses.size()");
	System.out.println("number of courses are " + courseNum);
	int purchasAmt = js.getInt("dashboard.purchaseAmount");
	System.out.println("purchase amount is " + purchasAmt);
	String fristCourseTitle = js.getString("courses[0].title");
	System.out.println("first course title is " + fristCourseTitle);
	for (int i = 0; i <= courseNum; i++) {
	String courseName = js.get("courses["+i+"].title");
	System.out.println(courseName);	
	if(courseName.equalsIgnoreCase("RPA")) {
		int copycount = js.get("courses["+i+"].copies");
		System.out.println("number of copies sold by rpa is " + copycount);
		break;
	}
	}
	int totalcount =0;
	
	for (int i = 0; i < courseNum ; i++) {
		int price = js.getInt("courses["+i+"].price");
		int qty = js.getInt("courses["+i+"].copies");
		int counttotal = price * qty;
		System.out.println(counttotal);
		totalcount = totalcount + counttotal;
		
	}
	
	System.out.println(totalcount);
	
	if(totalcount == purchasAmt) {
		System.out.println(totalcount + " is matching with " + purchasAmt);
	}
}

}
