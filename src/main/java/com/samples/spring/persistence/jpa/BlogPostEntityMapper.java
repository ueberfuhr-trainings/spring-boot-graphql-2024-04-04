package com.samples.spring.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.samples.spring.domain.BlogPost;

@Mapper(
    componentModel = "spring",
    uses = AuthorEntityMapper.class
)
public interface BlogPostEntityMapper {

  BlogPostEntity map (BlogPost source);

  // do not copy the author, if not loaded
  @Mapping(
      target = "author",
      conditionExpression = "java(org.hibernate.Hibernate.isInitialized(source.getAuthor()))"
  )
  BlogPost map (BlogPostEntity source);

  // do not copy the author, if not loaded
  @Mapping(
      target = "author",
      conditionExpression = "java(org.hibernate.Hibernate.isInitialized(source.getAuthor()))"
  )
  void copy(BlogPostEntity source, @MappingTarget BlogPost target);
  
}
