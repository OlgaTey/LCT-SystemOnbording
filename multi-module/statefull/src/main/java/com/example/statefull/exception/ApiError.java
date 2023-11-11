package com.example.statefull.exception;

import lombok.Getter;

@Getter
public class ApiError {
    private String errorMessage;

    public ApiError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
