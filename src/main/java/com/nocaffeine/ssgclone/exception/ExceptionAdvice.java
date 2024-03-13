package com.nocaffeine.ssgclone.exception;


import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

//    @ExceptionHandler(IllegalStateException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseDto<?> illegalArgumentExceptionAdvice(IllegalStateException e) {
//        return ResponseDto.fail( "error", e.getMessage());
//    }

}
