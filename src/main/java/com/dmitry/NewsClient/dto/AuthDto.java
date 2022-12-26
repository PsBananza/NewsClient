package com.dmitry.NewsClient.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dmitry.NewsClient.exeption.ValidationConstants;
import lombok.Data;


@Data
public class AuthDto {
    @NotBlank(message = ValidationConstants.USER_EMAIL_NOT_NULL)
    @Email(message = ValidationConstants.USER_EMAIL_NOT_VALID)
    @Size(min = ValidationConstants.USER_EMAIL_MIN_SIZE, max = ValidationConstants.USER_EMAIL_MAX_SIZE,
            message = ValidationConstants.USER_EMAIL_NOT_VALID)
    private String email;
    @NotNull
    @NotBlank
    @Size(min = ValidationConstants.PASSWORD_MIN_SIZE,
            max = ValidationConstants.PASSWORD_MAX_SIZE,
            message = ValidationConstants.PAGE_SIZE_NOT_VALID)
    private String password;
}
