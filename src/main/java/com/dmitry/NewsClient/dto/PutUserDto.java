package com.dmitry.NewsClient.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.dmitry.NewsClient.exeption.ValidationConstants;
import lombok.Data;

@Data
public class PutUserDto {
    private String avatar;
    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = ValidationConstants.USER_EMAIL_MIN_SIZE, max = ValidationConstants.USER_EMAIL_MAX_SIZE,
            message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;
    @Size(min = ValidationConstants.USER_NAME_MIN_SIZE, max = ValidationConstants.USER_NAME_MAX_SIZE,
            message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    private String name;
    private String role;
}
