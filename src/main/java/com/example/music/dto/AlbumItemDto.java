package com.example.music.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlbumItemDto {
//    private Map<String, String> externalUrls;
//    private String href;
    private String id;
//    private String albumType;
    private Integer totalTracks;
    private List<ImageDto> images;
    private String name;
//    private String releaseDate;
//    private List<Map<String, Object>> artists;
}