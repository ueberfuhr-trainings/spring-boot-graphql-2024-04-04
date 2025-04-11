package com.samples.spring.boundary;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.samples.spring.domain.BlogPost;

@Mapper(
    componentModel = "spring",
    uses = QlAuthorDtoMapper.class
)
public interface QlBlogPostDtoMapper {

  QlBlogPostDto map(BlogPost source);

  BlogPost map(QlBlogPostDto source);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  BlogPost map(QlBlogPostInputDto source);

}
