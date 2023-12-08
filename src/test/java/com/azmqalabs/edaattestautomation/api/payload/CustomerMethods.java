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

public class CustomerMethods{

	ExtentReports extent;
	static ExtentTest test;
//	String URI=commonMethods.launchapiUrl();
//	Log Log;

	public CustomerMethods(ExtentTest test) {
		this.test = test;

	}

	public void Customer(Map<Object, Object> testdatamap) {
		try {
			String URI = commonMethods.launchapiUrl();
			//System.out.println(URI + testdatamap.get("ParamQueryURL"));
			//System.out.println(commonMethods.generateToken(URI));
			String Method = testdatamap.get("Method").toString();
			Response response = null;
			switch (Method) {
		
			case "GET":

				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
				.contentType(ContentType.JSON).queryParam("request.isDeleted", testdatamap.get("boolean").toString())
				.get(URI + testdatamap.get("ParamQueryURL").toString());
		response.then().log().all();
		test.log(Status.PASS, response.asString());
		Assert.assertEquals(response.getStatusCode(),Integer.valueOf(testdatamap.get("StatusCode").toString()));
		break;
			case "GETWITHID":
			
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).pathParam("nationalId", testdatamap.get("NationalID").toString())
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
		}
			} catch (Exception e) {
			test.log(Status.FATAL, "Failed");
		}
	}
}
