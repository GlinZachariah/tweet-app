package com.glinzac.tweetapp.models;

public class ReplyResponse extends Reply {

	private long replyId;
	public ReplyResponse(String userId, String comment, String timeStamp,Long replyId) {
		super(userId, comment, timeStamp);
		// TODO Auto-generated constructor stub
		this.replyId = replyId;
	}

}
