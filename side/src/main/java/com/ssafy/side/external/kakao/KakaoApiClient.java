package com.ssafy.side.external.kakao;

import com.ssafy.side.external.kakao.dto.KakaoAccessTokenInfo;
import com.ssafy.side.external.kakao.dto.KakaoUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface KakaoApiClient {

    @GetMapping(value = "/v2/user/me")
    KakaoUserResponse getUserInformation(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);

    @GetMapping(value = "/v1/user/access_token_info")
    KakaoAccessTokenInfo getAccessTokenInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}
