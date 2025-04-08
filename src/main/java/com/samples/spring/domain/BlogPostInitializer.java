package com.samples.spring.domain;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BlogPostInitializer {

  private final BlogPostsService service;

  public BlogPostInitializer(BlogPostsService service) {
    super();
    this.service = service;
  }
  
  @EventListener(ApplicationReadyEvent.class)
  public void initializeFirstBlogPost() {
    if(service.count()==0) {
      service.create(
          BlogPost.valueOf(
              "Welcome!", 
              "This is my blog."
          )
      );
    }
  }
  
}
