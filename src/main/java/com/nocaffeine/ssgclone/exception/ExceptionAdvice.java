package com.nocaffeine.ssgclone.exception;


import com.nocaffeine.ssgclone.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<?> illegalArgumentException(IllegalArgumentException e) {
        return ResponseDto.fail( "error", e.getMessage());
    }

}
