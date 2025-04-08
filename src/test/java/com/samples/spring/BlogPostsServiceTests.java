package com.samples.spring;

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
    var input = new BlogPostInput("Test", "Test-Content");
    var post = service.create(input);
    assertThat(post.id())
      .isNotNull();
    var findResult = service.findById(post.id());
    assertThat(findResult)
      .isPresent();
  }
  
  @Test
  void shouldValidateBlogPost() {
    var input = new BlogPostInput("T", "Test-Content");
    assertThatThrownBy(() -> service.create(input))
      .isInstanceOf(ValidationException.class);
  }
  
}
