package com.glinzac.tweetapp.models;

public class TweetReponse extends Tweet {
	
	private long tweetId;
	public TweetReponse(String userId, String tweetMessage, String timeStamp,Long tweetId) {
		super(userId, tweetMessage, timeStamp);
		this.tweetId = tweetId;
		// TODO Auto-generated constructor stub
	}

}
