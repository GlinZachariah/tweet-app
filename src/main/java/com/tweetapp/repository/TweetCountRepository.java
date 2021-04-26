package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.entities.TweetCount;
import com.tweetapp.entities.TweetEntity;

@Repository
public interface TweetCountRepository extends MongoRepository<TweetCount, Long> {

}
