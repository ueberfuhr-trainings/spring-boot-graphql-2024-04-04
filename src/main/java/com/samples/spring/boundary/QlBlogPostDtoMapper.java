package com.samples.spring.boundary;

import org.springframework.stereotype.Component;

import com.samples.spring.domain.BlogPost;

// TODO MapStruct
@Component
public class QlBlogPostDtoMapper {

  public QlBlogPostDto map(BlogPost source) {
    if(null == source) {
      return null;
    }
    return new QlBlogPostDto(
        source.getId(), 
        source.getTitle(), 
        source.getContent(), 
        source.getCreated()
    );
  }

  public BlogPost map(QlBlogPostDto source) {
    if(null == source) {
      return null;
    }
    var result = new BlogPost();
    result.setId(source.id());
    result.setTitle(source.title());
    result.setContent(source.content());
    result.setCreated(source.created());
    return result;
  }

  public BlogPost map(QlBlogPostInputDto source) {
    if(null == source) {
      return null;
    }
    var result = new BlogPost();
    result.setTitle(source.title());
    result.setContent(source.content());
    return result;
  }

}
