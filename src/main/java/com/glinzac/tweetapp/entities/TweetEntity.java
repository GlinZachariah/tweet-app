package com.glinzac.tweetapp.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.glinzac.tweetapp.models.Reply;

@Document
public class TweetEntity {
	
	@Id
	private long tweetId;
	private String userId;
	private String tweetMessage;
	private String timeStamp;
	private long likeCounter;
	
	private List<Reply> replies;
	private List<String>userIdLiked;
	
	public TweetEntity() {
		
	}
	
	public TweetEntity(long tweetId) {
		super();
		this.tweetId = tweetId;
	}

	public long getTweetId() {
		return tweetId;
	}

	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTweetMessage() {
		return tweetMessage;
	}

	public void setTweetMessage(String tweetMessage) {
		this.tweetMessage = tweetMessage;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public long getLikeCounter() {
		return likeCounter;
	}

	public void setLikeCounter(long likeCounter) {
		this.likeCounter = likeCounter;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public List<String> getUserIdLiked() {
		return userIdLiked;
	}

	public void setUserIdLiked(List<String> userIdLiked) {
		this.userIdLiked = userIdLiked;
	}
	
	
}
