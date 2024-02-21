package com.example.music;

import com.example.music.dto.ReviewDto;
import com.example.music.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("reviews")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(
            ReviewService service
    ) {
        this.service = service;
    }

    @PostMapping
    public ReviewDto create(
            @RequestBody
            ReviewDto dto
    ) {
        return service.create(dto);
    }

    @GetMapping
    public Page<ReviewDto> readList(
            @PageableDefault(size = 5)
            Pageable pageable
    ) {
        return service.readList(pageable);
    }

    @GetMapping("{id}")
    public ReviewDto readOne(
            @PathVariable("id")
            Long id
    ) {
        return service.readOne(id);
    }

    @PutMapping("{id}")
    public ReviewDto update(
            @PathVariable("id")
            Long id,
            @RequestBody
            ReviewDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id")
            Long id
    ) {
        service.delete(id);
    }
}