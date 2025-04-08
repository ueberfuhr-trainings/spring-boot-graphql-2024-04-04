package com.samples.spring.persistence.jpa;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
  @ManyToOne // default fetch type is eager, no cascading
  private AuthorEntity author;

  @PrePersist
  void updateCreated() {
    if(null == created) {
      created = ZonedDateTime.now();
    }
  }

}
