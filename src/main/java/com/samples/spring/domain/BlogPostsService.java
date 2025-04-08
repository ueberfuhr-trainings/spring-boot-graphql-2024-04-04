package com.samples.spring.domain;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Validated
@Service
public class BlogPostsService {

  // BlogPost storage
  // TODO Replace this!

  private final Map<UUID, BlogPost> blogPosts = new ConcurrentHashMap<>();

  // BlogPost sample data
  // TODO Replace this!

  {
    create(
      BlogPost.valueOf(
        "My First BlogPost", 
        "This is my first blog post."
      )
    );
    create(
      BlogPost.valueOf(
        "My Second BlogPost", 
        "This is my second blog post."
      )
    );
  }
  
  public Stream<BlogPost> findAll() {
    return this
        .blogPosts
        .values()
        .stream();
  }
  
  public Optional<BlogPost> findById(UUID id) {
    return Optional.ofNullable(
        this
          .blogPosts
          .get(id)
      );
  }
  
  public void create(@Valid BlogPost newPost) {
    newPost.setId(UUID.randomUUID());
    newPost.setCreated(ZonedDateTime.now());
    this
      .blogPosts
      .put(newPost.getId(), newPost);
  }
  
  public boolean delete(UUID id) {
    return this
        .blogPosts
        .remove(id) != null;
  }
  
  public long count() {
    return this
        .blogPosts
        .size();
  }
  
  
}
