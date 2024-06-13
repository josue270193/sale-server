package com.josue.saleserverddd.infrastructure.configuration;

import com.josue.saleserverddd.domain.exceptions.RepositoryException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RepositoryException.class})
    protected ProblemDetail handleRepositoryException(RepositoryException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setTitle(ex.getClass().getSimpleName());
        problemDetail.setProperty("details", ex.getDetails());
        return problemDetail;
    }

}
