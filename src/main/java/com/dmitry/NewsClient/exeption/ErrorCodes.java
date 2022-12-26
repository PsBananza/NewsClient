package com.dmitry.NewsClient.exeption;

import lombok.Getter;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ErrorCodes {

    UNKNOWN(0, "unknown"),
    USERNAME_SIZE_NOT_VALID(1, ValidationConstants.USERNAME_SIZE_NOT_VALID),
    ROLE_SIZE_NOT_VALID(2, ValidationConstants.ROLE_SIZE_NOT_VALID),
    EMAIL_SIZE_NOT_VALID(3, ValidationConstants.EMAIL_SIZE_NOT_VALID),
    MUST_NOT_BE_NULL(4, "Must not be null"),
    USER_NOT_FOUND(5, "Could not find user"),
    TOKEN_NOT_PROVIDED(6, "JWT token not provided"),
    UNAUTHORISED(7, "Unauthorised"),
    USER_EMAIL_NOT_NULL(8, ValidationConstants.USER_EMAIL_NOT_NULL),
    USER_PASSWORD_NULL(9, "User password must be null"),
    USER_ROLE_NOT_NULL(10, ValidationConstants.ROLE_NOT_NULL),

    USER_EMAIL_NOT_VALID(17, ValidationConstants.USER_EMAIL_NOT_VALID),

    USER_AVATAR_NOT_NULL(24, ValidationConstants.USER_AVATAR_NOT_NULL),
    PASSWORD_NOT_VALID(25, ValidationConstants.PASSWORD_NOT_VALID),
    PASSWORD_NOT_NULL(26, ValidationConstants.PASSWORD_NOT_NULL),
    USER_ALREADY_EXISTS(30, "User already exists"),

    USER_NAME_HAS_TO_BE_PRESENT(43, ValidationConstants.USER_NAME_HAS_TO_BE_PRESENT),
    TAGS_NOT_VALID(44, ValidationConstants.TAGS_NOT_VALID),
    USER_WITH_THIS_EMAIL_ALREADY_EXIST(46, "User with this email already exist"),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION(47, "Http request not valid"),

    NEWS_DESCRIPTION_SIZE(11, ValidationConstants.NEWS_DESCRIPTION_SIZE),
    NEWS_DESCRIPTION_NOT_NULL(12, ValidationConstants.NEWS_DESCRIPTION_NOT_NULL),
    NEWS_TITLE_SIZE(13, ValidationConstants.NEWS_TITLE_SIZE),
    NEWS_TITLE_NOT_NULL(14, ValidationConstants.NEWS_TITLE_NOT_NULL),
    NEWS_NOT_FOUND(27, ValidationConstants.NEWS_NOT_FOUND),
    NEWS_IMAGE_HAS_TO_BE_PRESENT(45, ValidationConstants.NEWS_IMAGE_HAS_TO_BE_PRESENT),

    PARAM_PAGE_NOT_NULL(15, ValidationConstants.PARAM_PAGE_NOT_NULL),
    PARAM_PER_PAGE_NOT_NULL(16, ValidationConstants.PARAM_PER_PAGE_NOT_NULL),
    CODE_NOT_NULL(20, ValidationConstants.CODE_NOT_NULL);

    private final int code;
    private final String message;
    public static Map<String, Integer> errorCodes = Stream
            .of(values())
            .collect(Collectors
                    .toMap(message -> message.message, code -> code.code));

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
