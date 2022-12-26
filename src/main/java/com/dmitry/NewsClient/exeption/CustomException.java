package com.dmitry.NewsClient.exeption;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final int code;

    public CustomException(ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.code = errorCodes.getCode();
    }

}
