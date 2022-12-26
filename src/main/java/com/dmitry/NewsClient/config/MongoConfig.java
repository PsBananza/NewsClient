package com.dmitry.NewsClient.config;

import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.Tag;
import com.dmitry.NewsClient.entity.UserEntity;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.jsr310.Jsr310CodecProvider;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "banana";
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://127.0.0.1:27017/banana");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .codecRegistry(CodecRegistries.fromProviders(PojoCodecProvider.builder()
//                        .register(UserEntity.class, NewsEntity.class, Tag.class)
//                        .build(), new Jsr310CodecProvider(), new ValueCodecProvider()))
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @NonNull
    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("com/dmitry/NewsClient/mongo");
    }
}
