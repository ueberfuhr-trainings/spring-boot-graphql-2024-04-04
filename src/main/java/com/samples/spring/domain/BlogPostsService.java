package com.samples.spring.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Validated
@Service
public class BlogPostsService {

  private final BlogPostsSink sink;
  
  public BlogPostsService(BlogPostsSink sink) {
    super();
    this.sink = sink;
  }

  public Stream<BlogPost> findAll() {
    return sink.findAll();
  }
  
  public Optional<BlogPost> findById(UUID id) {
    return sink.findById(id);
  }

  public void create(@Valid BlogPost newPost) {
    sink.create(newPost);
  }
  
  public boolean delete(UUID id) {
    return sink.delete(id);
  }
  
  public long count() {
    return sink.count();
  }
  
  
}
