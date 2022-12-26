package com.dmitry.NewsClient.service;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.exeption.ErrorCodes;
import com.dmitry.NewsClient.mapstruct.LoginUserDtoMapper;
import com.dmitry.NewsClient.mongo.MongoRep;
import com.dmitry.NewsClient.repository.RepositoryUser;
import com.dmitry.NewsClient.mongo.UserMongoRepository;
import com.dmitry.NewsClient.service.authInterface.AuthService;
import com.dmitry.NewsClient.service.authInterface.RegistrationService;
import lombok.RequiredArgsConstructor;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements RegistrationService, AuthService {

    private final RepositoryUser userRepo;
    private final PasswordEncoder passwordEncoder;
    private final LoginUserDtoMapper loginUserDtoMapper;
    private final UserMongoRepository userMongoRep;
    private final MongoTemplate mongoTemplate;

    @Override
    public LoginUserDto registerUser(RegisterUserDto userDto) {
        if (userRepo.findByEmail(userDto.getEmail()) != null) {
            throw new CustomException(ErrorCodes.USER_WITH_THIS_EMAIL_ALREADY_EXIST);
        }
        UserEntity entity = new UserEntity()
                .setEmail(userDto.getEmail())
                .setName(userDto.getName())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setRole(userDto.getRole())
                .setAvatar(FileServiceImp.avatar);
        userMongoRep.save(entity);
        return loginUserDtoMapper.loginUserDto(entity);
    }
    @Override
    public LoginUserDto authUser(AuthDto authDto) {
        UserEntity entity;
        Query query = new Query(Criteria.where("email").is(authDto.getEmail()));
        entity = mongoTemplate.findOne(query, UserEntity.class);
        if (entity == null) {
            throw new CustomException(ErrorCodes.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(authDto.getPassword(), entity.getPassword())) {
            throw new CustomException(ErrorCodes.PASSWORD_NOT_VALID);
        }

        return loginUserDtoMapper.loginUserDto(entity);
    }

}
