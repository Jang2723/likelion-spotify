package com.example.music.config;

import com.example.music.SpotifyTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class SpotifyRestConfig {
    private final SpotifyTokenService tokenService;

    @Bean
    public RestClient spotifyClient() {
        return RestClient.builder()
                .baseUrl("https://api.spotify.com/v1")
                // 이 RestClient로 보내지는 모든 요쳥에 기본 헤더를
                // 메서드를 전달한다.
                .requestInitializer(request -> request.getHeaders()
                        .setBearerAuth(tokenService.getToken()))
                .build();
    }
}
