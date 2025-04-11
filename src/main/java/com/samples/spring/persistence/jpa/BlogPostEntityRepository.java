package com.samples.spring.persistence.jpa;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostEntityRepository
  extends JpaRepository<BlogPostEntity, UUID>{

  // Query("select p from BlogPost where ...")
  List<BlogPostEntity> findAllByCreatedAfter(ZonedDateTime time);
  
}
