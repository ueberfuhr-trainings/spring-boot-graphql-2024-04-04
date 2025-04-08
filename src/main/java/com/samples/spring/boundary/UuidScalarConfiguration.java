package com.samples.spring.boundary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.scalars.ExtendedScalars;

@Configuration
public class UuidScalarConfiguration {

  @Bean
  RuntimeWiringConfigurer registerUuidScalar() {
    return builder -> builder
      .scalar(ExtendedScalars.UUID);
  }

}
