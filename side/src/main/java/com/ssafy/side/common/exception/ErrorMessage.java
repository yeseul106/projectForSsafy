package com.ssafy.side.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    /**
     * 400 Bad Request
     */
    MISSING_AUTHORIZATION_CODE("소셜 로그인 인가 코드 값이 누락되었습니다."),
    NO_TOKEN("토큰을 넣어주세요."),
    NO_COOKIE("쿠키가 비었습니다."),
    NO_REFRESH_TOKEN_IN_COOKIE("쿠키에 refreshToken 값이 없습니다."),

    /**
     * 401 UNAUTHORIZED
     */
    UNAUTHORIZED("Unauthorized"),
    UNAUTHORIZED_TOKEN("유효하지 않은 토큰입니다."),
    KAKAO_UNAUTHORIZED_USER("카카오 로그인 실패. 만료되었거나 잘못된 인가코드입니다."),
    SIGNIN_REQUIRED("access, refreshToken 모두 만료되었습니다. 재로그인이 필요합니다."),
    VALID_ACCESS_TOKEN("아직 유효한 accessToken 입니다."),

    /**
     * 404 NOT_FOUND
     */
    NOT_FOUND_MEMBER("해당하는 유저가 없습니다."),
    ;

    private final String message;
}
