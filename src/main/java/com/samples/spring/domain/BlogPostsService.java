package com.samples.spring.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Validated
@Service
public class BlogPostsService {

  private final ApplicationEventPublisher eventPublisher;
  private final BlogPostsSink sink;
  
  public BlogPostsService(ApplicationEventPublisher eventPublisher, BlogPostsSink sink, BlogPostsSink inMemoryBlogPostsSink) {
    super();
    this.eventPublisher = eventPublisher;
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
    eventPublisher
      .publishEvent(new BlogPostCreatedEvent(newPost));
  }
  
  public boolean delete(UUID id) {
    final var success = sink.delete(id);
    if(success) {
      eventPublisher
        .publishEvent(new BlogPostDeletedEvent(id));
    }
    return success;
  }
  
  public long count() {
    return sink.count();
  }
  
  
}
