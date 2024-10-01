package com.ssafy.side.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends SkillTestingCustomException{

    public NotFoundException(ErrorMessage code) {
        super(HttpStatus.NOT_FOUND, code);
    }
}
