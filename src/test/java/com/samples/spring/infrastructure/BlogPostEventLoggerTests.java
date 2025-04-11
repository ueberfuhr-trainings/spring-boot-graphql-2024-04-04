package com.samples.spring.infrastructure;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.samples.spring.domain.BlogPost;
import com.samples.spring.domain.BlogPostsService;

// Preferred: Check the logs
// here: mock the logger

@SpringBootTest
@AutoConfigureTestDatabase
class BlogPostEventLoggerTests {

  @Autowired
  BlogPostsService service;
  @MockitoBean
  BlogPostEventLogger eventLogger;
  
  @Test
  void shouldLogOnBlogPostCreated() {
    reset(eventLogger);
    
    var blogPost = BlogPost
        .builder()
        .title("Test-Title")
        .content("Test-Content")
        .build();
    service.create(blogPost);
    
    // Prüfe, ob im Event der BlogPost enthalten ist
    verify(eventLogger)
      .logBlogPostCreated(
        argThat(event -> event.newPost() == blogPost)
      );
    
  }

  @Test
  void shouldLogOnBlogPostDeleted() {
    
    var blogPost = BlogPost
        .builder()
        .title("Test-Title")
        .content("Test-Content")
        .build();
    service.create(blogPost);

    reset(eventLogger);
    
    service.delete(blogPost.getId());

    // Prüfe, ob im Event der BlogPost enthalten ist
    verify(eventLogger)
      .logBlogPostDeleted(
        argThat(event -> event.id().equals(blogPost.getId()))
      );
    
  }

}
