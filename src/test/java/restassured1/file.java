package restassured1;


import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class file {
	
	@Test(enabled = true,dependsOnMethods={"putAGNAMANU"})
	public void getAGNAMANU()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
		given()
		.get("user/agnamanum")
        .then()
		.statusCode(200).log().all();	
	}
	@SuppressWarnings("unchecked")
	@Test(enabled=true,dataProvider="postData")
	public void postAGNAMANU(String data)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.contentType(ContentType.JSON)
        .body(data)
		.when()
		.post("user")
		.then()
		.statusCode(200).log().all();
		
	}
	
	@SuppressWarnings("unchecked")
	@DataProvider(name="postData")
    public Object[][] providerPOST(){
		JSONObject j1 = new JSONObject();
        j1.put("username", "agnamanum");
        j1.put("firstName", "agna");
        j1.put("lastName","manu");
        j1.put("email", "agnamanu@gmail.com");
        j1.put("password", "1234agna");
        j1.put("phone","9876543210");
        j1.put("userStatus","1");
        
        Object[][] postData = {
            {j1.toString()}
        };        
        return postData;
    }
	@Test(enabled=true,dataProvider="putData",dependsOnMethods={"postAGNAMANU"})
	public void putAGNAMANU(String data)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.contentType(ContentType.JSON)
        .body(data)
		.when()
		.put("user/agnamanum")
		.then()
		.statusCode(200).log().all();
		
	}
	
	@SuppressWarnings("unchecked")
	@DataProvider(name="putData")
    public Object[][] providerPUT(){
		JSONObject j1 = new JSONObject();
        j1.put("username", "agnamanum");
        j1.put("firstName", "agna");
        j1.put("lastName","manu");
        j1.put("email", "agnamanu@gmail.com");
        j1.put("password", "1234agnam");
        j1.put("phone","9876543210");
        j1.put("userStatus","1");
        
        Object[][] putData = {
            {j1.toString()}
        };        
        return putData;
	}  
	@Test(enabled=true,dataProvider="deleteData",dependsOnMethods={"putAGNAMANU"})
	public void deleteAGNAMANU(String data)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.delete("user/"+data)
		.then()
		.statusCode(200).log().all();
		
	}
	@DataProvider(name="deleteData")
    public Object[][] providerDELETE(){
        Object[][] deleteData = {{"agnamanum"}};
        return deleteData;
    }
	@Test(enabled=true,dataProvider="loginData",dependsOnMethods={"postAGNAMANU"})
	public void loginAGNAMANU(String username, String password)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.queryParam("username", username)
        .queryParam("password", password)
		.get("user/login")
		.then()
		.statusCode(200).log().all();
	}
    @DataProvider(name="loginData")
    public Object[][] providerLogin(){
        Object[][] loginData = {{"agnamanum", "1234agna"}};
        return loginData;
    }
}