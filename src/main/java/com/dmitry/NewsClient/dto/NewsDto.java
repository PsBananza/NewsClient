package com.dmitry.NewsClient.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.dmitry.NewsClient.exeption.TagExeption;
import com.dmitry.NewsClient.exeption.ValidationConstants;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewsDto {

    @NotBlank(message = ValidationConstants.NEWS_DESCRIPTION_NOT_NULL)
    @Size(min = ValidationConstants.NEWS_DESCRIPTION_SIZE_MIN, max = ValidationConstants.NEWS_DESCRIPTION_SIZE_MAX,
            message = ValidationConstants.NEWS_DESCRIPTION_SIZE)
    private String description;

    @TagExeption
    private List<String> tags;

    private String image;

    @NotBlank(message = ValidationConstants.NEWS_TITLE_NOT_NULL)
    @Size(min = ValidationConstants.NEWS_TITLE_SIZE_MIN, max = ValidationConstants.NEWS_TITLE_SIZE_MAX,
            message = ValidationConstants.NEWS_TITLE_SIZE)
    private String title;
    private String userId;

}
