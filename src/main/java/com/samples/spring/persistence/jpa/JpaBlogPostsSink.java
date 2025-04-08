package com.samples.spring.persistence.jpa;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.samples.spring.domain.BlogPost;
import com.samples.spring.domain.BlogPostOptions;
import com.samples.spring.domain.BlogPostsSink;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class JpaBlogPostsSink
  implements BlogPostsSink {

  private final BlogPostEntityRepository repo;
  private final BlogPostEntityMapper mapper;
  private final JpaEntityGraphQueryBuilder queryBuilder;
  
  @Override
  public Stream<BlogPost> findAll(BlogPostOptions options) {
    // We build the EntityGraph dynamically here,
    // so we could not use the repository pattern.
    // otherwise, we could use: https://github.com/Cosium/spring-data-jpa-entity-graph
    return queryBuilder
        .findAllQuery(options)
        .getResultList()
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
  public void update(BlogPost blogPost) {
    var entity = mapper.map(blogPost);
    repo.save(entity);
    mapper.copy(entity, blogPost);
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
