package com.samples.spring.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface BlogPostsSink {

  Stream<BlogPost> findAll();

  default Optional<BlogPost> findById(UUID id) {
    return findAll()
        .filter(bp -> id.equals(bp.getId()))
        .findFirst();
  }
  
  void create(BlogPost newPost);
  
  boolean delete(UUID id);

  default long count() {
    return findAll()
        .count();
  }
}
