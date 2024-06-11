package MethodsHandsOn;

import PayLoad.PayLoad;
import io.restassured.path.json.JsonPath;

public class StudentData {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(PayLoad.StudData());
		String nameData = js.getString("name[0]");
		System.out.println(nameData);

	}

}
