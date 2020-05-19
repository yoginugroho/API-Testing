package com.commentapi.testcases;

import com.api.models.Comment;
import com.commentapi.base.TestBase;
import com.commentapi.utilities.RestUtils;


import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class TC_004_Update_Comment extends TestBase {
	@SuppressWarnings("unchecked")
	
	@DataProvider(name="testData")
	Object[][] dataProviderMethod() throws IOException {
		for(Comment comment : comments) {
			comment.setBody("Update Body"+ RestUtils.commentBody());
		}
		
		int row = comments.size();
		int column = 4;
		Object[][] dataTest=new Object[row][column+1];
		int i=0;
		for(Comment comment : comments) {
			dataTest[i][0] = comment.getCommentId();
			dataTest[i][1] = comment.getPostId();
			dataTest[i][2] = comment.getName();
			dataTest[i][3]=comment.getEmail();
			dataTest[i][4]=comment.getBody();
			i++;
		}
		
		return dataTest;
	}
	
	@Test(dataProvider="testData")
	public void updateComment(int commentId,int postId, String name, String email, String body) {
		
		
		//for (Comment comment : comments) {
			logger.info("Post Id :"+postId+" -Name :"+name+
			" -Email :"+email+" -Body :"+body);
			RestAssured.baseURI = "https://5e9fa91311b078001679ca03.mockapi.io/dana";
			RequestSpecification httpRequest = RestAssured.given();
			JSONObject RequestParams = new JSONObject();

			RequestParams.put("postId", postId);
			RequestParams.put("name", name);
			RequestParams.put("email",email);
			RequestParams.put("body",body);
		
			
			httpRequest.header("Content-Type", "application/json");

			httpRequest.body(RequestParams.toJSONString());
			

			Response response=httpRequest.request(Method.PUT, "/comment/"+commentId);
			
			
			logger.info("**************** Checking Status Code *****************");
			int statusCode = response.getStatusCode();
			logger.info("Status code => " + statusCode);
			Assert.assertEquals(statusCode, 200);
			logger.info("");
			
			logger.info("*************** Checking Response Body ****************");
			String responseBody = response.getBody().asString();
			logger.info("Response body => " + responseBody);
			logger.info("");
			
			Assert.assertEquals(responseBody.contains("\"id\":"+"\""+commentId+"\""), true);
			Assert.assertEquals(responseBody.contains("\"postId\":"+postId), true);
			Assert.assertEquals(responseBody.contains("\"name\":"+"\""+name+"\""), true);
			Assert.assertEquals(responseBody.contains("\"email\":"+"\""+email+"\""), true);
			Assert.assertEquals(responseBody.contains("\"body\":"+"\""+body+"\""), true);
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
		logger.info("======== TC_002_Update_Comment Finished ========");
	}

}
