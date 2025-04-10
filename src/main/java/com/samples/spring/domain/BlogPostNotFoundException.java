package com.samples.spring.domain;

import java.util.UUID;

@SuppressWarnings("serial")
public class BlogPostNotFoundException extends RuntimeException {

  private final UUID id;

  public BlogPostNotFoundException(UUID id) {
    super();
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
  
}
