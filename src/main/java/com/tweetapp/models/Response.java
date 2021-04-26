package com.tweetapp.models;

public class Response {
	private Long code;
	private String status;
	private Object message;
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Response(Long code) {
		super();
		this.code = code;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	
}
