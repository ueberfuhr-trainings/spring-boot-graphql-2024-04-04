package com.samples.spring.boundary;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Locale;
import java.util.UUID;

@Configuration
public class UuidScalarConfiguration {

  @Bean
  RuntimeWiringConfigurer registerUuidScalar() {
    return builder -> builder
      .scalar(
        GraphQLScalarType
          .newScalar()
          .name("UUID")
          .description("Java UUID scalar")
          .coercing(new UuidScalar())
          .build()
      );
  }

  private static class UuidScalar implements Coercing<UUID, String> {

    @Override
    public String serialize(
      Object dataFetcherResult,
      GraphQLContext graphQLContext,
      Locale locale
    ) throws CoercingSerializeException {
      if (dataFetcherResult instanceof UUID uuid) {
        return uuid.toString();
      } else {
        throw new CoercingSerializeException("Expected a UUID object.");
      }
    }

    @Override
    public UUID parseValue(
      Object input,
      GraphQLContext graphQLContext,
      Locale locale
    ) throws CoercingParseValueException {
      try {
        if (input instanceof String s) {
          return UUID.fromString(s);
        } else {
          throw new CoercingParseValueException("Expected a String");
        }
      } catch (IllegalArgumentException e) {
        throw new CoercingParseValueException(String.format("Not a uuid: '%s'.", input), e);
      }
    }

    @Override
    public UUID parseLiteral(
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
