package com.glinzac.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.glinzac.tweetapp.entities.TweetCount;
import com.glinzac.tweetapp.entities.TweetEntity;

@Repository
public interface TweetCountRepository extends MongoRepository<TweetCount, Long> {

}
