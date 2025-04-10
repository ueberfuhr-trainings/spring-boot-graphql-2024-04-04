package com.samples.spring.persistence.memory;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import com.samples.spring.domain.BlogPost;
import com.samples.spring.domain.BlogPostsSink;

class InMemoryBlogPostsSink
  implements BlogPostsSink{

  private final Map<UUID, BlogPost> blogPosts = new ConcurrentHashMap<>();

  @Override
  public Stream<BlogPost> findAll() {
    return this
        .blogPosts
        .values()
        .stream();
  }

  @Override
  public Optional<BlogPost> findById(UUID id) {
    return Optional.ofNullable(
        this
          .blogPosts
          .get(id)
      );
  }

  @Override
  public void create(BlogPost newPost) {
    newPost.setId(UUID.randomUUID());
    newPost.setCreated(ZonedDateTime.now());
    this
      .blogPosts
      .put(newPost.getId(), newPost);
  }

  @Override
  public boolean delete(UUID id) {
    return this
        .blogPosts
        .remove(id) != null;
  }

  @Override
  public long count() {
    return this
        .blogPosts
        .size();
  }

  @Override
  public boolean existsById(UUID id) {
    return this
        .blogPosts
        .containsKey(id);
  }
  
}
