package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages = " com.tweetapp")
public class CustomTweetappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomTweetappApplication.class, args);
	}

}
