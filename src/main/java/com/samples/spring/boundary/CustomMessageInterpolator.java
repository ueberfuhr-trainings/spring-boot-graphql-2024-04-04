package com.samples.spring.boundary;

import graphql.GraphQLError;
import graphql.validation.interpolation.MessageInterpolator;
import graphql.validation.rules.ValidationEnvironment;

import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CustomMessageInterpolator implements MessageInterpolator {

  @Override
  public GraphQLError interpolate(
    String message,
    Map<String, Object> map,
    ValidationEnvironment validationEnvironment
  ) {

    var location = validationEnvironment.getLocation();
    var argumentName = validationEnvironment.getArgument().getName();
    var messageWithArgumentName = String.format(message, argumentName);
    return GraphQLError
        .newError()
        .errorType(ErrorType.BAD_REQUEST)
        .locations(List.of(location))
        .message(messageWithArgumentName)
        .build();

  }
}
