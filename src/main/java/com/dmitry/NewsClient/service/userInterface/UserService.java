package com.dmitry.NewsClient.service.userInterface;

import java.util.List;
import java.util.UUID;

import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.PublicUserView;
import com.dmitry.NewsClient.dto.PutUserDto;
import com.dmitry.NewsClient.dto.PutUserDtoResponse;

public interface UserService {
    List<PublicUserView> getUserAll();
    PublicUserView getUserId(String id);
    PublicUserView getUserInfo(String id);
    PutUserDtoResponse putUserDtoResponse(PutUserDto userDto, String id);
    BaseSuccessResponse deleteUser(String id);

}
