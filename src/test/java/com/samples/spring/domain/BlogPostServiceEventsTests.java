package com.samples.spring.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

@SpringBootTest
@RecordApplicationEvents
public class BlogPostServiceEventsTests {

  @Autowired
  BlogPostsService service;
  @Autowired
  ApplicationEvents events;
  
  
  @Test
  void shouldLogOnBlogPostCreated() {
    var blogPost = new BlogPost();
    blogPost.setTitle("Test-Title");
    blogPost.setContent("Test-Content");
    service.create(blogPost);
    
    // Prüfe, ob Event ausgelöst wurde
    assertThat(events.stream(BlogPostCreatedEvent.class))
      .last()
      .extracting(BlogPostCreatedEvent::newPost)
      .isSameAs(blogPost);
    
  }
  
}
