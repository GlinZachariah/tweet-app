package com.tweetapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tweetapp.entities.TweetCount;
import com.tweetapp.entities.TweetEntity;
import com.tweetapp.entities.UserCount;
import com.tweetapp.entities.UserEntity;
import com.tweetapp.models.Reply;
import com.tweetapp.models.Response;
import com.tweetapp.models.Tweet;
import com.tweetapp.repository.TweetCountRepository;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TweetRepository tweetRepo;
	
	@Autowired
	TweetCountRepository tweetCountRepo;
	
	private static final Logger LOGGER =LoggerFactory.getLogger(UserService.class);

	public ResponseEntity<Response> getAllTweets() {
		Response result = new Response(200L);
		result.setMessage(tweetRepo.findAll());
		ResponseEntity<Response> response = new ResponseEntity<>(result,HttpStatus.OK);
		LOGGER.info("Fetched list of all the tweets successfully!");
		return response;
	}

	public ResponseEntity<Response> getAllUsers() {
		List<String> usersList = userRepo.findAll()
					.stream()
					.map(UserEntity::getUserId)
					.collect(Collectors.toList());
		Response result = new Response(200L);
		result.setMessage(usersList);
		LOGGER.info("Fetched list of all the users successfully!");
		ResponseEntity<Response> response = new ResponseEntity<>(result,HttpStatus.OK);
		return response; 
	}

	public ResponseEntity<Response> getUserByUserId(String userId) {
		boolean doesUserExists = userRepo.existsById(userId);
		Response result = new Response(404L);
		if(doesUserExists) {
			UserEntity user = userRepo.findById(userId).get();
			result.setCode(200L);
			result.setMessage(user);
			ResponseEntity<Response> response = new ResponseEntity<>(result,HttpStatus.OK);
			LOGGER.info("Fetched user details for User : {}",userId);
			return response;
		}
		LOGGER.info("Invalid User : {}", userId);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	public  ResponseEntity<Response> getTweetsForUser(String userId) {
		boolean doesUserExists = userRepo.existsById(userId);
		Response result = new Response(404L);
		if(doesUserExists) {
			List<TweetEntity> tweets = tweetRepo.findByUserId(userId);
			result.setCode(200L);
			result.setMessage(tweets);
			ResponseEntity<Response> response = new ResponseEntity<>(result,HttpStatus.OK);
			LOGGER.info("Fetched tweets for User : {} successfully!",userId);
			return response;
		}
		LOGGER.error("Error while fetching details for Unknown User : {} ", userId);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	public ResponseEntity<Response> addNewTweet(String userId, Tweet tweet) {
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
		userTweet.setTags(tweet.getTags());
		tweetRepo.save(userTweet);
		tweetCountRepo.save(tweetCnt);
		Response response = new Response(201L);
		LOGGER.info("Added New Tweet : {} by User : {} successfully!",tweet,userId);
		return  new  ResponseEntity<>(response,HttpStatus.CREATED);
	}

	public ResponseEntity<Response> updateTweet(String userId, Long id, Tweet tweet) {
		TweetEntity currentTweet= tweetRepo.findById(id).get();
		if(currentTweet.getUserId().equals(userId)) {
			currentTweet.setTweetMessage(tweet.getTweetMessage());
			currentTweet.setTags(tweet.getTags());
		}
		tweetRepo.save(currentTweet);
		Response response = new Response(200L);
		LOGGER.info("Updated the tweet with id : {} by User : {} successfully!", id,userId);
		return  new  ResponseEntity<>(response,HttpStatus.OK);
	}

	public  ResponseEntity<Response> deleteTweet(String userId, Long id) {
		TweetEntity currentTweet= tweetRepo.findById(id).get();
		Response response = new Response(404L);
		if(currentTweet.getUserId().equals(userId)) {
			tweetRepo.deleteById(id);
			response.setCode(200L);
			LOGGER.info("Tweet Deleted with id : {} by  User : {} successfully!", id,userId);
			return  new  ResponseEntity<>(response,HttpStatus.OK);
		}
		LOGGER.error("User Not Authorized to delete the Tweet!",userId);
		return new  ResponseEntity<>(response,HttpStatus.OK);
	}

	public  ResponseEntity<Response> likePostByUserId(String userId, Long id) {
		TweetEntity currentTweet= tweetRepo.findById(id).get();
		List<String> userIdsLiked =currentTweet.getUserIdLiked();
		if(userIdsLiked == null) {
			userIdsLiked = new ArrayList<>();
		}
		if(userIdsLiked.contains(userId)) {
			userIdsLiked.remove(userId);
			currentTweet.setLikeCounter(currentTweet.getLikeCounter()-1);
			LOGGER.info("UnLiking the tweet : {} by User : {}", id, userId);
		}else {
			userIdsLiked.add(userId);
			currentTweet.setUserIdLiked(userIdsLiked);
			currentTweet.setLikeCounter(currentTweet.getLikeCounter()+1);
			LOGGER.info("Liking the tweet : {} by User : {}", id, userId);
		}
		tweetRepo.save(currentTweet);
		Response response = new Response(200L);
		LOGGER.info("Tweet : {} likes updated to : {} by Users : {}", id, currentTweet.getUserIdLiked().size(), currentTweet.getUserIdLiked());
		return  new  ResponseEntity<>(response,HttpStatus.OK);
	}

	public  ResponseEntity<Response> replyTweetByUserId(String userId, Long id, Reply reply) {
		TweetEntity currentTweet= tweetRepo.findById(id).get();
		List<Reply> repliesList = currentTweet.getReplies();
		if(repliesList == null) {
			repliesList = new ArrayList<>();
		}
		repliesList.add(reply);
		currentTweet.setReplies(repliesList);
		tweetRepo.save(currentTweet);
		Response response = new Response(200L);
		LOGGER.info("Replying to the tweet : {} by User : {}", id, userId);
		return new  ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	

}
