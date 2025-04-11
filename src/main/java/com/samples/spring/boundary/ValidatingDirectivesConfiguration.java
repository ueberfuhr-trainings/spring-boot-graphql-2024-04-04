package com.samples.spring.boundary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ValidatingDirectivesConfiguration {

  private final CustomMessageInterpolator messageInterpolator;

  @Bean
  public RuntimeWiringConfigurer validatingDirectives() {
    final var validationRules = ValidationRules
      .newValidationRules()
      .onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
      .messageInterpolator(messageInterpolator)
      .build();
    final var schemaWiring = new ValidationSchemaWiring(validationRules);
    return builder -> builder
      .directiveWiring(schemaWiring)
      .build();
  }


}
