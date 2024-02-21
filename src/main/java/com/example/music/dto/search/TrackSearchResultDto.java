package com.example.music.dto.search;


import com.example.music.dto.TrackItemDto;
import lombok.Data;

import java.util.List;


@Data
public class TrackSearchResultDto {
    private String href;
    private Integer limit;
    private String next;
    private Integer offset;
    private String previous;
    private Integer total;
    private List<TrackItemDto> items;
}