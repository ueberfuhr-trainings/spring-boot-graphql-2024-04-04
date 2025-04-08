package com.samples.spring;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;

@Controller
public class QlBlogPostsController {

  private final BlogPostsService service;
  
  public QlBlogPostsController(BlogPostsService service) {
    super();
    this.service = service;
  }

  // GraphQl implementation
  
  @QueryMapping("findAllBlogPosts")
  public Stream<BlogPost> findAllBlogPosts() {
      return service.findAll();
  }
  
  @QueryMapping("findBlogPostById")
  public BlogPost findBlogPostById(
    @Argument("id")
    UUID id
  ) {
    return service
        .findById(id)
        .orElse(null);
  }
  
  @MutationMapping("createBlogPost")
  public BlogPost createBlogPost(
    @Valid
    @Argument("input")
    BlogPostInput input
  ) {
    return service.create(input);
  }
  
  @MutationMapping("deleteBlogPost")
  public boolean deleteBlogPost(
    @Argument("id")
    UUID id
      ) {
    return service.delete(id);
  }
  
  
}
