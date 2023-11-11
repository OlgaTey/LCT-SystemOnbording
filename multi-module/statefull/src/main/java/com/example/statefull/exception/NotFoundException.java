package com.example.statefull.exception;

public class NotFoundException extends RuntimeException{
    private String msg;

    public NotFoundException(String msg) {
        super(msg);
    }
}
