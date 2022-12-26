package com.dmitry.NewsClient.mapstruct;

import com.dmitry.NewsClient.dto.PublicUserView;
import com.dmitry.NewsClient.dto.PutUserDtoResponse;
import com.dmitry.NewsClient.entity.UserEntity;
import org.mapstruct.Mapper;

import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserViewMapper {
    PublicUserView userView(UserEntity entity);
    PutUserDtoResponse userDtoResp(UserEntity entity);

}
