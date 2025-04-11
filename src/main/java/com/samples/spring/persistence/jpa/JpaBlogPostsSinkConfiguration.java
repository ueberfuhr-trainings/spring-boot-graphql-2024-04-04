package com.samples.spring.persistence.jpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.samples.spring.domain.AuthorsSink;
import com.samples.spring.domain.BlogPostsSink;

import jakarta.persistence.EntityManager;

@Configuration
// @ConditionalOnBean(BlogPostEntityRepository.class)
public class JpaBlogPostsSinkConfiguration {

  @Bean
  BlogPostsSink jpaBlogPostsSink(
      BlogPostEntityRepository repo, 
      BlogPostEntityMapper mapper,
      JpaEntityGraphQueryBuilder queryBuilder
  ) {
    return new JpaBlogPostsSink(repo, mapper, queryBuilder);
  }
  
  @Bean
  AuthorsSink jpaAuthorsSink(
      AuthorEntityRepository repo,
      AuthorEntityMapper mapper
  ) {
    return new JpaAuthorsSink(repo, mapper);
  }
  
  @Bean
  JpaEntityGraphQueryBuilder jpaEntityGraphQueryBuilder(
      EntityManager em
  ) {
    return new JpaEntityGraphQueryBuilder(em);
  }
  
}
