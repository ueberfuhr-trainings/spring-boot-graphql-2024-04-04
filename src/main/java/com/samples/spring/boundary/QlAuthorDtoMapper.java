package com.samples.spring.boundary;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.samples.spring.domain.Author;

@Mapper(componentModel = "spring")
public interface QlAuthorDtoMapper {

  QlAuthorDto map(Author source);
  
  Author map(QlAuthorDto source);
  
  @Mapping(target = "id", ignore = true)
  Author map(QlAuthorInputDto source);
  
}
