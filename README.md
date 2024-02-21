## API 사용해보기 - Spotify
- https://developer.spotify.com/ 
- 개발자 항목의 **Dashboard**으로 이동
- API 이용약관에 동의 후 이메일 인증 진행
- Create app에서 API는 Web API만 선택

## 간단 요구사항
- 사용자는 검색을 할 수 있습니다. 검색을 위한 문자열과, 아티스트, 앨범, 노래의 기준을 전달합니다. 이에 따른 결과를 API로 반환합니다.
- 사용자는 검색 결과를 바탕으로 리뷰를 할 수 있는 대상을 선택합니다. 아티스트, 앨범, 노래에 대해 각각 리뷰를 작성할 수 있습니다.
- 리뷰를 작성할 때는 제목, 내용, 점수, 리뷰 대상 종류(아티스트, 앨범, 노래), 그리고 Spotify ID (Spotify 내부에서 데이터를 구분하기 위한 ID)를 전달합니다.
- 리뷰가 저장될 때는 리뷰 대상의 이미지의 링크와 Spotify에서 확인할 수 있는 링크가 포함되어 저장됩니다.

## 사용할 API
이 프로젝트에서 활용할 API

### API 인증
- App이 만들어지면 Client ID와 Client Secret이 부여됨 (Basic Information에서 확인)
- 다른 Spotify API를 사용하기 위한 인증 정보
- Client ID와 Client Secret을 `application/x-www-form-urlencoded`의 Content-Type으로
- `grant_type=client_credentials` 와 함께 전달

```bash
curl -X POST "https://accounts.spotify.com/api/token" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "grant_type=client_credentials&client_id=your-client-id&client_secret=your-client-secret"
```
- 응답으로 1시간 동안 유효한 Bearer Token 발급됨
- 이 토큰을 이후 모든 요청에 Authorization 헤더에 포함해야 함!
```json
{
   "access_token": "NgCXRKc...MzYjw",
   "token_type": "bearer",
   "expires_in": 3600
}
```
---
### 검색 하기
Search for Item API : https://developer.spotify.com/documentation/web-api/reference/search   
검색 기준은 전부 Query Parmeter로 추가하며, 검색 문자열, 검색 대상, 발매 국가, 결과 갯수, offset을 추가   
`GET /search`
- `q`: 검색 문자열
- `type`: 검색 대상 (artist, album, track 등)
- `market`: 발매 국가 (KR 등)
- `limit`: 결과 갯수
- `offset`: 몇번째 결과부터 보여줄 지

### 앨범 찾기
Get Album API : https://developer.spotify.com/documentation/web-api/reference/get-an-album   
`GET /albums/{id}`
- `id` : Spotify ID 제공
  - 잘못된 ID를 제공하면 400 응답이 발생

### 아티스트 찾기
Get Artist API : https://developer.spotify.com/documentation/web-api/reference/get-an-artist   
`GET / artists/{id}`
- `id` : Spotify ID 제공
  - 잘못된 ID를 제공하면 400 응답이 발생

### 노래 찾기
Get Track API : https://developer.spotify.com/documentation/web-api/reference/get-track   
`GET /tracks/{id}`
- `id`: Spotify ID를 제공
  - 잘못된 ID를 제공하면 400 응답이 발생