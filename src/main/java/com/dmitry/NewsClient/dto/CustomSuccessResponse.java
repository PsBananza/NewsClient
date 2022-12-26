package com.dmitry.NewsClient.dto;

import lombok.Data;

@Data
public class CustomSuccessResponse<T> {

    final T data;

    Integer statusCode = 0;
    Boolean success = true;

}
