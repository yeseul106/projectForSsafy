package com.ssafy.side.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FailResponse {
    private final int status;
    private final String code;

    public static FailResponse fail(int status, String code) {
        return FailResponse.builder()
                .status(status)
                .code(code)
                .build();
    }
}
