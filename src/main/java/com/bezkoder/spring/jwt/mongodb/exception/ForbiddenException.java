package com.bezkoder.spring.jwt.mongodb.exception;

public class ForbiddenException extends Exception {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException() {
        super("Người dùng hiện tại không có quyền.");
    }
}
