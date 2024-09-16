package com.ssafy.side.api.auth.service;

import com.ssafy.side.api.auth.dto.SocialLoginRequestDto;
import com.ssafy.side.api.auth.dto.SocialLoginResponseDto;

public interface AuthService {

    SocialLoginResponseDto socialLogin(SocialLoginRequestDto requestDto);

}
