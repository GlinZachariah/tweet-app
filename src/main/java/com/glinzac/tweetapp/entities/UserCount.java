package com.glinzac.tweetapp.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserCount {
	
	@Id
	private long id;
	
	private long userCount;
	
	
	
	public UserCount(long id) {
		super();
		this.id = id;
	}

	public long getUserCount() {
		return userCount;
	}

	public long getNewUserId() {
		this.userCount = this.userCount+1;
		return this.userCount;
	}
	
	

}
