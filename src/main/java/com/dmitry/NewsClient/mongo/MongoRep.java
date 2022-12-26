package com.dmitry.NewsClient.mongo;

import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.Tag;
import com.dmitry.NewsClient.entity.UserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.jsr310.Jsr310CodecProvider;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.mongodb.client.model.Filters.*;

@Repository
public class MongoRep {
//    private final MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
//    private final MongoDatabase database = mongoClient.getDatabase("banana")
//            .withCodecRegistry(CodecRegistries.fromProviders(PojoCodecProvider.builder()
//                    .register(UserEntity.class, NewsEntity.class, Tag.class)
//                    .build(), new Jsr310CodecProvider(), new ValueCodecProvider()));
//    public UserEntity mongoSaveLogin(UserEntity entity) {
//        var mongoCollection = database.getCollection("users", UserEntity.class);
//        mongoCollection.insertOne(entity);
//        return entity;
//    }
//
//    public NewsEntity mongoSaveNews(NewsEntity entity) {
//        var mongoCollection = database.getCollection("news", NewsEntity.class);
//        mongoCollection.insertOne(entity);
//        return entity;
//    }
//
//    public UserEntity mongoFindEmail(String email) {
//        var mongoCollection = database.getCollection("users", UserEntity.class);
//        return mongoCollection.find(eq("email", email)).first();
//    }
//
//    public UserEntity mongoByUserId(String id) {
//        var mongoCollection = database.getCollection("users", UserEntity.class);
//        return mongoCollection.find(eq("_id", new ObjectId(id))).first();
//    }


}
