package com.samples.spring.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ValidationException;

@SpringBootTest
public class BlogPostsServiceTests {

  @Autowired
  BlogPostsService service;
  
  @Test
  void shouldCreateBlogPost() {
    var post = BlogPost.valueOf("Test", "Test-Content");
    service.create(post);
    assertThat(post.getId())
      .isNotNull();
    var findResult = service.findById(post.getId());
    assertThat(findResult)
      .isPresent();
  }
  
  @Test
  void shouldValidateBlogPost() {
    var post = BlogPost.valueOf("T", "Test-Content");
    assertThatThrownBy(() -> service.create(post))
      .isInstanceOf(ValidationException.class);
  }
  
}
