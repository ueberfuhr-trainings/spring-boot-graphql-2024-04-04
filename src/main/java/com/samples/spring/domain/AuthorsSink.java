package com.samples.spring.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface AuthorsSink {

  Stream<Author> findAll();

  default Optional<Author> findById(UUID id) {
    return findAll()
        .filter(author -> id.equals(author.getId()))
        .findFirst();
  }
  
  void create(Author newPost);
  
  default boolean existsById(UUID id) {
    return findById(id)
        .isPresent();
  }
}
