package com.packs.counproc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class ApiResponseBody<T> {
    private int code;

    private HttpStatus httpStatus;

    private String message;

    private T data;


    public ApiResponseBody(T data,String message) {
        this.data = data;
        this.httpStatus=HttpStatus.OK;
        this.message=message;
        this.code=200;
    }

    public ApiResponseBody(String message) {
        this.httpStatus=HttpStatus.OK;
        this.message=message;
        this.code=200;
    }

    public ApiResponseBody(int code,HttpStatus httpStatus, String message) {
        this.httpStatus=httpStatus;
        this.message=message;
        this.code=code;
    }

}
