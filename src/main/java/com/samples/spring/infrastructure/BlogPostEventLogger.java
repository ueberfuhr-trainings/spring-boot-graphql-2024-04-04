package com.samples.spring.infrastructure;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.samples.spring.domain.BlogPostCreatedEvent;
import com.samples.spring.domain.BlogPostDeletedEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BlogPostEventLogger {

  @EventListener
  public void logBlogPostCreated(BlogPostCreatedEvent event) {
    log.info("Blogpost created with id " + event.newPost().getId());
  }

  @EventListener
  public void logBlogPostDeleted(BlogPostDeletedEvent event) {
    log.info("Blogpost deleted with id " + event.id());
  }

}
