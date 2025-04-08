package com.samples.spring.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.samples.spring.shared.events.PublishEvent;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor
public class BlogPostsService {

  private final BlogPostsSink sink;
  
  public Stream<BlogPost> findAll() {
    return this.findAll(BlogPostOptions.builder().build());
  }

  public Stream<BlogPost> findAll(BlogPostOptions options) {
    return sink.findAll(options);
  }

  public Optional<BlogPost> findById(UUID id) {
    return sink.findById(id);
  }

  @PublishEvent(BlogPostCreatedEvent.class)
  public void create(@Valid BlogPost newPost) {
    sink.create(newPost);
  }

  @PublishEvent(BlogPostUpdatedEvent.class)
  public void update(@Valid BlogPost blogPost) {
    if(!exists(blogPost.getId())) {
      throw new BlogPostNotFoundException(blogPost.getId());
    }
    sink.update(blogPost);    
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
