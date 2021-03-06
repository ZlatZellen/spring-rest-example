package com.example.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.example.domain.error.ApiError;
import com.example.domain.error.ApiErrorResponse;
import com.example.domain.error.ApiValidationError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handle(Exception e) {
        return createResponseEntity(new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                "No handler found"));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handle(HttpMediaTypeNotSupportedException e) {
        return createResponseEntity(new ApiErrorResponse(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handle(HttpMessageNotReadableException e) {
        return createResponseEntity(new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Not readable message"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handle(MethodArgumentNotValidException e) {
        List<ApiError> errors = e.getFieldErrors()
                .stream()
                .map(ApiValidationError::new)
                .collect(Collectors.toList());
        return createResponseEntity(new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation failed. Error count: " + e.getFieldErrorCount(),
                errors));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handle(ResourceNotFoundException e) {
        return createResponseEntity(new ApiErrorResponse(
                HttpStatus.NOT_FOUND,
                e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handle(DataIntegrityViolationException e) {
        return createResponseEntity(new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Attempt to save or delete data results in violation constraint"));
    }

    private ResponseEntity<ApiErrorResponse> createResponseEntity(
            ApiErrorResponse apiErrorResponse) {

        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }
}
