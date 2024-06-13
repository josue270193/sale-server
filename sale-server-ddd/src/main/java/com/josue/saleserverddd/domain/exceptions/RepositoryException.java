package com.josue.saleserverddd.domain.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class RepositoryException extends RuntimeException {

    public RepositoryException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatus();

    public Map<String, Object> getDetails() {
        return Map.of();
    }
}
