package com.samples.spring.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    properties = {
        "application.initialization.enabled=true"
    }
)
// @ActiveProfiles("...")
@AutoConfigureTestDatabase
public class BlogPostInitializationTests {

  @Autowired
  BlogPostsService service;

  // TODO: works also after running the other tests manipulating the service
  // will work correctly when we use a database
  @Test
  void shouldNotBeEmpty() {
    assertThat(service.count())
      .isGreaterThan(0L);
  }
  
}
