package com.samples.spring.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BlogPostOptions {

  @Builder.Default
  private boolean author = true;
  
}
