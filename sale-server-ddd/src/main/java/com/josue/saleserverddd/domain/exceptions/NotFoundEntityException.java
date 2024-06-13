package com.josue.saleserverddd.domain.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class NotFoundEntityException extends RepositoryException {

    private static final String DEFAULT_MESSAGE = "Not found Entity: %s";
    private final String entity;
    private final Map<String, Object> params;

    public NotFoundEntityException(String entity) {
        super(String.format(DEFAULT_MESSAGE, entity));
        this.params = Map.of();
        this.entity = entity;
    }

    public NotFoundEntityException(String entity, Map<String, Object> params) {
        super(String.format(DEFAULT_MESSAGE, entity));
        this.params = params;
        this.entity = entity;
    }

    public NotFoundEntityException(String message, String entity, Map<String, Object> params) {
        super(String.format(message, entity));
        this.params = params;
        this.entity = entity;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public Map<String, Object> getDetails() {
        return this.params;
    }

}
