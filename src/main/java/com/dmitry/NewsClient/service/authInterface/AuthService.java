package com.dmitry.NewsClient.service.authInterface;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.LoginUserDto;

public interface AuthService {
    LoginUserDto authUser(AuthDto authDto);
}
