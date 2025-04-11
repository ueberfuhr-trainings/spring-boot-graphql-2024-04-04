package com.samples.spring.persistence.memory;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import com.samples.spring.domain.Author;
import com.samples.spring.domain.AuthorsSink;

class InMemoryAuthorsSink
  implements AuthorsSink{

  private final Map<UUID, Author> authors = new ConcurrentHashMap<>();

  @Override
  public Stream<Author> findAll() {
    return this
        .authors
        .values()
        .stream();
  }

  @Override
  public Optional<Author> findById(UUID id) {
    return Optional.ofNullable(
        this
          .authors
          .get(id)
      );
  }

  @Override
  public void create(Author newPost) {
    newPost.setId(UUID.randomUUID());
    this
      .authors
      .put(newPost.getId(), newPost);
  }

  @Override
  public boolean existsById(UUID id) {
    return this
        .authors
        .containsKey(id);
  }
  
}
