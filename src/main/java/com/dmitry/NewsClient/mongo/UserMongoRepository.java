package com.dmitry.NewsClient.mongo;

import com.dmitry.NewsClient.entity.UserEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface UserMongoRepository extends MongoRepository<UserEntity, Long> {

}
