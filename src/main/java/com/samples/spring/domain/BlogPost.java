package com.samples.spring.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogPost {

  private UUID id;
  @NotNull
  @Size(min = 4, max = 100)
  private String title;
  @NotNull
  @Size(min = 4, max = 1000)
  private String content;
  private ZonedDateTime created;
  private Author author;

}
