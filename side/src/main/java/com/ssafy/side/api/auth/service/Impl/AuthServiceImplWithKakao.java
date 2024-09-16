package com.ssafy.side.api.auth.service.Impl;

import static com.ssafy.side.common.exception.ErrorMessage.MISSING_AUTHORIZATION_CODE;

import com.ssafy.side.api.auth.dto.SocialInfoDto;
import com.ssafy.side.api.auth.dto.SocialLoginRequestDto;
import com.ssafy.side.api.auth.dto.SocialLoginResponseDto;
import com.ssafy.side.api.auth.service.AuthService;
import com.ssafy.side.api.auth.service.KakaoAuthService;
import com.ssafy.side.api.member.domain.Member;
import com.ssafy.side.api.member.domain.MemberRepository;
import com.ssafy.side.common.config.jwt.JwtTokenProvider;
import com.ssafy.side.common.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplWithKakao implements AuthService {

    private final KakaoAuthService kakaoAuthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public SocialLoginResponseDto socialLogin(SocialLoginRequestDto requestDto) {
        if (requestDto.code() == null) {
            throw new BadRequestException(MISSING_AUTHORIZATION_CODE.getMessage());
        }

        try {
            SocialInfoDto socialData = kakaoAuthService.getKakaoUserData(requestDto);

            String refreshToken = jwtTokenProvider.generateRefreshToken();

            Boolean isExistUser = memberRepository.existsBySocialId(String.valueOf(socialData.id()));

            // 신규 유저 저장
            if (!isExistUser.booleanValue()) {
                Member member = Member.builder()
                        .nickname(socialData.nickname())
                        .socialId(String.valueOf(socialData.id()))
                        .refreshToken(refreshToken)
                        .build();

                memberRepository.save(member);
            }
            else memberRepository.findMemberBySocialIdOrThrow(String.valueOf(socialData.id())).updateRefreshToken(refreshToken);

            // socialId를 통해서 등록된 유저 찾기
            Member signedMember = memberRepository.findMemberBySocialIdOrThrow(String.valueOf(socialData.id()));

            String accessToken = jwtTokenProvider.generateAccessToken(signedMember.getId());

            return new SocialLoginResponseDto(accessToken, signedMember.getRefreshToken());

        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException();
        }
    }
}
