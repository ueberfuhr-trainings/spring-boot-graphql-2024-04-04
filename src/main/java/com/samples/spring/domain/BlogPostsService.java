package com.samples.spring.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.samples.spring.shared.events.PublishEvent;

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

  @PublishEvent(BlogPostCreatedEvent.class)
  public void create(@Valid BlogPost newPost) {
    sink.create(newPost);
  }

  public boolean exists(UUID id) {
    return sink.existsById(id);
  }

  @PublishEvent(BlogPostDeletedEvent.class)
  public void delete(UUID id) {
    if(!sink.delete(id)) {
      throw new BlogPostNotFoundException(id);
    }
  }
  
  public long count() {
    return sink.count();
  }
  
}
