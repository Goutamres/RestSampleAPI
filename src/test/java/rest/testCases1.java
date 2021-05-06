package rest;



import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testCases1 {
	
	
	
	@Test
	public void GetAllVideos() {				
		
		Response res = 
		given()
		.when()
			.get("http://localhost:8080/app/videogames")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
			
					
	}
	
	
	@Test
	
	public void PostVideoGames() {
		
		
		String json = "{\"name\":\"eaCricket\",\"rating\":\"Global\",\"id\":191,\"reviewScore\":1,\"category\":\"sports\",\"releaseDate\":\"2021-05-06T07:21:31.512Z\"}";
		
		
		Response re = 
		given().urlEncodingEnabled(true)
			.contentType("application/json")
			.body(json)	

			
		.when()
			.post("http://localhost:8080/app/videogames")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		AssertJUnit.assertEquals(re.asString().contains("\"Record Added Successfully\""), true);
		
		given()
		.when()	
			.get("http://localhost:8080/app/videogames/191")
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id", equalTo("191"))
			.body("videoGame.name", equalTo("eaCricket"));
		
		
		String json2 = "{\"name\":\"eaCricket1234\",\"rating\":\"Global\",\"id\":191,\"reviewScore\":3,\"category\":\"sports\",\"releaseDate\":\"2021-05-06T07:21:31.512Z\"}";
		
		
		given()
			.contentType("application/json")
			.body(json2)
		.when()
			.put("http://localhost:8080/app/videogames/191")
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id", equalTo("191"))
			.body("videoGame.name", equalTo("eaCricket1234"));
		
		
		Response res = given()
		.when()
			.delete("http://localhost:8080/app/videogames/191")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
			AssertJUnit.assertEquals(res.asString().contains("Record Deleted Successfully"), true);
	}
	
	

}
