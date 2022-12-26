package com.dmitry.NewsClient.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.dmitry.NewsClient.config.jwt.JwtProvider;
import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.CreateNewsSuccessResponse;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.GetNewsOutDto;
import com.dmitry.NewsClient.dto.NewsDto;
import com.dmitry.NewsClient.dto.PageableResponse;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.service.newsInterface.NewsService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/news")
@RestController
@RequiredArgsConstructor
public class NewsController {
    private final NewsService service;
    private final JwtProvider provider;

    @PostMapping
    public CreateNewsSuccessResponse createNews(@RequestHeader(name = "Authorization") String token, @Valid
                                                @RequestBody NewsDto newsDto) throws CustomException {
        return service.createNews(newsDto);
    }

    @GetMapping
    public CustomSuccessResponse<PageableResponse<List<GetNewsOutDto>>> getNews(@RequestParam int page,
                                                                                @RequestParam int perPage) throws CustomException {
        return service.getNews(page, perPage);
    }

    @GetMapping("/find")
    public PageableResponse<List<GetNewsOutDto>> findNews(@RequestParam int page,
                                                          @RequestParam int perPage,
                                                          @RequestParam(required = false) String author,
                                                          @RequestParam(required = false) String tags,
                                                          @RequestParam(required = false) String keywords) throws CustomException {
        return service.findNews(page, perPage, author, keywords, tags);
    }

    @DeleteMapping("/{id}")
    public BaseSuccessResponse deleteNews(@PathVariable String id) throws CustomException {
        return service.deleteNews(id);
    }

    @PutMapping("/{id}")
    public BaseSuccessResponse putNews(@PathVariable String id, @RequestBody @Valid NewsDto newsDto) throws CustomException {
        return service.putNews(id, newsDto);
    }

}
