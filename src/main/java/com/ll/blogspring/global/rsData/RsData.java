package com.ll.blogspring.global.rsData;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RsData<T> {
    private final String resultCode;
    private String msg;
    private final T data;
    private final int statusCode;

    public static <T> RsData<T> of(String resultCode, String msg, T data){
        int statusCode = Integer.parseInt(resultCode);

        return new RsData<>(resultCode, msg, data, statusCode);
    }
    public static RsData<?> of(String resultCode, String msg){
        return of(resultCode, msg, null);
    }
    public boolean isSucess(){
        return statusCode >= 200 && statusCode < 400;
    }

    public boolean isFail(){
        return !isSucess();
    }

    public <T> RsData<T> of(T data){
        return RsData.of(resultCode, msg, data);
    }
}
