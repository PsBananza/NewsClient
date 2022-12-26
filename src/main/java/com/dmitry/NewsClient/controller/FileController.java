package com.dmitry.NewsClient.controller;

import java.io.IOException;

import javax.validation.Valid;

import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.fileInterface.FileService;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> getUser(@PathVariable @Valid final String fileName) throws CustomException, IOException {
        Resource file = fileService.getFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<CustomSuccessResponse<String>> registerUser(@RequestParam @Valid MultipartFile file) throws CustomException, IOException {

        return new ResponseEntity(new CustomSuccessResponse(fileService.fileUpt(file)), HttpStatus.OK);
    }

}
