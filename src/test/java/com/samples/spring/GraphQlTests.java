package com.samples.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
public class GraphQlTests {

  @Autowired
  GraphQlTester graphQlTester;
  
  @Test
  void shouldReturnAllBlogPosts() {
    
    graphQlTester
      .document("""
          query { 
            findAllBlogPosts { 
              id, 
              title 
            }
          }
          """)
      .execute()
      .errors()
        .verify()
      .path("findAllBlogPosts")
        .hasValue();
    
  }
  
  @Test
  void shouldCreateAndReturnBlogPost() {
    
    var newId = graphQlTester
      .document("""
          mutation {
            createBlogPost(input: {
                title: "Test-Title", 
                content: "Test-Content"
            }) {
              id,
              title,
              content
            }
          }
          """)
      .execute()
      .errors()
        .verify()
      .path("createBlogPost.title")
        .entity(String.class)
        .isEqualTo("Test-Title")
      .path("createBlogPost.content")
        .entity(String.class)
        .isEqualTo("Test-Content")
      .path("createBlogPost.id")
        .entity(Long.class)
        .get();
    
    graphQlTester
      .document(
          String.format("""
              query findBlogPostById($id: ID!) { 
                findBlogPostById(id: $id) { 
                  title,
                  content
                }
              }
              """, newId)
          )
      .variable("id", newId)
      .execute()
      .errors()
        .verify()
      .path("findBlogPostById.title")
        .entity(String.class)
        .isEqualTo("Test-Title")
      .path("findBlogPostById.content")
        .entity(String.class)
        .isEqualTo("Test-Content");

  }
  
}
