package com.dmitry.NewsClient.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageableResponse<T> {

    List<GetNewsOutDto> content;
    Long numberOfElements;

}
