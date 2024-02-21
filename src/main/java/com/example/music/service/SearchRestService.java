package com.example.music.service;

import com.example.music.dto.SearchResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchRestService implements SearchService{
    private final RestClient spotifyClient;
//    private final SpotifyTokenService tokenService;

    @Override
    public SearchResultDto search(
            String q,
            String type,
            String market,
            Integer limit,
            Integer offset
    ) {
        // /search?q=q&type=type&...
        String url = UriComponentsBuilder.fromUriString("/search")
                .queryParam("q", q)
                .queryParam("type", type)
                .queryParam("market", market)
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .build(false)
                .toUriString();
        return spotifyClient.get()
                .uri(url)
                .retrieve()
                .body(SearchResultDto.class);
    }
}
