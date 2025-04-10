# Spring Sample with GraphQl and JPA

[![Continuous Integration Build](https://github.com/ueberfuhr-trainings/spring-boot-graphql-2024-04-04/actions/workflows/maven.yml/badge.svg)](https://github.com/ueberfuhr-trainings/spring-boot-graphql-2024-04-04/actions/workflows/maven.yml)

We can find some documentation [here](docs/README.md).

To build and run the code, we could use these commands:

```bash
# directly run from sources
mvn spring-boot:run
# build and run the JAR
mvn clean package
java -jar target/spring-sample-0.0.1-SNAPSHOT.jar
```

## GraphiQL

To enable GraphiQL UI for our application, we need to do one of the following configurations:

- Specify an environment variable `GRAPHIQL_ENABLED` with a value of `true`
- Run the application with `-Dspring.graphql.graphiql.enabled` with a value of `true`
- Run the application with `-Dspring.profiles.active=local` (for local environments)