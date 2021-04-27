package com.tweetapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tweetapp.controller.MainController;
import com.tweetapp.entities.UserCount;
import com.tweetapp.entities.UserEntity;
import com.tweetapp.models.LoginForm;
import com.tweetapp.models.RegisterForm;
import com.tweetapp.models.Response;
import com.tweetapp.repository.UserCountRepository;
import com.tweetapp.repository.UserRepository;

@Service
public class MainService {
	
	private static final Logger LOGGER =LogManager.getLogger(MainService.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserCountRepository userCount;

	public ResponseEntity<Response> registerNewUser(RegisterForm registerForm) {
		String userId =registerForm.getUserId();
		LOGGER.info("Registring new userName with userId: {}", userId);
		
		try {
			UserCount userCnt = null;
			userCnt =userCount.findById(100L).orElse(new UserCount(100L));
			boolean userExists = userRepo.existsById(userId);
			if (userExists) {
				Response response = new Response(409L);
				LOGGER.error("User already exists with userId : {}!!", userId);
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			Long id = userCnt.getNewUserId();
			UserEntity newUser = new UserEntity();
			newUser.setId(id);
			newUser.setFirstName(registerForm.getFirstName());
			newUser.setLastName(registerForm.getLastName());
			newUser.setEmail(registerForm.getEmail());
			newUser.setContactNumber(registerForm.getContactNumber());
			newUser.setUserId(userId);
			newUser.setPassword(registerForm.getPassword());
			this.userRepo.save(newUser);
			this.userCount.save(userCnt);
			LOGGER.info("New user created with id :{} by username : {}", id, userId);
		}catch(Exception e) {
			LOGGER.error("Error while fetching data :{}",e);
			Response response = new Response(417L);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		Response response = new Response(201L);
		return new  ResponseEntity<>(response,HttpStatus.CREATED);
	}

	public ResponseEntity<Response> loginUser(LoginForm loginForm) {
		boolean userExists = userRepo.existsById(loginForm.getUserId());
		if (userExists) {
			UserEntity user =userRepo.findById(loginForm.getUserId()).get();
			if(user.getPassword().equals(loginForm.getPassword())) {
				Response response = new Response(200L);
				return new ResponseEntity<>(response,HttpStatus.OK);
			}else {
				Response response = new Response(409L);
				LOGGER.error("Invalid password: {}", loginForm.getUserId());
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
		}
		Response response = new Response(400L);
		LOGGER.error("Invalid user : {}", loginForm.getUserId());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	public ResponseEntity<Response> resetUser(LoginForm forgotForm) {
		boolean userExists = userRepo.existsById(forgotForm.getUserId());
		if (userExists) {
			UserEntity user =userRepo.findById(forgotForm.getUserId()).get();
			user.setPassword(forgotForm.getPassword());
			try {
				userRepo.save(user);
			} catch(Exception e) {
				LOGGER.error("Error while fetching data :{}",e);
				Response response = new Response(417L);
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
		}
		Response response = new Response(200L);
		LOGGER.info("Reset Password completed for User : {}", forgotForm.getUserId());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
