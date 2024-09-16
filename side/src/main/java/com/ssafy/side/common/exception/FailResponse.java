package com.ssafy.side.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FailResponse {
    private final int status;
    private final String message;

    public static FailResponse fail(int status, String message) {
        return FailResponse.builder()
                .status(status)
                .message(message)
                .build();
    }
}
