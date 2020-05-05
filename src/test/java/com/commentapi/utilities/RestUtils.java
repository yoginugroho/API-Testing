package com.commentapi.utilities;


import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	public static String firstName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return("Vincent "+generatedString);
	}
	public static String LastName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return("Rompies "+generatedString);
	}
	public static String email() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return("vincentrompies"+generatedString+"@gmail.com");
	}
	public static String username() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return("vincent.rompies"+generatedString);
	}
	public static String password() {
		String generatedString = RandomStringUtils.randomAlphanumeric(6);
		return generatedString;
	}
	public static String postTitle() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return "cara membuat title "+generatedString;
	}
	
	public static String postBody() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return "Body "+generatedString;
	}
	
	public static String commentBody() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return "Body "+generatedString;
	}
	
}
