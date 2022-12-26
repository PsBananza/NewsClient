package com.dmitry.NewsClient.mongo;

import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface NewsMongoRep extends MongoRepository<NewsEntity, Long> {

    @Query
    Page<NewsEntity> findByUser(Pageable pageable, UserEntity user);

    @Query(value = "SELECT * FROM task t WHERE t.id = :id", nativeQuery = true)
    NewsEntity findById(@Param("id") long id);

}
