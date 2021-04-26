package com.tweetapp.models;



public class Reply {
	private String userId;
	
	private String timeStamp;
	
	private String comment;
	
	public Reply(String userId,String comment,String timeStamp) {
		this.userId = userId;
		this.timeStamp = timeStamp;
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
