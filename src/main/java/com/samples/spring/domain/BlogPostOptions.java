package com.samples.spring.domain;

public class BlogPostOptions {
  
  private boolean author = true;
  
  public BlogPostOptions withAuthor(boolean author) {
    this.author = author;
    return this;
  }

  public boolean isAuthor() {
    return author;
  }

}
