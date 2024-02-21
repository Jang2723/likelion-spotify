package com.example.music.dto;

import com.example.music.entity.Review;
import com.example.music.entity.TypeEnum;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String title;
    private String content;
    private Double rating;
    private TypeEnum type;
    private String spotifyId;
    @Setter
    private String coverImage;
    @Setter
    private String spotifyLink;

    public static ReviewDto fromEntity(Review entity) {
        return ReviewDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .rating(entity.getRating())
                .type(entity.getType())
                .spotifyId(entity.getSpotifyId())
                .coverImage(entity.getCoverImage())
                .spotifyLink(entity.getSpotifyLink())
                .build();
    }
}