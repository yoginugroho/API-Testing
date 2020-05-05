package com.commentapi.testcases;

import com.api.models.*;
import com.commentapi.utilities.RestUtils;
import com.commentapi.base.TestBase;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC_001_Create_Comment extends TestBase {
	@SuppressWarnings("unchecked")
	
	@BeforeClass
	public void createUser() {
		logger.info("Creating user...");
		for(int i=0; i<10;i++) {
			String firstName=RestUtils.firstName();
			String lastName=RestUtils.LastName();
			String email=RestUtils.email();
			String username=RestUtils.username();
			String password=RestUtils.password();
			users.add(new User(firstName,lastName, email,username,password));
			
			
		}
		
		for (User user : users) {
			RestAssured.baseURI = "https://5e9fa91311b078001679ca03.mockapi.io/dana";
			RequestSpecification httpRequest = RestAssured.given();
			JSONObject RequestParams = new JSONObject();

			RequestParams.put("firstName", user.getFirstName());
			RequestParams.put("lastName", user.getLastName());
			RequestParams.put("email", user.getEmail());
			RequestParams.put("username", user.getUsername());
			RequestParams.put("password", user.getPassword());
			
			httpRequest.header("Content-Type", "application/json");

			httpRequest.body(RequestParams.toJSONString());

			Response response=httpRequest.request(Method.POST, "/user");
			try {
				JSONParser parser = new JSONParser(); 
				JSONObject json = (JSONObject) parser.parse(response.getBody().asString());
				int userId = Integer.parseInt(json.get("id").toString());
				user.setUserId(userId);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@BeforeClass(dependsOnMethods={"createUser"})
	public void createPost(){
		logger.info("creating post...");
		User user = users.get(0);
		int userId = user.getUserId();
		String title = RestUtils.postTitle();
		String body = RestUtils.postBody();
		posts.add(new Post(userId,title,body));
		
		for(Post post:posts) {
			RestAssured.baseURI = "https://5e9fa91311b078001679ca03.mockapi.io/dana";
			RequestSpecification httpRequest = RestAssured.given();
			JSONObject RequestParams = new JSONObject();

			RequestParams.put("userId", userId);
			RequestParams.put("title", post.getTitle());
			RequestParams.put("body", post.getBody());
		
			
			httpRequest.header("Content-Type", "application/json");

			httpRequest.body(RequestParams.toJSONString());
			

			Response response=httpRequest.request(Method.POST, "/post");
			
			try {
				JSONParser parser = new JSONParser(); 
				JSONObject json = (JSONObject) parser.parse(response.getBody().asString());
				int postId = Integer.parseInt(json.get("id").toString());
				post.setPostId(postId);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@DataProvider(name="testData")
	Object[][] dataProviderMethod() throws IOException {
		for(User user:users) {
			Post post = posts.get(0);
			int postId = post.getPostId();
			String name = user.getFirstName()+" "+user.getLastName();
			String email = user.getEmail();
			String body = RestUtils.commentBody();
			
			comments.add(new Comment(postId,name,email,body));
		}
		int row = comments.size();
		int column = 4;
		Object[][] dataTest=new Object[row][column+1];
		int i=0;
		for(Comment comment : comments) {
			dataTest[i][0] = i;
			dataTest[i][1] = comment.getPostId();
			dataTest[i][2] = comment.getName();
			dataTest[i][3]=comment.getEmail();
			dataTest[i][4]=comment.getBody();
			i++;
		}
		
		return dataTest;
	}
	
	@Test(dataProvider="testData")
	public void createComment(int index,int postId, String name, String email, String body) {
		
		
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
			

			Response response=httpRequest.request(Method.POST, "/comment");
			try {
				JSONParser parser = new JSONParser(); 
				JSONObject json = (JSONObject) parser.parse(response.getBody().asString());
				int commentId = Integer.parseInt(json.get("id").toString());
				Comment comment = comments.get(index);
				comment.setCommentId(commentId);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			logger.info("**************** Checking Status Code *****************");
			int statusCode = response.getStatusCode();
			logger.info("Status code => " + statusCode);
			Assert.assertEquals(statusCode, 201);
			logger.info("");
			
			logger.info("*************** Checking Response Body ****************");
			String responseBody = response.getBody().asString();
			logger.info("Response body => " + responseBody);
			logger.info("");
			
			Assert.assertEquals(responseBody.contains("id"), true);
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
			Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
			logger.info("");
			
		
	}


	@AfterClass
	void tearDown() {
		logger.info("======== TC_001_Create_Comment Finished ========");
	}

}
