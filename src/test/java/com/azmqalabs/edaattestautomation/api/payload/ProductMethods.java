package com.azmqalabs.edaattestautomation.api.payload;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.apache.poi.util.SystemOutLogger;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.azmqalabs.edaattestautomation.api.common.commonMethods;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ProductMethods{

	ExtentReports extent;
	static ExtentTest test;

	public ProductMethods(ExtentTest test) {
		this.test = test;

	}
	public void Product(Map<Object, Object> testdatamap) {

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
				
			case "GETProduct":
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).queryParam("request.productName", testdatamap.get("request.productName").toString())
						.contentType(ContentType.JSON).queryParam("request.categoryId", testdatamap.get("request.categoryId").toString())
						.get(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();
				ResponseBody body =response.getBody();
				System.out.println(body.jsonPath().get("Body[0].ProductNameAr"));
				String bodyasString=body.asString();
				String msg=body.jsonPath().get("Status.Message").toString();
			
				if(String.valueOf(response.getStatusCode()).equals(testdatamap.get("StatusCode").toString())&&msg.equalsIgnoreCase("Products was retrived successfully"))
				{
					test.log(Status.PASS, "Pass");
				}
				else {
					test.log(Status.FAIL, "FAIL");
					
				}				

				break;
				
			case "GET":
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON)
						.when().get(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();
				test.log(Status.PASS, "Product GET Method ");
				Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;
			case "GETWITHProductId":

				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).pathParam("productId", testdatamap.get("ProductId").toString())
						.get(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();
				test.log(Status.PASS, response.asString());
				Assert.assertEquals(response.getStatusCode(),Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;
				
			case "GETWITHCode":
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
						.contentType(ContentType.JSON).pathParam("code", testdatamap.get("Code").toString())
						.get(URI + testdatamap.get("ParamQueryURL").toString());
				response.then().log().all();
				test.log(Status.PASS, response.asString());
				Assert.assertEquals(response.getStatusCode(),Integer.valueOf(testdatamap.get("StatusCode").toString()));
				break;	
			case "PUT":
				response = given().headers("Authorization", "bearer " + commonMethods.generateToken(URI))
				.contentType(ContentType.JSON).pathParam("productId", testdatamap.get("ProductId").toString())
				.body(testdatamap.get("RequestBody").toString())
				.when().put(URI + testdatamap.get("ParamQueryURL").toString());
		response.then().log().all();	
		Assert.assertEquals(response.getStatusCode(), Integer.valueOf(testdatamap.get("StatusCode").toString()));
		break;
			
	}
}
}