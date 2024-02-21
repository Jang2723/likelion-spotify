package com.example.music.dto;

import com.example.music.dto.search.AlbumSearchResultDto;
import com.example.music.dto.search.ArtistSearchResultDto;
import com.example.music.dto.search.TrackSearchResultDto;
import lombok.Data;

@Data
public class SearchResultDto {
    private TrackSearchResultDto tracks;
    private AlbumSearchResultDto albums;
    private ArtistSearchResultDto artists;
}
