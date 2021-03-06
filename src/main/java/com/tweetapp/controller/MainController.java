package com.tweetapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.models.LoginForm;
import com.tweetapp.models.RegisterForm;
import com.tweetapp.models.Response;
import com.tweetapp.service.MainService;

@RestController
@RequestMapping(path = "/api/v1.0/tweets")
public class MainController {
	
	private static final Logger LOGGER =LogManager.getLogger(MainController.class);
	

	
	@Autowired
	MainService mainService;
	
//	POST /api/v1.0/tweets/register Register as new user
	
	@PostMapping(path= "/register")
	public ResponseEntity<Response> regsiterNewUser(@RequestBody RegisterForm registerForm) {
		LOGGER.info("POST Request - Registering New User");
		ResponseEntity<Response> response =  this.mainService.registerNewUser(registerForm);
		return response;
	}
	
//	GET /api/v1.0/tweets/login Login
	
	@PostMapping(path= "/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginForm loginForm) {
		LOGGER.info("POST Request - Logging in User :{}",loginForm.getUserId());
		ResponseEntity<Response> response = this.mainService.loginUser(loginForm);
		return response;
	}

//	GET /api/v1.0/tweets/<username>/forgot Forgot password
	@PostMapping(path= "/{username}/forgot")
	public  ResponseEntity<Response> forgotPassword(@PathVariable String username,@RequestBody LoginForm forgotForm) {
		LOGGER.info("POST Request - Resetting Password for User : {}", username);
		ResponseEntity<Response> response = this.mainService.resetUser(forgotForm);
		return response;
	}



}
