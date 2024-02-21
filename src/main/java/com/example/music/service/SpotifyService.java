package com.example.music.service;

import com.example.music.dto.AlbumItemDto;
import com.example.music.dto.ArtistItemDto;
import com.example.music.dto.TrackItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyService {
    private final SpotifyHttpInterface spotifyClient;
    public AlbumItemDto getAlbum(String id) {
        return spotifyClient.getAlbum(id);
    }

    public ArtistItemDto getArtist(String id) {
        return spotifyClient.getArtist(id);
    }

    public TrackItemDto getTrack(String id) {
        return spotifyClient.getTrack(id);
    }
}