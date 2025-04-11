package com.samples.spring.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.samples.spring.domain.BlogPost;

@Mapper(
    componentModel = "spring",
    uses = AuthorEntityMapper.class
)
public interface BlogPostEntityMapper {

  BlogPostEntity map (BlogPost source);

  BlogPost map (BlogPostEntity source);

  void copy(BlogPostEntity source, @MappingTarget BlogPost target);
  
}
