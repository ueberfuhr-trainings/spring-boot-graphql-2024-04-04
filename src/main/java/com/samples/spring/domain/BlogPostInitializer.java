package com.samples.spring.domain;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@ConditionalOnProperty(
    name = "application.initialization.enabled",
    havingValue = "true",
    matchIfMissing = true
)
// @Profile("local")
@RequiredArgsConstructor
public class BlogPostInitializer {

  private final BlogPostsService service;

  @EventListener(ApplicationReadyEvent.class)
  public void initializeFirstBlogPost() {
    if(service.count()==0) {
      service.create(
          BlogPost
            .builder()
            .title("Welcome!")
            .content("This is my blog.")
            .build()
      );
    }
  }
  
}
