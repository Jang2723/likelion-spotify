package com.example.music.service;

import com.example.music.dto.SearchResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class SearchHttpService implements SearchService{
    private final SpotifyHttpInterface spotifyInterface;

    @Override
    public SearchResultDto search(
            String q,
            String type,
            String market,
            Integer limit,
            Integer offset
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("q", q);
        params.put("type", type);
        params.put("market", market);
        params.put("limit", limit);
        params.put("offset", offset);
        return spotifyInterface.search(params);
    }
}