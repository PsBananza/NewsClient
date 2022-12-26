package com.dmitry.NewsClient.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.dmitry.NewsClient.config.jwt.JwtProvider;
import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.PublicUserView;
import com.dmitry.NewsClient.dto.PutUserDto;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.userInterface.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtProvider provider;

    @GetMapping
    public ResponseEntity<CustomSuccessResponse<List<PublicUserView>>> allUser() throws CustomException {
        return new ResponseEntity(new CustomSuccessResponse(userService.getUserAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomSuccessResponse<PublicUserView>> idUser(@PathVariable String id) throws CustomException {
        return new ResponseEntity(new CustomSuccessResponse(userService.getUserId(id)), HttpStatus.OK);
    }
    @GetMapping("/info")
    public ResponseEntity<CustomSuccessResponse<PublicUserView>> infoUser(@RequestHeader(name = "Authorization") String token) throws CustomException {
        return new ResponseEntity(new CustomSuccessResponse(userService.getUserInfo(String.valueOf(provider.getLoginFromToken(token)))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomSuccessResponse<PublicUserView>> putUser(@RequestHeader(name = "Authorization") String token,
                                                                         @RequestBody @Valid PutUserDto putUserDto) throws CustomException {
        return new ResponseEntity(new CustomSuccessResponse(userService.putUserDtoResponse(putUserDto, provider.getLoginFromToken(token))), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BaseSuccessResponse> deleteUser(@RequestHeader(name = "Authorization") String token) throws CustomException {
        return new ResponseEntity(userService.deleteUser(provider.getLoginFromToken(token)), HttpStatus.OK);
    }


}
