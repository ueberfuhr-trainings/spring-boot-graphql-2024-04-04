package com.samples.spring;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;

@Controller
public class QlBlogPostsController {

  // BlogPost storage
  // TODO Replace this!

  private final Map<UUID, BlogPost> blogPosts = new ConcurrentHashMap<>();

  // BlogPost sample data
  // TODO Replace this!

  {
    createBlogPost(
      new BlogPostInput(
        "My First BlogPost", 
        "This is my first blog post."
      )
    );
    createBlogPost(
        new BlogPostInput(
          "My Second BlogPost", 
          "This is my second blog post."
        )
      );
  }
  
  // GraphQl implementation
  
  @QueryMapping("findAllBlogPosts")
  public Stream<BlogPost> findAllBlogPosts() {
      return this
          .blogPosts
          .values()
          .stream();
  }
  
  @QueryMapping("findBlogPostById")
  public BlogPost findBlogPostById(
    @Argument("id")
    UUID id
  ) {
    return this
        .blogPosts
        .get(id);
  }
  
  @MutationMapping("createBlogPost")
  public BlogPost createBlogPost(
    @Valid
    @Argument("input")
    BlogPostInput input
  ) {
    
    var blogPost = new BlogPost(
        UUID.randomUUID(), 
        input.title(), 
        input.content()
    );
    this
      .blogPosts
      .put(blogPost.id(), blogPost);
    return blogPost;
  }
  
  @MutationMapping("deleteBlogPost")
  public boolean deleteBlogPost(
    @Argument("id")
    UUID id
      ) {
    return this
        .blogPosts
        .remove(id) != null;
  }
  
  
}
