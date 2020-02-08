package com.railvayticketiffice.exception;

import org.springframework.http.HttpStatus;


public class RequestException extends RuntimeException {

    public RequestException() {
    }

    public RequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    private HttpStatus httpStatus;


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
