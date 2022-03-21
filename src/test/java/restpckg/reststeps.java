package restpckg;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class reststeps {
	
	
	@Test
	public void create(){
		try{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		
		obj.put("username", "IronMan");
		obj.put("firstName", "Tony");
		obj.put("lastName", "Stark");
		obj.put("email", "tonystark@gmail.com");
		obj.put("password", "Jarvis");
		obj.put("phone", "9876543210");
		obj.put("userStatus", 1);
		
		given()
			.contentType(ContentType.JSON).body(obj.toJSONString()).
		when()
			.post("/user").
		then()
			.statusCode(200).log().all();}
		catch(Exception e){
			System.out.println("Unable to create new user");
		}
	}
	
	@Test(dependsOnMethods="create",priority=1)
	public void update(){
		try{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		
		obj.put("username", "IronMan");
		obj.put("firstName", "Tony");
		obj.put("lastName", "Stark");
		obj.put("email", "tonystark@gmail.com");
		obj.put("password", "Jarvis12345");
		obj.put("phone", "9876543210");
		obj.put("userStatus", 1);
		
		given()
			.contentType(ContentType.JSON).body(obj.toJSONString()).
		when()
			.put("/user/IronMan").
		then()
			.statusCode(200).log().all();}
		catch(Exception e){
			System.out.println("Unable to update the user details");
		}
	}
	
	
	@Test(dependsOnMethods="create",priority=2)
	public void login(){
		try{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
			.queryParam("username", "IronMan").queryParam("password", "Jarvis12345").get("/user/login").
		then()
			.statusCode(200).log().all();}
		catch(Exception e){
			System.out.println("Unable to login with valid credentials");
		}
	}
	
	@Test(dependsOnMethods="login")
	public void logout(){
		try{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
			.get("/user/logout").
		then()
			.statusCode(200).log().all();}
		catch(Exception e){
			System.out.println("Unable to logout");
		}
	}
	
	@Test(dependsOnMethods="logout")
	public void delete(){
		try{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		given()
			.contentType(ContentType.JSON).
		when()
			.delete("/user/IronMan").
		then()
			.statusCode(200).log().all();}
		catch(Exception e){
			System.out.println("Unable to delete the user from database");
		}
	}
}
