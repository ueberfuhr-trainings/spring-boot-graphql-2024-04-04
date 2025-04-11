package com.samples.spring.domain;

import java.util.UUID;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Author {

  private UUID id;
  @Size(min = 1, max = 50)
  private String firstName;
  @Size(min = 1, max = 50)
  private String lastName;
  
}
