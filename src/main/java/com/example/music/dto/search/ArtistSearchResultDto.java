package com.example.music.dto.search;

import com.example.music.dto.ArtistItemDto;
import lombok.Data;

import java.util.List;

@Data
public class ArtistSearchResultDto {
    private String href;
    private Integer limit;
    private String next;
    private Integer offset;
    private String previous;
    private Integer total;
    private List<ArtistItemDto> items;
}