package com.samples.spring.persistence.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.samples.spring.domain.AuthorsSink;
import com.samples.spring.domain.BlogPostsSink;

@Configuration
public class InMemoryBlogPostsSinkConfiguration {

  @ConditionalOnMissingBean
  @Bean
  BlogPostsSink inMemoryBlogPostsSink() {
    return new InMemoryBlogPostsSink();
  }
  
  @ConditionalOnMissingBean
  @Bean
  AuthorsSink inMemoryAuthorsSink() {
    return new InMemoryAuthorsSink();
  }
  
}
