package com.dmitry.NewsClient.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseErrorDTO {
    private List<Integer> codes;
    @Builder.Default
    private int statusCode = 1;
    @Builder.Default
    private boolean success = true;
    private String timeStamp;
}
