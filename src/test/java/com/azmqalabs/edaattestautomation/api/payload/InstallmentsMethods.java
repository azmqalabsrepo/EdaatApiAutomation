package com.azmqalabs.edaattestautomation.api.payload;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.apache.http.protocol.ResponseDate;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.azmqalabs.edaattestautomation.api.common.commonMethods;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class InstallmentsMethods{

	ExtentReports extent;
	static ExtentTest test;

	public InstallmentsMethods(ExtentTest test) {
		this.test = test;

	}
	public void Installment(Map<Object, Object> testdatamap) {

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
			
	}
}
}