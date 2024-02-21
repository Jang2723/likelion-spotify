package com.example.music.service;


import com.example.music.dto.AlbumItemDto;
import com.example.music.dto.ArtistItemDto;
import com.example.music.dto.SearchResultDto;
import com.example.music.dto.TrackItemDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

// Spotify에서 사용할 API들을 정리한다.
public interface SpotifyHttpInterface {
    @GetExchange("/search")
    SearchResultDto search(@RequestParam Map<String, ?> params);
    @GetExchange("/albums/{id}")
    AlbumItemDto getAlbum(@PathVariable("id") String id);
    @GetExchange("/artists/{id}")
    ArtistItemDto getArtist(@PathVariable("id") String id);
    @GetExchange("/tracks/{id}")
    TrackItemDto getTrack(@PathVariable("id") String id);
}