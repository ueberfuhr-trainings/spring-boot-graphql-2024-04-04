package com.samples.spring;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Validated
@Service
public class BlogPostsService {

  // BlogPost storage
  // TODO Replace this!

  private final Map<UUID, BlogPost> blogPosts = new ConcurrentHashMap<>();

  // BlogPost sample data
  // TODO Replace this!

  {
    create(
      new BlogPostInput(
        "My First BlogPost", 
        "This is my first blog post."
      )
    );
    create(
        new BlogPostInput(
          "My Second BlogPost", 
          "This is my second blog post."
        )
      );
  }
  
  public Stream<BlogPost> findAll() {
    return this
        .blogPosts
        .values()
        .stream();
  }
  
  public Optional<BlogPost> findById(UUID id) {
    return Optional.ofNullable(
        this
          .blogPosts
          .get(id)
      );
  }
  
  public BlogPost create(@Valid BlogPostInput newPost) {
    var blogPost = new BlogPost(
        UUID.randomUUID(), 
        newPost.title(), 
        newPost.content(),
        LocalDateTime.now()
    );
    this
      .blogPosts
      .put(blogPost.id(), blogPost);
    return blogPost;
  }
  
  public boolean delete(UUID id) {
    return this
        .blogPosts
        .remove(id) != null;
  }
  
  
}
