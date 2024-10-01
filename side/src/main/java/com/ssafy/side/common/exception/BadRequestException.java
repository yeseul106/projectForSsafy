package com.ssafy.side.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends SkillTestingCustomException {

    public BadRequestException(ErrorMessage code) {
        super(HttpStatus.BAD_REQUEST, code);
    }
}
