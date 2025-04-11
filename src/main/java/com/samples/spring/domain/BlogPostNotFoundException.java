package com.samples.spring.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@RequiredArgsConstructor
@Getter
public class BlogPostNotFoundException extends RuntimeException {

  private final UUID id;

}
