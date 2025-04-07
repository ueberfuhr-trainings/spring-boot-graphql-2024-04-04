package com.samples.spring;

import java.util.stream.Stream;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class QlBlogPostsController {

  @QueryMapping("findAllBlogPosts")
  public Stream<BlogPost> findAllBlogPosts() {
      return Stream.of(
          new BlogPost(
              1L, 
              "My First BlogPost", 
              "This is my first blog post."
          )
      );
  }
  
}
