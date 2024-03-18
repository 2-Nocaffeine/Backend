package com.nocaffeine.ssgclone.common.exception;


import com.nocaffeine.ssgclone.common.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BaseException.class)
//    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> baseException(BaseException e) {
        return ResponseDto.fail(e.getErrorCode(), e.getMessage());
    }

}
