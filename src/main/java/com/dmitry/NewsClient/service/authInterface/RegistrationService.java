package com.dmitry.NewsClient.service.authInterface;

import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;

public interface RegistrationService {
    LoginUserDto registerUser(RegisterUserDto userDto);
}
