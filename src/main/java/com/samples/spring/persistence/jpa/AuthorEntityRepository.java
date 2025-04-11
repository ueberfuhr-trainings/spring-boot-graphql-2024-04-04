package com.samples.spring.persistence.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorEntityRepository
  extends JpaRepository<AuthorEntity, UUID>{

}
