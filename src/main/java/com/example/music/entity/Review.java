package com.example.music.entity;


import com.example.music.dto.ReviewDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity{
    private String title;
    private String content;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    private String coverImage;
    private String spotifyId;
    private String spotifyLink;

    public static Review newEntity(ReviewDto dto) {
        return Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .rating(dto.getRating())
                .type(dto.getType())
                .coverImage(dto.getCoverImage())
                .spotifyId(dto.getSpotifyId())
                .spotifyLink(dto.getSpotifyLink())
                .build();
    }
}