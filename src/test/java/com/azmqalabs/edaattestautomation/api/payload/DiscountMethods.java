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

public class DiscountMethods{

	ExtentReports extent;
	static ExtentTest test;

	public DiscountMethods(ExtentTest test) {
		this.test = test;

	}
	public void Discount(Map<Object, Object> testdatamap) {

			String URI = commonMethods.launchapiUrl();
			String Method = testdatamap.get("Method").toString();
			Response response = null;
			switch (Method) {						
			case "POST":	
			
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).accept(ContentType.JSON)
						.body(testdatamap.get("RequestBody").toString())
						.when().post(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();	
				Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;
			case "GET":
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON)
						.when().get(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();
				test.log(Status.PASS, "Product GET Method ");
				Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;
			case "GETWITHID":

				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).pathParam("discountId", testdatamap.get("DiscountId").toString())
						.get(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();
				test.log(Status.PASS, response.asString());
				Assert.assertEquals(response.getStatusCode(),Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;	
			case "PUT":	
				
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("discountId", testdatamap.get("DiscountId").toString())
						.body(testdatamap.get("RequestBody").toString())
						.when().put(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();	
				Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;	
			case "PUTACTIVE":	
				
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("discountId", testdatamap.get("DiscountId").toString())
						.when().put(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();	
				Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;	
			case "PUTDEACTIVE":	
			
			response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
					.contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("discountId", testdatamap.get("DiscountId").toString())
					.when().put(URI + testdatamap.get("ParamQueryURL").toString());
			response.then().log().all();	
			Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
			break;	
			
				
			
			
	}
}
}