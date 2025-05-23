package com.samples.spring.persistence.jpa;

import com.samples.spring.domain.BlogPostOptions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

class JpaEntityGraphQueryBuilder {

  private final EntityManager em;
  
  JpaEntityGraphQueryBuilder(EntityManager em) {
    super();
    this.em = em;
  }

  TypedQuery<BlogPostEntity> findAllQuery(BlogPostOptions options) {
    var entityGraph = em
        .createEntityGraph(BlogPostEntity.class);
    if(options.isAuthor()) {
      entityGraph.addSubgraph("author");
    }
    return em
        .createQuery("select b from BlogPost b", BlogPostEntity.class)
        .setHint("jakarta.persistence.fetchgraph", entityGraph);
  }
  
}
