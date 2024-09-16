package com.ssafy.side.api.auth.dto;

import jakarta.validation.constraints.NotNull;

public record ReissueTokenRequestDto(
        @NotNull(message = "refreshToken 값은 null 일 수 없습니다.") String refreshToken
) {
}
