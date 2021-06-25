package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetandPostExample {
@Test
	public void testGet(){
		baseURI = "https://reqres.in/api";
		given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[4].first_name",equalTo("George")).
			body("data.first_name",hasItems("George","Rachel"));
	}
	public void testpost() {
		Map<String,Object> map = new HashMap<String,Object>();
		//map.put("name", "Raghav");
		//map.put("job", "teacher");
		//System.out.println(map);
		JSONObject request = new JSONObject(map);
		request.put("name","Raghav");
		request.put("job", "teacher");
		System.out.println(request.toJSONString());
		baseURI = "https://reqres.in/api";
		given().
		header("Content-Type", "application/json").
		contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		post("/users").
		then().
		//statusCode(200);
		statusCode(201).log().all();
		
	}
}
