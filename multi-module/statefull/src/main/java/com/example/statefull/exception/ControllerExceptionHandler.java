package com.example.statefull.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({
            NotFoundException.class
    })
    public ResponseEntity<ApiError> handleError(NotFoundException e) {
        return sendResponse(new ApiError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            RuntimeException.class
    })
    public ResponseEntity<ApiError> handleRunTimeException(RuntimeException e) {
        return sendResponse(new ApiError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<ApiError> sendResponse(ApiError body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleRunTimeException(MethodArgumentNotValidException e) {
        return sendResponse(new ApiError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
