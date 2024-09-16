package com.ssafy.side.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends SkillTestingCustomException{

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
