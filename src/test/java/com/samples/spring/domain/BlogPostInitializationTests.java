package com.samples.spring.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogPostInitializationTests {

  @Autowired
  BlogPostsService service;
  
  @Test
  void shouldNotBeEmpty() {
    assertThat(service.count())
      .isGreaterThan(0L);
  }
  
}
