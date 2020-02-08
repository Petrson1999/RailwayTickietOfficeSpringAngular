package com.railvayticketiffice.security;

public enum JwtType {
    ACCESS("access"),
    REGISTRATION("registration");

    private String stringValue;

    JwtType(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
