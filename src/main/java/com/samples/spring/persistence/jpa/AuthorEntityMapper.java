package com.samples.spring.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.samples.spring.domain.Author;

@Mapper(componentModel = "spring")
public interface AuthorEntityMapper {
  
  AuthorEntity map(Author source);
  
  Author map(AuthorEntity source);
  
  void copy(AuthorEntity source, @MappingTarget Author target);

}
