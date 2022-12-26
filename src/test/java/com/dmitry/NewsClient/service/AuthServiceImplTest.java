package com.dmitry.NewsClient.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.mapstruct.LoginUserDtoMapper;
import com.dmitry.NewsClient.mongo.MongoRep;
import com.dmitry.NewsClient.repository.RepositoryUser;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @MockBean
    private LoginUserDtoMapper loginUserDtoMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RepositoryUser repositoryUser;
    @MockBean
    private MongoRep mongoRep;

    @Test
    void testRegisterUser() {
        UserEntity userEntity = new UserEntity()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setPassword("banana")
                .setRole("Role");

        UserEntity userEntity1 = new UserEntity()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setPassword("banana")
                .setRole("Role");
        when(mongoRep.mongoFindEmail((String) any())).thenReturn(userEntity);
        when(mongoRep.mongoSaveLogin((UserEntity) any())).thenReturn(userEntity1);

    }

    @Test
    void testAuthUser() {
        UserEntity userEntity = new UserEntity()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setPassword("banana")
                .setRole("Role");
        when(mongoRep.mongoFindEmail((String) any())).thenReturn(userEntity);

        LoginUserDto loginUserDto = new LoginUserDto()
                .setAvatar("Avatar")
                .setEmail("Bananza@yandex.ru")
                .setId(UUID.randomUUID())
                .setName("Name")
                .setRole("Role")
                .setToken("ABC123");
        when(loginUserDtoMapper.loginUserDto((UserEntity) any())).thenReturn(loginUserDto);
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);

        AuthDto authDto = new AuthDto();
        authDto.setEmail("Bananza@yandex.ru");
        authDto.setPassword("banana");
        assertSame(loginUserDto, authServiceImpl.authUser(authDto));
        verify(mongoRep).mongoFindEmail((String) any());
        verify(loginUserDtoMapper).loginUserDto((UserEntity) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }
}

