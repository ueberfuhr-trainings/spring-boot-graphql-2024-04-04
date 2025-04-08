package com.samples.spring.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.samples.spring.shared.events.PublishEvent;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor
public class AuthorsService {
  
  private final AuthorsSink sink;
  
  public Stream<Author> findAll() {
    return sink.findAll();
  }

  public Optional<Author> findById(UUID id) {
    return sink.findById(id);
  }

  @PublishEvent(AuthorCreatedEvent.class)
  public void create(@Valid Author author) {
     sink.create(author);
  }

}
