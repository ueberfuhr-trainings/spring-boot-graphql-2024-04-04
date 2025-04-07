package com.samples.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;

@Controller
public class QlBlogPostsController {

  // ID generation
  // TODO Replace this!
  
  private static long counter = 0;
  
  private static long createId() {
    return counter++;
  }

  // BlogPost storage
  // TODO Replace this!

  private final Map<Long, BlogPost> blogPosts = new ConcurrentHashMap<Long, BlogPost>();

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
    long id
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
    
    // Validierung: title >3 <=100, content >3 <=1000, title<content
    
    
    var blogPost = new BlogPost(
        createId(), 
        input.title(), 
        input.content()
    );
    this
      .blogPosts
      .put(blogPost.id(), blogPost);
    return blogPost;
  }
  
  
}
