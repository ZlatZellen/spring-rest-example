package com.example.domain.error;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class ApiValidationError extends ApiError {
    private final String field;

    public ApiValidationError(FieldError fieldError) {
        super(fieldError.getDefaultMessage());
        this.field = fieldError.getField();
    }
}
