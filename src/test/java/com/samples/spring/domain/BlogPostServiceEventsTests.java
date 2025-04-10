package com.samples.spring.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

@SpringBootTest
@RecordApplicationEvents
class BlogPostServiceEventsTests {

  @Autowired
  BlogPostsService service;
  @Autowired
  ApplicationEvents events;
  
  @BeforeEach
  void setup() {
    events.clear();
  }
  
  @Test
  void shouldLogOnBlogPostCreated() {
    var blogPost = new BlogPost();
    blogPost.setTitle("Test-Title");
    blogPost.setContent("Test-Content");
    service.create(blogPost);
    
    // Prüfe, ob Event ausgelöst wurde
    assertThat(events.stream(BlogPostCreatedEvent.class))
      .filteredOn(event -> event.newPost() == blogPost)
      .hasSize(1);
    
  }

  @Test
  void shouldLogOnBlogPostDeleted() {
    var blogPost = new BlogPost();
    blogPost.setTitle("Test-Title");
    blogPost.setContent("Test-Content");
    service.create(blogPost);
    
    service.delete(blogPost.getId());
    // Prüfe, ob Event ausgelöst wurde
    assertThat(events.stream(BlogPostDeletedEvent.class))
      .filteredOn(event -> event.id().equals(blogPost.getId()))
      .hasSize(1);

    events.clear();

    // Erneutes Löschen
    service.delete(blogPost.getId());
    // Prüfe, das Event NICHT ausgelöst wurde
    assertThat(events.stream(BlogPostDeletedEvent.class))
      .filteredOn(event -> event.id().equals(blogPost.getId()))
      .isEmpty();

  }

}
