package com.commentapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.commentapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC_002_Get_All_Comment extends TestBase {
	@Test
	public void getAllComment() {
		logger.info("*************** Get All Request *********");
		logger.info("");
		//for (Comment comment : comments) {
		RestAssured.baseURI = "https://5e9fa91311b078001679ca03.mockapi.io/dana";
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject RequestParams = new JSONObject();

		httpRequest.header("Accept", "application/json");

		httpRequest.body(RequestParams.toJSONString());


		Response response=httpRequest.request(Method.GET, "/comment");
		

		logger.info("**************** Checking Status Code *****************");
		int statusCode = response.getStatusCode();
		logger.info("Status code => " + statusCode);
		Assert.assertEquals(statusCode, 200);
		logger.info("");

		logger.info("*************** Checking Response Body ****************");
		String responseBody = response.getBody().asString();
		logger.info("Response body => " + responseBody);
		logger.info("");

		Assert.assertEquals(responseBody.contains("id"), true);
		Assert.assertEquals(responseBody.contains("postId"), true);
		Assert.assertEquals(responseBody.contains("name"), true);
		Assert.assertEquals(responseBody.contains("email"), true);
		Assert.assertEquals(responseBody.contains("body"), true);
		Assert.assertTrue(responseBody != null);


		logger.info("**************** Checking Content Type ****************");
		String contentType = response.header("Content-Type");
		logger.info("Content Type => " + contentType);
		Assert.assertEquals(contentType, "application/json");
		logger.info("");

		logger.info("**************** Checking Status Line *****************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line => " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		logger.info("");


	}


	@AfterClass
	void tearDown() {
		logger.info("======== TC_003_Get_All_Comment Finished ========");
	}
}
