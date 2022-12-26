package com.dmitry.NewsClient.dto;

import java.util.List;
import java.util.UUID;

import com.dmitry.NewsClient.entity.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetNewsOutDto {

    private String description;
    private String id;
    private String image;
    private List<Tag> tags;
    private String title;
    private String userId;
    private String username;


}
