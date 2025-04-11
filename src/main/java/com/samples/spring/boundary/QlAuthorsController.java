package com.samples.spring.boundary;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.samples.spring.domain.AuthorsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QlAuthorsController {

  private final AuthorsService service;
  private final QlAuthorDtoMapper mapper;

  @QueryMapping("findAllAuthors")
  public Stream<QlAuthorDto> findAllAuthors() {
      return service
          .findAll()
          .map(mapper::map);
  }
  
  @QueryMapping("findAuthorById")
  public QlAuthorDto findAuthorById(
    @Argument("id")
    UUID id
  ) {
    return service
        .findById(id)
        .map(mapper::map)
        .orElse(null);
  }
  
  @MutationMapping("createAuthor")
  public QlAuthorDto createAuthor(
    @Valid
    @Argument("input")
    QlAuthorInputDto input
  ) {
    var author = mapper.map(input);
    service.create(author);
    var responseDto = mapper.map(author);
    return responseDto;
  }
  
}
