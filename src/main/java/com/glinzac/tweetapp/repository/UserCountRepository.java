package com.glinzac.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.glinzac.tweetapp.entities.UserCount;

@Repository
public interface UserCountRepository extends MongoRepository<UserCount, Long> {

}
