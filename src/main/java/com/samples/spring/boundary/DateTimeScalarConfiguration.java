package com.samples.spring.boundary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.scalars.ExtendedScalars;

@Configuration
public class DateTimeScalarConfiguration {

  @Bean
  RuntimeWiringConfigurer registerDateTimeScalar() {
    return builder -> builder
      .scalar(ExtendedScalars.DateTime);
  }

}
