package com.example.music.service;

import com.example.music.dto.SearchResultDto;

public interface SearchService {
    SearchResultDto search(
            String q,
            String type,
            String market,
            Integer limit,
            Integer offset
    );
}