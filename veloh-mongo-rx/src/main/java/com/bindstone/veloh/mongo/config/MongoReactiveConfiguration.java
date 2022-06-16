package com.bindstone.veloh.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@EnableMongoRepositories(basePackages = "com.bindstone.veloh.mongo.repository")
public class MongoReactiveConfiguration extends AbstractReactiveMongoConfiguration {

    @Bean
    public MongoClient mongo() throws Exception {
        final ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/veloh");
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getDatabaseName() {
        return "reactive";
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        try {
            var client = mongo();
            return new ReactiveMongoTemplate(client, "veloh");
        } catch (Exception e) {
            throw new RuntimeException("Unable to create Mongo Template", e);
        }
    }
}
