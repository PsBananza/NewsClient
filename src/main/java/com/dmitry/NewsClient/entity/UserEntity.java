package com.dmitry.NewsClient.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Entity
@Setter
@Getter
@Table(name = "users")
@Accessors(chain = true)
public class UserEntity {

    private String avatar;
    private String email;
    private String name;
    @Id
    @BsonProperty("_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String role;
    private String password;

    private int count;
    private int revenue;
    private boolean viewed;


}
