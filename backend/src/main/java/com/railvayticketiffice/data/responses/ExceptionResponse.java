package com.railvayticketiffice.data.responses;

import com.railvayticketiffice.exception.RequestException;

public class ExceptionResponse {

    public ExceptionResponse(){}

    public ExceptionResponse(int status, String error, String message, long timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

    private int status;
    private String error;
    private String message;
    private long timestamp;

    public static ExceptionResponse createExceptionResponse(RequestException exception) {
        return new ExceptionResponse(exception.getHttpStatus().value(),
                exception.getHttpStatus().name(),
                exception.getMessage(),
                System.currentTimeMillis());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
