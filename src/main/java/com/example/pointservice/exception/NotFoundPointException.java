package com.example.pointservice.exception;

public class NotFoundPointException extends RuntimeException{
    public NotFoundPointException() {
        super();
    }

    public NotFoundPointException(String message) {
        super(message);
    }

    public NotFoundPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundPointException(Throwable cause) {
        super(cause);
    }

    protected NotFoundPointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
