package com.dmitry.NewsClient.controller;

import javax.validation.Valid;

import com.dmitry.NewsClient.dto.AuthDto;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.LoginUserDto;
import com.dmitry.NewsClient.dto.RegisterUserDto;
import com.dmitry.NewsClient.service.GetUploadingPurchase;
import com.dmitry.NewsClient.service.authInterface.AuthService;
import com.dmitry.NewsClient.service.authInterface.RegistrationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@CrossOrigin
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService userService;
    private final RegistrationService registerService;
    private final GetUploadingPurchase purchase;

    @PostMapping("/register")
    public ResponseEntity<CustomSuccessResponse<LoginUserDto>> registerUser(@RequestBody @Valid RegisterUserDto userDto) {
        return new ResponseEntity(new CustomSuccessResponse(registerService.registerUser(userDto)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomSuccessResponse<LoginUserDto>> authUser(@RequestBody @Valid AuthDto authDto) {
        return new ResponseEntity(new CustomSuccessResponse(userService.authUser(authDto)), HttpStatus.OK);
    }

    @GetMapping("/log")
    public ResponseEntity<byte[]> authUser() throws IOException, ParseException {
        return purchase.uploadPurchaseResultsForSa();
    }

//    @GetMapping("/mongo")
//    public ResponseEntity mongo() throws IOException, ParseException {
//        MongoDbTest mongoDbTest1 = new MongoDbTest();
//        mongoDbTest1.mongoDb1();
//        return ResponseEntity.ok().build();
//    }

}
