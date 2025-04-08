package com.samples.spring;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Configuration
public class DateTimeScalarConfiguration {

  @Bean
  RuntimeWiringConfigurer registerDateTimeScalar() {
    return builder -> builder
      .scalar(
        GraphQLScalarType
          .newScalar()
          .name("DateTime")
          .description("Java LocalDateTime scalar")
          .coercing(new DateTimeScalar())
          .build()
      );
  }

  private static class DateTimeScalar implements Coercing<LocalDateTime, String> {

    @Override
    public String serialize(
      Object dataFetcherResult,
      GraphQLContext graphQLContext,
      Locale locale
    ) throws CoercingSerializeException {
      if (dataFetcherResult instanceof LocalDateTime date) {
        return date.toString(); // ISO-8601 format yyyy-MM-dd
      } else {
        throw new CoercingSerializeException("Expected a LocalDateTime object.");
      }
    }

    @Override
    public LocalDateTime parseValue(
      Object input,
      GraphQLContext graphQLContext,
      Locale locale
    ) throws CoercingParseValueException {
      try {
        if (input instanceof String s) {
          return LocalDateTime.parse(s);
        } else {
          throw new CoercingParseValueException("Expected a String");
        }
      } catch (DateTimeParseException e) {
        throw new CoercingParseValueException(String.format("Not a valid date time: '%s'.", input), e);
      }
    }

    @Override
    public LocalDateTime parseLiteral(
      Value<?> input,
      CoercedVariables variables,
      GraphQLContext graphQLContext,
      Locale locale
    ) throws CoercingParseLiteralException {
      if (input instanceof StringValue s) {
        return parseValue(s.getValue(), graphQLContext, locale);
      } else {
        throw new CoercingParseLiteralException("Expected a StringValue.");
      }
    }

  }

}
