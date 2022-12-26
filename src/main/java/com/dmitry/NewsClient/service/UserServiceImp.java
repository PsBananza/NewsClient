package com.dmitry.NewsClient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.PublicUserView;
import com.dmitry.NewsClient.dto.PutUserDto;
import com.dmitry.NewsClient.dto.PutUserDtoResponse;
import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.mapstruct.UserViewMapper;
import com.dmitry.NewsClient.repository.RepositoryUser;
import com.dmitry.NewsClient.service.userInterface.UserService;
import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserViewMapper userViewMapper;

    private final RepositoryUser userRepo;

    private final MongoTemplate mongoTemplate;

    @Override
    public List<PublicUserView> getUserAll() {
//        List<UserEntity> userEntity = userRepo.findAll();
        List<UserEntity> userEntity = mongoTemplate.findAll(UserEntity.class, "userEntity");

        PublicUserView userView = new PublicUserView();
        List<PublicUserView> publicUserViews = new ArrayList<>();
        for (UserEntity p: userEntity) {
            userViewMapper.userView(p);
            publicUserViews.add(userView);
        }
        return publicUserViews;
    }

    @Override
    public PublicUserView getUserId(String id) {
        UserEntity entity = mongoTemplate.findById(new ObjectId(id), UserEntity.class);
        PublicUserView userView1 = userViewMapper.userView(entity);
        return userView1;
    }

    @Override
    public PublicUserView getUserInfo(String id) {
        UserEntity entity = mongoTemplate.findById(new ObjectId(id), UserEntity.class);
        PublicUserView userView1 = userViewMapper.userView(entity);
        return userView1;
    }

    @Override
    public PutUserDtoResponse putUserDtoResponse(PutUserDto userDto, String id) {
        UserEntity entity = mongoTemplate.findById(new ObjectId(id), UserEntity.class);
        entity.setAvatar(userDto.getAvatar())
                .setEmail(userDto.getEmail())
                .setName(userDto.getName())
                .setAvatar(FileServiceImp.avatar)
                .setRole(userDto.getRole());
        mongoTemplate.save(entity);
        return userViewMapper.userDtoResp(entity);
    }

    @Override
    public BaseSuccessResponse deleteUser(String id) {
//        UserEntity entity = userRepo.findById(id);
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemplate.findAndRemove(query, UserEntity.class, "userEntity");
        return new BaseSuccessResponse();
    }




}
