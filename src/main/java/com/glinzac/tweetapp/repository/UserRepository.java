package com.glinzac.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.glinzac.tweetapp.entities.UserEntity;


@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
	
}
