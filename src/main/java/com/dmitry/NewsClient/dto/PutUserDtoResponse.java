package com.dmitry.NewsClient.dto;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class PutUserDtoResponse {

    private String avatar;
    private String email;
    UUID id;
    private String name;
    private String role;

}
