package com.railvayticketiffice.data.responses;

public class BaseResponse {

    public BaseResponse(){}

    public BaseResponse(boolean succeeded, String message) {
        this.succeeded = succeeded;
        this.message = message;
    }

    private boolean succeeded;

    private String message;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
