package com.ssafy.side.common.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends SkillTestingCustomException {

    public UnAuthorizedException(String responseMessage) {
        super(HttpStatus.UNAUTHORIZED, responseMessage);
    }
}
