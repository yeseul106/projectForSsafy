package com.ssafy.side.common.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends SkillTestingCustomException {

    public UnAuthorizedException(ErrorMessage code) {
        super(HttpStatus.UNAUTHORIZED, code);
    }
}
