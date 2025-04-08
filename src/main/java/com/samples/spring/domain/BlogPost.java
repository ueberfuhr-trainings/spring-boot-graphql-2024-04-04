package com.samples.spring.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BlogPost {

  private UUID id;
  @NotNull
  @Size(min = 4, max = 100)
  private String title;
  @NotNull
  @Size(min = 4, max = 1000)
  private String content;
  private ZonedDateTime created;

  public static BlogPost valueOf(String title, String content) {
    var result = new BlogPost();
    result.setTitle(title);
    result.setContent(content);
    return result;
  }
  
  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public ZonedDateTime getCreated() {
    return created;
  }
  public void setCreated(ZonedDateTime created) {
    this.created = created;
  }
  
}
