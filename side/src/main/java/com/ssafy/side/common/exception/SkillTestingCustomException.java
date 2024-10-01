package com.ssafy.side.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SkillTestingCustomException extends RuntimeException {

    HttpStatus status;
    ErrorMessage code;

    public SkillTestingCustomException(HttpStatus status, ErrorMessage code) {
        super();
        this.status = status;
        this.code = code;
    }
}
