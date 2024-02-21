package com.example.music.service;

import com.example.music.dto.ReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    ReviewDto create(ReviewDto dto);
    Page<ReviewDto> readList(Pageable pageable);
    ReviewDto readOne(Long id);
    ReviewDto update(Long id, ReviewDto dto);
    void delete(Long id);

}