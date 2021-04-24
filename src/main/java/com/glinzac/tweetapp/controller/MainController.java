package com.glinzac.tweetapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.glinzac.tweetapp.models.LoginForm;
import com.glinzac.tweetapp.models.RegisterForm;
import com.glinzac.tweetapp.service.MainService;

@RestController
@RequestMapping(value = "/api/v1.0/tweets")
public class MainController {
	

	
	@Autowired
	MainService mainService;
	
//	POST /api/v1.0/tweets/register Register as new user
	
	@PostMapping(value= "/register")
	public ResponseEntity<Object> regsiterNewUser(@RequestBody RegisterForm registerForm) {
		ResponseEntity<Object> response =  this.mainService.registerNewUser(registerForm);
		return response;
	}
	
//	GET /api/v1.0/tweets/login Login
	
	@PostMapping(value= "/login")
	public ResponseEntity<Object> loginUser(@RequestBody LoginForm loginForm) {
		ResponseEntity<Object> response = this.mainService.loginUser(loginForm);
		return response;
	}

//	GET /api/v1.0/tweets/<username>/forgot Forgot password
	@PostMapping(value= "/{username}/forgot")
	public  ResponseEntity<Object> forgotPassword(@PathVariable String username,@RequestBody LoginForm forgotForm) {
		ResponseEntity<Object> response = this.mainService.resetUser(forgotForm);
		return response;
	}



}
