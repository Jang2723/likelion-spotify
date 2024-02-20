package com.example.music;

import com.example.music.dto.AccessTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Slf4j
@Component
public class SpotifyTokenService {
//    @Value("${spotify.client-id}")
//    private String clientId;
//    @Value("${spotify.client-secret}")
//    private String clientSecret;

    // Request Body가 변하지 않으므로 필드로 올리자.
    private final MultiValueMap<String , Object> parts;

    // 마지막으로 Token을 발급한 시점
    private LocalDateTime lastIssued;

    // 현재 사용 중인 Bearer Token
    private String token;
    private final RestClient authRestClient;

    public SpotifyTokenService(
            RestClient authRestClient,
            @Value("${spotify.client-id}")
            String clientId,
            @Value("${spotify.client-secret}")
            String clientSecret
    ) {
        this.authRestClient = authRestClient;
        // 항상 같은 Request Body를 보내게 되므로,
        // 해당 Request Body는 저장을 해두자
        this.parts = new LinkedMultiValueMap<>();
        this.parts.add("grant_type","client_credentials");
        this.parts.add("client_id",clientId);
        this.parts.add("client_secret",clientSecret);

        reissue();
    }

    // Token을 재발행하는 메서드
    private void reissue() {
        log.info("issuing access token");
        // 1. Token을 발행 받는다
        AccessTokenDto response = authRestClient.post()
                // Content-Type : 헤더 설정
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(parts)
                .retrieve()
                .body(AccessTokenDto.class);

        // 2. 내가 Token을 받은 시간을 기록한다.
        lastIssued = LocalDateTime.now();
        // 3. 새로 발급받은 Token을 기록한다.
        token = response.getAccessToken();
        log.info("new access token is: {}", token);
    }

    // 현재 사용중인 Token을 반환하는 메서드
    public String getToken() {
        log.info("last issued: {}", lastIssued);
        log.info("time passed: {} mins", ChronoUnit.MINUTES.between(lastIssued, LocalDateTime.now()));
        // 만약 마지막에 발급받은지 50분이 지났다.
        if (lastIssued.isBefore(LocalDateTime.now().minusMinutes(50)))
            // 그런 경우 재발행
            reissue();;
        // 재발행을 했든 안했뜬 token을 돌려준다.
        return token;
    }

    // 메서드가 실행될 때 마다 AccessToken이 재발행 된다.
    @Deprecated
    public AccessTokenDto getAccessToken() {
//        MultiValueMap<String ,Object> parts = new LinkedMultiValueMap<>();
//        parts.add("grant_type","client_credentials");
//        parts.add("client_id",clientId);
//        parts.add("client_secret",clientSecret);

        return authRestClient.post()
                // Content-Type : 헤더 설정
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(parts)
                .retrieve()
                .body(AccessTokenDto.class);
    }
}
