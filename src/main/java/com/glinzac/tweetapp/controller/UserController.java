package com.glinzac.tweetapp.controller;

import java.util.List;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glinzac.tweetapp.entities.TweetEntity;
import com.glinzac.tweetapp.entities.UserEntity;
import com.glinzac.tweetapp.models.Reply;
import com.glinzac.tweetapp.models.Tweet;
import com.glinzac.tweetapp.repository.TweetRepository;
import com.glinzac.tweetapp.service.UserService;



@RestController
@RequestMapping(value = "/api/v1.0/tweets")
public class UserController {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
//	GET /api/v1.0/tweets/all Get all tweets
	@GetMapping(value = "/all")
	public List<TweetEntity> getAllTweets() {
		return userService.getAllTweets();
	}
	

//	GET /api/v1.0/tweets/users/all Get all users
	@GetMapping(value = "/users/all")
	public List<String> getAllUsers() {
		return userService.getAllUsers();
	}

//	GET /api/v/1.0/tweets/user/search/username* Search by username
	@GetMapping(value = "/user/search/{userId}")
	public UserEntity getUserByUserId(@PathVariable String userId) {
		return userService.getUserByUserId(userId);
	}

//	GET /api/v1.0/tweets/username Get all tweets of user
	@GetMapping(value = "/{userId}")
	public List<TweetEntity> getTweetsByUserName(@PathVariable String userId) {
		return userService.getTweetsForUser(userId);
	}

//	POST /api/v1.0/tweets/<username>/add Post new tweet
	@PostMapping(value = "/{userId}/add")
	public ResponseEntity<String> addNewPostByUserName(@PathVariable String userId,@RequestBody Tweet tweet) {
		return userService.addNewTweet(userId,tweet);
	}

//	PUT /api/v1.0/tweets/<username>/update/<id> Update tweet
	@PutMapping(value = "/{userId}/update/{id}")
	public ResponseEntity<String> updateOldTweet(@PathVariable String userId,@PathVariable Long id,@RequestBody Tweet tweet) {
		return userService.updateTweet(userId,id,tweet);
	}

//	DELETE /api/v1.0/tweets/<username>/delete/<id> Delete tweet
	@DeleteMapping(value = "/{userId}/delete/{id}")
	public ResponseEntity<String> deletePostByUserName(@PathVariable String userId,@PathVariable Long id,@RequestBody Tweet tweet) {
		return userService.deleteTweet(userId,id,tweet);
	}

//	PUT /api/v1.0/tweets/<username>/like/<id> Like tweet
	@PutMapping(value = "/{userId}/like/{id}")
	public ResponseEntity<String> likePostByUserName(@PathVariable String userId,@PathVariable Long id) {
		return userService.likePostByUserId(userId,id);
	}

//	POST /api/v1.0/tweets/<username>/reply/<id> Reply to tweet
	@PutMapping(value = "/{userId}/reply/{id}")
	public Object replyPostByUserName(@PathVariable String userId,@PathVariable Long id,@RequestBody Reply reply) {
		return userService.replyTweetByUserId(userId,id,reply);
	}
}
