package com.samples.spring.boundary;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.samples.spring.domain.BlogPostsService;

import jakarta.validation.Valid;

@Controller
public class QlBlogPostsController {

  private final BlogPostsService service;
  private final QlBlogPostDtoMapper mapper;
  
  public QlBlogPostsController(
      BlogPostsService service,
      QlBlogPostDtoMapper mapper
  ) {
    super();
    this.service = service;
    this.mapper = mapper;
  }

  // GraphQl implementation
  
  @QueryMapping("findAllBlogPosts")
  public Stream<QlBlogPostDto> findAllBlogPosts() {
      return service
          .findAll()
          .map(mapper::map);
  }
  
  @QueryMapping("findBlogPostById")
  public QlBlogPostDto findBlogPostById(
    @Argument("id")
    UUID id
  ) {
    return service
        .findById(id)
        .map(mapper::map)
        .orElse(null);
  }
  
  @MutationMapping("createBlogPost")
  public QlBlogPostDto createBlogPost(
    @Valid
    @Argument("input")
    QlBlogPostInputDto input
  ) {
    var blogPost = mapper.map(input);
    service.create(blogPost);
    var responseDto = mapper.map(blogPost);
    return responseDto;
  }
  
  @MutationMapping("deleteBlogPost")
  public boolean deleteBlogPost(
    @Argument("id")
    UUID id
      ) {
    if(!service.exists(id)) {
      return false;
    }
    service.delete(id);
    return true;
  }
  
  
}
