package com.commentapi.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.api.models.Comment;
import com.api.models.Post;
import com.api.models.User;



public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
    public String empID = "10";
    
    public static Logger logger;
    public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Comment> comments = new ArrayList<Comment>();
	public static ArrayList<Post> posts = new ArrayList<Post>();

    @BeforeClass
    public void setUp() {

        logger = Logger.getLogger("TestBase");
        PropertyConfigurator.configure("Log4j.properties");
        logger.setLevel(Level.INFO);
       
    }
}