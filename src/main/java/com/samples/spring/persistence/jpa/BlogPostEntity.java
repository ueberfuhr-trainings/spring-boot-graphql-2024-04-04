package com.samples.spring.persistence.jpa;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name = "BLOGPOSTS")
@Entity(name = "BlogPost") // für JPQL
public class BlogPostEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @NotNull
  @Size(min = 4, max = 100)
  private String title;
  @NotNull
  @Size(min = 4, max = 1000)
  private String content;
  @Column(name = "CREATION_DATE")
  private ZonedDateTime created;

  @PrePersist
  void updateCreated() {
    if(null == created) {
      created = ZonedDateTime.now();
    }
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
