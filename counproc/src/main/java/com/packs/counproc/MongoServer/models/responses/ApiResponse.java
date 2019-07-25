package com.packs.counproc.MongoServer.models.responses;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private HttpStatus httpStatus = HttpStatus.OK;
    private String message = "success";
    private T data;
    private int code = 200;

    //response constructors
    public ApiResponse(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(int code, HttpStatus httpStatus, T data,String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public ApiResponse() {
    }

    //Static constructor methods
    public static <T> ApiResponse make(int code, HttpStatus httpStatus, String message) {
        return new ApiResponse(code, httpStatus, message);
    }

    public static <T> ApiResponse make(int code, String message) {
        return new ApiResponse(code, message);
    }

    public static <T> ApiResponse make (int code, HttpStatus httpStatus, T data, String message) {
        return new ApiResponse(code,httpStatus,data,message);
    }

    //getters and setters
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}