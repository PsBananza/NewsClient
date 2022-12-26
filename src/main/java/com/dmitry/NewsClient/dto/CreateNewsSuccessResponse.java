package com.dmitry.NewsClient.dto;

import lombok.Data;

@Data
public class CreateNewsSuccessResponse {

    private String id;

    Integer statusCode = 0;
    Boolean success = true;

}
