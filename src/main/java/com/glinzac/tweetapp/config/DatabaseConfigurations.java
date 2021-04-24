package com.glinzac.tweetapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.glinzac.tweetapp.repository")
public class DatabaseConfigurations extends AbstractMongoClientConfiguration {
	
	@Value("${spring.data.mongodb.uri}")
	private String connectionURI;
	
	@Value("${spring.data.mongodb.authentication-database}")
	private String databaseName;
	
	
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(connectionURI);
        
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
    
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), databaseName);
    }

	@Override
	protected String getDatabaseName() {
		return this.databaseName;
	}

}
