package com.api.models;

public class Comment {
	private int commentId;
	private int postId;
	private String name;
	private String email;
	private String body;
	
	public Comment(int postId, String name, String email, String body) {
		super();
		this.postId=postId;
		this.name=name;
		this.email=email;
		this.body=body;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
