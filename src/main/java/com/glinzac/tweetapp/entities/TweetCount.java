package com.glinzac.tweetapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TweetCount {

	@Id
	private long id;
	
	private long tweetCount;
	
	public TweetCount(long id) {
		super();
		this.id = id;
	}

	public long getTweetCount() {
		return tweetCount;
	}

	public long getNewTweetId() {
		this.tweetCount = this.tweetCount+1;
		return this.tweetCount;
	}
	
	
}
