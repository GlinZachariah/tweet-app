package com.tweetapp.models;


import java.util.List;

public class Tweet {

	private String userId;
	private String tweetMessage;
	private String timeStamp;
	private long likeCounter;
	private List<String> tags;
	
	
	public Tweet() {
		
	}
	
	
	public Tweet(String userId,String tweetMessage,String timeStamp) {
		this.userId = userId;
		this.tweetMessage = tweetMessage;
		this.timeStamp = timeStamp;
	}

	public String getUserId() {
		return userId;
	}

	public String getTweetMessage() {
		return tweetMessage;
	}



	public String getTimeStamp() {
		return timeStamp;
	}


	public List<String> getTags() {
		return tags;
	}
	
		
}
