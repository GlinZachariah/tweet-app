package com.glinzac.tweetapp.models;


import java.util.List;

public class Tweet {

	private String userId;
	private String tweetMessage;
	private String timeStamp;
	private long likeCounter;
	
	private List<Reply> replies;
	
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

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public String getTimeStamp() {
		return timeStamp;
	}
		
}
