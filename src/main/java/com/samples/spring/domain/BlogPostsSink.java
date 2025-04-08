package com.samples.spring.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface BlogPostsSink {

  Stream<BlogPost> findAll(BlogPostOptions options);

  default Optional<BlogPost> findById(
      UUID id
  ) {
    return findAll(BlogPostOptions.builder().build())
        .filter(bp -> id.equals(bp.getId()))
        .findFirst();
  }
  
  void create(BlogPost newPost);
  
  void update(BlogPost blogPost);

  boolean delete(UUID id);

  default long count() {
    return findAll(BlogPostOptions.builder().build())
        .count();
  }

  default boolean existsById(UUID id) {
    return findById(id)
        .isPresent();
  }

}
