package com.samples.spring;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import graphql.GraphQLError;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @GraphQlExceptionHandler
  GraphQLError handleValidationExeptions(ConstraintViolationException ex) {
    return GraphQLError
        .newError()
        .errorType(ErrorType.BAD_REQUEST)
        .message(ex.getMessage())
        .build();
  }
  
}
