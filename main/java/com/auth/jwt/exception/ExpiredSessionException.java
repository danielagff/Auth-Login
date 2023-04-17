package com.auth.jwt.exception;

public class ExpiredSessionException extends RuntimeException {
    public ExpiredSessionException(String message) {
        super(message);
    }
}
