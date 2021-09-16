package com.example.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Object id) {
        this("Unable to find " + resource + " with id " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
