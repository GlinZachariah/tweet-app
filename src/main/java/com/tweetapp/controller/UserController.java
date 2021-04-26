package com.tweetapp.controller;

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

import com.tweetapp.entities.TweetEntity;
import com.tweetapp.entities.UserEntity;
import com.tweetapp.models.Reply;
import com.tweetapp.models.Response;
import com.tweetapp.models.Tweet;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.service.UserService;



@RestController
@RequestMapping(path = "/api/v1.0/tweets")
public class UserController {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
//	GET /api/v1.0/tweets/all Get all tweets
	@GetMapping(path = "/all")
	public ResponseEntity<Response> getAllTweets() {
		return userService.getAllTweets();
	}
	

//	GET /api/v1.0/tweets/users/all Get all users
	@GetMapping(path = "/users/all")
	public ResponseEntity<Response> getAllUsers() {
		return userService.getAllUsers();
	}

//	GET /api/v/1.0/tweets/user/search/username* Search by username
	@GetMapping(path = "/user/search/{userId}")
	public ResponseEntity<Response> getUserByUserId(@PathVariable String userId) {
		return userService.getUserByUserId(userId);
	}

//	GET /api/v1.0/tweets/username Get all tweets of user
	@GetMapping(path = "/{userId}")
	public ResponseEntity<Response> getTweetsByUserName(@PathVariable String userId) {
		return userService.getTweetsForUser(userId);
	}

//	POST /api/v1.0/tweets/<username>/add Post new tweet
	@PostMapping(path = "/{userId}/add")
	public ResponseEntity<Response> addNewPostByUserName(@PathVariable String userId,@RequestBody Tweet tweet) {
		return userService.addNewTweet(userId,tweet);
	}

//	PUT /api/v1.0/tweets/<username>/update/<id> Update tweet
	@PutMapping(path = "/{userId}/update/{id}")
	public ResponseEntity<Response> updateOldTweet(@PathVariable String userId,@PathVariable Long id,@RequestBody Tweet tweet) {
		return userService.updateTweet(userId,id,tweet);
	}

//	DELETE /api/v1.0/tweets/<username>/delete/<id> Delete tweet
	@DeleteMapping(path = "/{userId}/delete/{id}")
	public ResponseEntity<Response> deletePostByUserName(@PathVariable String userId,@PathVariable Long id) {
		return userService.deleteTweet(userId,id);
	}

//	PUT /api/v1.0/tweets/<username>/like/<id> Like tweet
	@PutMapping(path = "/{userId}/like/{id}")
	public ResponseEntity<Response> likePostByUserName(@PathVariable String userId,@PathVariable Long id) {
		return userService.likePostByUserId(userId,id);
	}

//	POST /api/v1.0/tweets/<username>/reply/<id> Reply to tweet
	@PutMapping(path = "/{userId}/reply/{id}")
	public ResponseEntity<Response> replyPostByUserName(@PathVariable String userId,@PathVariable Long id,@RequestBody Reply reply) {
		return userService.replyTweetByUserId(userId,id,reply);
	}
}
