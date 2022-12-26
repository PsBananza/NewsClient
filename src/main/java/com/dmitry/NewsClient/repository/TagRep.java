package com.dmitry.NewsClient.repository;

import com.dmitry.NewsClient.entity.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRep extends JpaRepository<Tag, Long> {

    @Query(value = "SELECT t FROM tags t WHERE t.title = :title", nativeQuery = true)
    Tag findByTitle(@Param("title") String title);

}
