package com.dmitry.NewsClient.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomResponseEntityException {

    private boolean success = true;
    private int statusCode;
    private int[] codes;
    private String timestamp;

}
