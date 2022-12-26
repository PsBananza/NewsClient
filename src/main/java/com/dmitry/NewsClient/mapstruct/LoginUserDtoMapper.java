package com.dmitry.NewsClient.mapstruct;

import com.dmitry.NewsClient.config.jwt.JwtProvider;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.entity.UserEntity;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoginUserDtoMapper {

    public LoginUserDto loginUserDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        LoginUserDto loginUserDto = new LoginUserDto();
        JwtProvider jwtProvider = new JwtProvider();

        loginUserDto.setAvatar(entity.getAvatar());
        loginUserDto.setEmail(entity.getEmail());
        loginUserDto.setId(entity.getId());
        loginUserDto.setName(entity.getName());
        loginUserDto.setRole(entity.getRole());
        loginUserDto.setToken(jwtProvider.generateToken(entity.getId()));

        return loginUserDto;
    }

}
