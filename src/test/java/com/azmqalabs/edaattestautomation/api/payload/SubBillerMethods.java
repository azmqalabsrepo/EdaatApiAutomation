package com.azmqalabs.edaattestautomation.api.payload;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.azmqalabs.edaattestautomation.api.common.commonMethods;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SubBillerMethods{

	ExtentReports extent;
	static ExtentTest test;

	public SubBillerMethods(ExtentTest test) {
		this.test = test;

	}
	public void SubBiller(Map<Object, Object> testdatamap) {

			String URI = commonMethods.launchapiUrl();
			String Method = testdatamap.get("Method").toString(); 
			Response response = null;
			switch (Method) {
			case "GETsubBiller":
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).queryParam("request.companyName", testdatamap.get("request.companyName").toString())
						.contentType(ContentType.JSON).queryParam("request.registrationNo", testdatamap.get("request.registrationNo").toString())
						.contentType(ContentType.JSON).queryParam("request.taxNo", testdatamap.get("request.taxNo").toString())
						.get(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();
				test.log(Status.PASS, response.asString());
				Assert.assertEquals(response.getStatusCode(),Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;
			case "GETWITHRegistrationNo":

				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).pathParam("registrationNo", testdatamap.get("request.registrationNo").toString())
						.get(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();
				test.log(Status.PASS, response.asString());
				Assert.assertEquals(response.getStatusCode(),Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;	
		
			case "POST":	
				
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).accept(ContentType.JSON)
						.body(testdatamap.get("RequestBody").toString())
						.when().post(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();	
				Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;
			case "POSTWITHProduct":	
			
			response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
					.contentType(ContentType.JSON).accept(ContentType.JSON)
					.body(testdatamap.get("RequestBody").toString())
					.when().post(URI + testdatamap.get("ParamQueryURL").toString());
			response.then().log().all();	
			Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
			break;

			case "PUT":
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
				.contentType(ContentType.JSON).pathParam("registrationNo", testdatamap.get("registrationNo").toString())
				.body(testdatamap.get("RequestBody").toString())
				.when().put(URI + testdatamap.get("ParamQueryURL").toString());
		response.then().log().all();	
		Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
		break;
			
	}
}
}