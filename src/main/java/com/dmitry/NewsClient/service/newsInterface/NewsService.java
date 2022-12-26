package com.dmitry.NewsClient.service.newsInterface;

import java.util.UUID;

import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.CreateNewsSuccessResponse;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.NewsDto;
import com.dmitry.NewsClient.dto.PageableResponse;

public interface NewsService {
    CreateNewsSuccessResponse createNews(NewsDto newsDto);
    CustomSuccessResponse getNews(int page, int perPage);
    PageableResponse findNews(int page, int perPage, String author, String keywords, String tags);
    BaseSuccessResponse deleteNews(String id);
    BaseSuccessResponse putNews(String id, NewsDto newsDto);
}
