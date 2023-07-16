package org.testing;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

import java.io.File;
import java.io.IOException;

import org.base.Baseclass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Gittest extends Baseclass
{
	
	@BeforeMethod
	public void beforeMethod() throws IOException 
	{
		RestAssured.baseURI = getproperties("BaseURI");
	}
	@Test(priority = 0);;----
       public void getUser()
	{
		RequestSpecification reqSpec = RestAssured.given();
       reqSpec.get("users/Krk098789").then().assertThat().statusCode(200).body("id", isA(Integer.class)).log().all();
	}
	@Test(priority = 1)
	public void getRepo()
	{ 
		RequestSpecification reqSpec = RestAssured.given();
     reqSpec.get("users/Krk098789/repos").then().assertThat().statusCode(200).body("[0].name",equalTo("Rk123")).log().all();
	}
	@Test(priority = 2)
	private void createUser() throws IOException 
	{
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.header("Authorization", "Bearer "+getproperties("token") ).header("Content-Type", "application/json")
		.body(new File(getproperties("jsonPathOfCreateRepo"))).when().post("user/repos").then().assertThat()
		.statusCode(201).body("name", equalTo("New-Repo")).log().all();
	}
	@Test(priority = 3)
	public void updateUser() throws IOException {
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.header("Authorization", "Bearer "+getproperties("token")).header("Content-Type", "application/json")
		.body(new File(getproperties("jsonPathOfUpdateRepo"))).when().patch("repos/Krk098789/New-Repo")
		.then().assertThat().statusCode(200).body("name", equalTo("Update-Repo")).log().all();
	}
	@Test(priority = 4)
	public void deleteUser() throws IOException {
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.header("Authorization", "Bearer "+getproperties("token")).when()
		.delete("repos/Krk098789/Update-Repo").then().assertThat().statusCode(204).log().all();
	}
	

}
