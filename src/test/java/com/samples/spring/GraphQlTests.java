package com.samples.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class GraphQlTests {

  @Autowired
  HttpGraphQlTester graphQlTester;
  
  @Test
  void shouldReturnAllBlogPosts() throws Exception {
    
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
  
}
