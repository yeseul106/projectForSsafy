package com.ssafy.side.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkillTestingCustomException extends RuntimeException {

    HttpStatus code;
    String error;

    public SkillTestingCustomException(HttpStatus statusCode) {
        super();
        this.code = statusCode;
    }

    public SkillTestingCustomException(HttpStatus statusCode, String responseMessage) {
        super(responseMessage);
        this.code = statusCode;
        this.error = responseMessage;
    }

    public int getStatusCode() {
        return this.code.value();
    }
}
