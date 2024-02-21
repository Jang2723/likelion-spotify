package com.example.music.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArtistItemDto {
//    private Map<String, String> externalUrls;
//    private List<String> genres;
    private String id;
    private List<ImageDto> images;
    private String name;
//    private Integer popularity;
}