package com.ssafy.side.api.auth.dto;

import jakarta.validation.constraints.NotNull;

public record LoginAccessTokenDto(
        @NotNull(message = "accessToken 값은 null일 수 없습니다.") String accessToken
) {
}
