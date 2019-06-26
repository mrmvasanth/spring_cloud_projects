package com.packs.TrackingSystemPOC.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
public class TrackerResponseEntity<T> {

    private @Getter
    HttpStatus httpStatus = HttpStatus.OK;
    private @Getter
    String message = "success";
    private @Getter
    T data;
    private @Getter
    int code = 200;


    public TrackerResponseEntity(T data) {
        this.data = data;
    }

    public TrackerResponseEntity(HttpStatus httpStatus, int code, T data) {
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public TrackerResponseEntity(int code,HttpStatus httpStatus,String message){
        this.code=code;
        this.httpStatus=httpStatus;
        this.message=message;
    }

    public TrackerResponseEntity message(String message) {
        this.message = message;
        return this;
    }

    public static <T> TrackerResponseEntity make(T data) {
        return new TrackerResponseEntity(data);
    }

    public static <T> TrackerResponseEntity make(HttpStatus httpStatus, int code, T data) {
        return new TrackerResponseEntity(httpStatus, code, data);
    }

    public static <T> TrackerResponseEntity make(int code,HttpStatus httpStatus, String message) {
        return new TrackerResponseEntity(code,httpStatus, message);
    }

}