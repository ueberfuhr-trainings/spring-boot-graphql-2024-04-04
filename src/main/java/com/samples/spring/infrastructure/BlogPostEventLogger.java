package com.samples.spring.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.samples.spring.domain.BlogPostCreatedEvent;

@Component
public class BlogPostEventLogger {

  private static final Logger logger = LoggerFactory.getLogger(BlogPostEventLogger.class);

  @EventListener
  public void logBlogPostCreated(BlogPostCreatedEvent event) {
    logger.info("Blogpost created with id " + event.newPost().getId());
  }
  
}
