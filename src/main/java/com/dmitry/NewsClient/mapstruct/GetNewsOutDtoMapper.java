package com.dmitry.NewsClient.mapstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dmitry.NewsClient.dto.GetNewsOutDto;
import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.Tag;

import org.springframework.stereotype.Component;

@Component
public class GetNewsOutDtoMapper {

    public GetNewsOutDto newsEntityOut(NewsEntity entity) {
        if (entity == null) {
            return null;
        }

        GetNewsOutDto getNewsOutDto = new GetNewsOutDto();

        getNewsOutDto.setDescription(entity.getDescription());
        getNewsOutDto.setId(String.valueOf(entity.getId()));
        getNewsOutDto.setImage(entity.getImage());
        getNewsOutDto.setUserId(entity.getUser().getId());
        List<Tag> list = entity.getTags();
        if (list != null) {
            getNewsOutDto.setTags(new ArrayList<Tag>(list));
        }
        getNewsOutDto.setTitle(entity.getTitle());
        getNewsOutDto.setUsername(entity.getUsername());

        return getNewsOutDto;
    }
}
