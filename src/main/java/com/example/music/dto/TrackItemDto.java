package com.example.music.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrackItemDto {
//    private Map<String, String> externalUrls;
    private AlbumItemDto album;
//    private List<ArtistItemDto> artists;
    private String id;
    private String name;
    private Integer trackNumber;
}