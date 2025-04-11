package com.samples.spring.persistence.jpa;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.samples.spring.domain.BlogPost;
import com.samples.spring.domain.BlogPostsSink;

class JpaBlogPostsSink
  implements BlogPostsSink {

  private final BlogPostEntityRepository repo;
  private final BlogPostEntityMapper mapper;
  
  public JpaBlogPostsSink(
      BlogPostEntityRepository repo, 
      BlogPostEntityMapper mapper
  ) {
    super();
    this.repo = repo;
    this.mapper = mapper;
  }

  @Override
  public Stream<BlogPost> findAll() {
    return repo
        .findAll()
        .stream()
        .map(mapper::map);
  }

  @Override
  public Optional<BlogPost> findById(UUID id) {
    return repo
        .findById(id)
        .map(mapper::map);
  }

  @Override
  public void create(BlogPost newPost) {
    var entity = mapper.map(newPost);
    repo.save(entity);
    mapper.copy(entity, newPost);
  }

  @Override
  public boolean delete(UUID id) {
    if(!repo.existsById(id)) {
      return false;
    }
    repo
      .deleteById(id);
    return true;
  }

  @Override
  public long count() {
    return repo
        .count();
  }

  @Override
  public boolean existsById(UUID id) {
    return repo
        .existsById(id);
  }

}
