package com.samples.spring.infrastructure;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.samples.spring.domain.BlogPost;
import com.samples.spring.domain.BlogPostsService;

// Preferred: Check the logs
// here: mock the logger

@SpringBootTest
public class BlogPostEventLoggerTests {

  @Autowired
  BlogPostsService service;
  @MockitoBean
  BlogPostEventLogger eventLogger;
  
  @Test
  void shouldLogOnBlogPostCreated() {
    reset(eventLogger);
    
    var blogPost = new BlogPost();
    blogPost.setTitle("Test-Title");
    blogPost.setContent("Test-Content");
    service.create(blogPost);
    
    // Prüfe, ob im Event der BlogPost enthalten ist
    verify(eventLogger)
      .logBlogPostCreated(ArgumentMatchers.any());
    
  }
  
}
