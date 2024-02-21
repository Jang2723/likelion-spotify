package com.example.music;

import com.example.music.service.SearchRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchRestService service;

    @GetMapping
    public Object search(
            @RequestParam("q")
            String q,
            @RequestParam(value = "type", defaultValue = "album,artist,track")
            String type,
            @RequestParam(value = "market", defaultValue = "KR")
            String market,
            @RequestParam(value = "limit", defaultValue = "5")
            Integer limit,
            @RequestParam(value = "offset", defaultValue = "0")
            Integer offset
    ) {
        return service.search(q,type,market,limit,offset);
    }

}
