package com.glinzac.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.glinzac.tweetapp.entities.TweetEntity;
import java.lang.String;
import java.util.List;

@Repository
public interface TweetRepository extends MongoRepository<TweetEntity, Long> {

	List<TweetEntity> findByUserId(String userid);
}
