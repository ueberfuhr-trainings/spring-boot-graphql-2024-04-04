package com.samples.spring.persistence.jpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.samples.spring.domain.BlogPostsSink;

@Configuration
// @ConditionalOnBean(BlogPostEntityRepository.class)
public class JpaBlogPostsSinkConfiguration {

  @Bean
  BlogPostsSink jpaBlogPostsSink(
      BlogPostEntityRepository repo, 
      BlogPostEntityMapper mapper
  ) {
    return new JpaBlogPostsSink(repo, mapper);
  }
  
}
