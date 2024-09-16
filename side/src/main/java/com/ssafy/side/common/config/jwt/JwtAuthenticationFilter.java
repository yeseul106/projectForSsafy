package com.ssafy.side.common.config.jwt;

import com.ssafy.side.common.exception.ErrorMessage;
import com.ssafy.side.common.exception.UnAuthorizedException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private static final String ISSUE_TOKEN_API_URL = "/auth/reissue";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        try {
            String accessToken = jwtTokenProvider.resolveToken(request);
            if (ISSUE_TOKEN_API_URL.equals(request.getRequestURI())) {
                Cookie[] cookies = request.getCookies();
                if (cookies == null) {
                  jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.UNAUTHORIZED,
                          ErrorMessage.NO_COOKIE.getMessage());
                  return;
                }
                Optional<String> refreshToken = Arrays.stream(cookies)
                        .filter(cookie -> "refreshToken".equals(cookie.getName()))
                        .map(Cookie::getValue)
                        .findFirst();

                if(refreshToken.isEmpty()) {
                  jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.UNAUTHORIZED,
                          ErrorMessage.NO_REFRESH_TOKEN_IN_COOKIE.getMessage());
                  return;
                } else {
                  if (jwtTokenProvider.validateToken(refreshToken.get()) == JwtExceptionType.EMPTY_JWT
                          || jwtTokenProvider.validateToken(accessToken) == JwtExceptionType.EMPTY_JWT) {
                    jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.BAD_REQUEST, ErrorMessage.NO_TOKEN.getMessage());
                    return;
                  } else if (jwtTokenProvider.validateToken(accessToken) == JwtExceptionType.EXPIRED_JWT_TOKEN) {
                    if (jwtTokenProvider.validateToken(refreshToken.get()) == JwtExceptionType.EXPIRED_JWT_TOKEN) {
                      // access, refresh 둘 다 만료
                      jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.UNAUTHORIZED,
                              ErrorMessage.SIGNIN_REQUIRED.getMessage());
                      return;
                    } else if (jwtTokenProvider.validateToken(refreshToken.get()) == JwtExceptionType.VALID_JWT_TOKEN) {
                      // 토큰 재발급
                      Long memberId = jwtTokenProvider.validateMemberRefreshToken(refreshToken.get());

                      String newAccessToken = jwtTokenProvider.generateAccessToken(memberId);

                      setAuthentication(newAccessToken);
                      request.setAttribute("newAccessToken", newAccessToken);
                    }
                  } else if (jwtTokenProvider.validateToken(accessToken) == JwtExceptionType.VALID_JWT_TOKEN) {
                    jwtAuthenticationEntryPoint.setResponse(response, HttpStatus.UNAUTHORIZED,
                            ErrorMessage.VALID_ACCESS_TOKEN.getMessage());
                    return;
                  } else {
                    throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED_TOKEN.getMessage());
                  }
                }
            } else {
                JwtExceptionType jwtException = jwtTokenProvider.validateToken(accessToken);

                if (accessToken != null) {
                    // 토큰 검증
                    if (jwtException == JwtExceptionType.VALID_JWT_TOKEN) {
                        setAuthentication(accessToken);
                    }
                }
            }
        } catch (Exception e) {
          throw new UnAuthorizedException(ErrorMessage.UNAUTHORIZED.getMessage());
        }

        chain.doFilter(request, response);
    }

    private void setAuthentication(String token) {
        Claims claims = jwtTokenProvider.getAccessTokenPayload(token);
        Authentication authentication = new UserAuthentication(Long.valueOf(String.valueOf(claims.get("id"))), null,
                null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
