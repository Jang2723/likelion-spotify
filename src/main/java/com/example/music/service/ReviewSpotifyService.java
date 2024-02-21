package com.example.music.service;

import com.example.music.dto.AlbumItemDto;
import com.example.music.dto.ArtistItemDto;
import com.example.music.dto.ReviewDto;
import com.example.music.dto.TrackItemDto;
import com.example.music.entity.Review;
import com.example.music.repo.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewSpotifyService implements ReviewService {
    private final SpotifyService spotifyService;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDto create(ReviewDto dto) {
        this.setUrls(dto);
        Review newEntity = Review.newEntity(dto);
        return ReviewDto.fromEntity(reviewRepository.save(newEntity));
    }

    @Override
    public Page<ReviewDto> readList(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(ReviewDto::fromEntity);
    }

    @Override
    public ReviewDto readOne(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewDto::fromEntity)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @Override
    public ReviewDto update(Long id, ReviewDto dto) {
        Review target = reviewRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        target.setTitle(dto.getTitle());
        target.setContent(dto.getContent());
        target.setRating(dto.getRating());

        return ReviewDto.fromEntity(reviewRepository.save(target));
    }


    @Override
    public void delete(Long id) {
        if (!reviewRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        reviewRepository.deleteById(id);
    }


    private void setUrls(ReviewDto dto) {
        switch (dto.getType()) {
            case ARTIST -> {
                ArtistItemDto response = spotifyService.getArtist(dto.getSpotifyId());
                if (!response.getImages().isEmpty())
                    dto.setCoverImage(response.getImages().get(0).getUrl());
                dto.setSpotifyLink(String.format("https://open.spotify.com/artist/%s", response.getId()));
            }
            case ALBUM -> {
                AlbumItemDto response = spotifyService.getAlbum(dto.getSpotifyId());
                if (!response.getImages().isEmpty())
                    dto.setCoverImage(response.getImages().get(0).getUrl());
                dto.setSpotifyLink(String.format("https://open.spotify.com/album/%s", response.getId()));
            }
            case TRACK -> {
                TrackItemDto response = spotifyService.getTrack(dto.getSpotifyId());
                if (response.getAlbum() != null && !response.getAlbum().getImages().isEmpty())
                    dto.setCoverImage(response.getAlbum().getImages().get(0).getUrl());
                dto.setSpotifyLink(String.format("https://open.spotify.com/track/%s", response.getId()));
            }
        }
    }
}