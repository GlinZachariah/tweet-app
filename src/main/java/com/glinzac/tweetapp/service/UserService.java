package com.glinzac.tweetapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.glinzac.tweetapp.entities.TweetCount;
import com.glinzac.tweetapp.entities.TweetEntity;
import com.glinzac.tweetapp.entities.UserCount;
import com.glinzac.tweetapp.entities.UserEntity;
import com.glinzac.tweetapp.models.Reply;
import com.glinzac.tweetapp.models.Tweet;
import com.glinzac.tweetapp.repository.TweetCountRepository;
import com.glinzac.tweetapp.repository.TweetRepository;
import com.glinzac.tweetapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TweetRepository tweetRepo;
	
	@Autowired
	TweetCountRepository tweetCountRepo;

	public List<TweetEntity> getAllTweets() {
		return tweetRepo.findAll();
	}

	public List<String> getAllUsers() {
		List<String> usersList = userRepo.findAll()
					.stream()
					.map(UserEntity::getUserId)
					.collect(Collectors.toList());
		return usersList; 
	}

	public UserEntity getUserByUserId(String userId) {
		boolean doesUserExists = userRepo.existsById(userId);
		if(doesUserExists) {
			UserEntity user = userRepo.findById(userId).get();
			return user;
		}
		return null;
	}

	public List<TweetEntity> getTweetsForUser(String userId) {
		boolean doesUserExists = userRepo.existsById(userId);
		if(doesUserExists) {
			List<TweetEntity> tweets = tweetRepo.findByUserId(userId);
			return tweets;
		}
		return null;
	}

	public ResponseEntity<String> addNewTweet(String userId, Tweet tweet) {
		TweetCount tweetCnt = tweetCountRepo.findById(100L).orElse(new TweetCount(100L));
		List<Reply> replies =new ArrayList();
		List<String> userIdList =new ArrayList();
		TweetEntity userTweet = new TweetEntity(tweetCnt.getNewTweetId());
		userTweet.setUserId(userId);
		userTweet.setLikeCounter(0);
		userTweet.setReplies(replies);
		userTweet.setTimeStamp(tweet.getTimeStamp());
		userTweet.setTweetMessage(tweet.getTweetMessage());
		userTweet.setUserIdLiked(userIdList);
		tweetRepo.save(userTweet);
		tweetCountRepo.save(tweetCnt);
		return  new  ResponseEntity<>(HttpStatus.CREATED);
	}

	public ResponseEntity<String> updateTweet(String userId, Long id, Tweet tweet) {
		TweetEntity currentTweet= tweetRepo.findById(id).get();
		if(currentTweet.getUserId().equals(userId)) {
			currentTweet.setTweetMessage(tweet.getTweetMessage());
		}
		tweetRepo.save(currentTweet);
		return  new  ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<String> deleteTweet(String userId, Long id, Tweet tweet) {
		TweetEntity currentTweet= tweetRepo.findById(id).get();
		if(currentTweet.getUserId().equals(userId)) {
			tweetRepo.deleteById(id);
			return  new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> likePostByUserId(String userId, Long id) {
		TweetEntity currentTweet= tweetRepo.findById(id).get();
		List<String> userIdsLiked =currentTweet.getUserIdLiked();
		if(userIdsLiked == null) {
			userIdsLiked = new ArrayList<>();
		}
		if(userIdsLiked.contains(userId)) {
			userIdsLiked.remove(userId);
			currentTweet.setLikeCounter(currentTweet.getLikeCounter()-1);
		}else {
			userIdsLiked.add(userId);
			currentTweet.setUserIdLiked(userIdsLiked);
			currentTweet.setLikeCounter(currentTweet.getLikeCounter()+1);
		}
		tweetRepo.save(currentTweet);
		
		return  new  ResponseEntity<>(HttpStatus.OK);
	}

	public Object replyTweetByUserId(String userId, Long id, Reply reply) {
		TweetEntity currentTweet= tweetRepo.findById(id).get();
		List<Reply> repliesList = currentTweet.getReplies();
		if(repliesList == null) {
			repliesList = new ArrayList<>();
		}
		repliesList.add(reply);
		currentTweet.setReplies(repliesList);
		tweetRepo.save(currentTweet);
		
		return null;
	}
	
	
	

}
