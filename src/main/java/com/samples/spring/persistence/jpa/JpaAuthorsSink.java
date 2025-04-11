package com.samples.spring.persistence.jpa;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.samples.spring.domain.Author;
import com.samples.spring.domain.AuthorsSink;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class JpaAuthorsSink
  implements AuthorsSink {

  private final AuthorEntityRepository repo;
  private final AuthorEntityMapper mapper;
  
  @Override
  public Stream<Author> findAll() {
    return repo
        .findAll()
        .stream()
        .map(mapper::map);
  }

  @Override
  public Optional<Author> findById(UUID id) {
    return repo
        .findById(id)
        .map(mapper::map);
  }

  @Override
  public void create(Author newPost) {
    var entity = mapper.map(newPost);
    repo.save(entity);
    mapper.copy(entity, newPost);
  }

  @Override
  public boolean existsById(UUID id) {
    return repo
        .existsById(id);
  }

}
