package com.nocaffeine.ssgclone.common;

import com.nocaffeine.ssgclone.common.exception.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ResponseDto<T> {
    private boolean success;
    private String message;
    private T data;
    private Error error;
    public static <T> ResponseDto<T> success(String message) {
        return new ResponseDto<>(true, message, null,null);
    }

    public static <T> ResponseDto<T> success(String message,T data) {
        return new ResponseDto<>(true, message, data,null);
    }

    public static <T> ResponseDto<T> fail(BaseResponseStatus code, String message) {
        return new ResponseDto<>(false, message, null, new Error(code));
    }

    public static <T> ResponseDto<T> fail(BaseResponseStatus code, T data,String message) {
        return new ResponseDto<>(false, message, data, new Error(code));
    }
    @Getter
    @AllArgsConstructor
    static class Error {
        private BaseResponseStatus code;
    }

}
