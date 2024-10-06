package com.ssafy.side.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnAuthorizedException extends SkillTestingCustomException {

    public UnAuthorizedException(ErrorMessage code) {
        super(HttpStatus.UNAUTHORIZED, code);
    }
}
