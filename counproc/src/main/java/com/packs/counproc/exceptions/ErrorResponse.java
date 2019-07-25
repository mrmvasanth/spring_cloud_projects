package com.packs.counproc.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@ToString
public class ErrorResponse
{
    public ErrorResponse(int code,HttpStatus httpStatus,String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
        this.httpStatus=httpStatus;
        this.code=code;
    }

    private String message;

    private List<String> details;

    private int code;

    private HttpStatus httpStatus;

}