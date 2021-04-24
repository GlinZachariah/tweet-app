package com.glinzac.tweetapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.glinzac.tweetapp.controller.MainController;
import com.glinzac.tweetapp.entities.UserCount;
import com.glinzac.tweetapp.entities.UserEntity;
import com.glinzac.tweetapp.models.LoginForm;
import com.glinzac.tweetapp.models.RegisterForm;
import com.glinzac.tweetapp.repository.UserCountRepository;
import com.glinzac.tweetapp.repository.UserRepository;

@Service
public class MainService {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(MainService.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserCountRepository userCount;

	public ResponseEntity<Object> registerNewUser(RegisterForm registerForm) {
		String userId =registerForm.getUserId();
		LOGGER.info("Registring new userName with userId: {}", userId);
		
		try {
			UserCount userCnt = null;
			userCnt =userCount.findById(100L).orElse(new UserCount(100L));
			boolean userExists = userRepo.existsById(userId);
			if (userExists) {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
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
		}catch(Exception e) {
			LOGGER.error("Error while fetching data :{}",e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		return new  ResponseEntity<>(HttpStatus.CREATED);
	}

	public ResponseEntity<Object> loginUser(LoginForm loginForm) {
		boolean userExists = userRepo.existsById(loginForm.getUserId());
		if (userExists) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}else {
			UserEntity user =userRepo.findById(loginForm.getUserId()).get();
			if(user.getPassword().equals(loginForm.getPassword())) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}

	public ResponseEntity<Object> resetUser(LoginForm forgotForm) {
		boolean userExists = userRepo.existsById(forgotForm.getUserId());
		if (userExists) {
			UserEntity user =userRepo.findById(forgotForm.getUserId()).get();
			user.setPassword(forgotForm.getPassword());
			try {
				userRepo.save(user);
			} catch(Exception e) {
				LOGGER.error("Error while fetching data :{}",e);
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
		}
		return new ResponseEntity<>("Password Reset Success",HttpStatus.OK);
	}

}
