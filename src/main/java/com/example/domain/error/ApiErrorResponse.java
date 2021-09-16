package com.example.domain.error;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    private final HttpStatus status;
    private final String message;
    private final List<ApiError> errors;

    public ApiErrorResponse(HttpStatus status, String message) {
        this(status, message, null);
    }
}

