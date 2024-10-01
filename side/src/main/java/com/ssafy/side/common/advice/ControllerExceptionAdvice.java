package com.ssafy.side.common.advice;

import com.ssafy.side.common.exception.FailResponse;
import com.ssafy.side.common.exception.SkillTestingCustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(SkillTestingCustomException.class)
    public ResponseEntity<FailResponse> handleGlobalException(SkillTestingCustomException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(FailResponse.fail(ex.getStatus().value(), ex.getCode().toString()));
    }
}
