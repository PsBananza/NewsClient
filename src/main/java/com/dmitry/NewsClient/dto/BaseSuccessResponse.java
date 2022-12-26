package com.dmitry.NewsClient.dto;

import lombok.Data;

@Data
public class BaseSuccessResponse {

    Integer statusCode = 0;
    Boolean success = true;

}
