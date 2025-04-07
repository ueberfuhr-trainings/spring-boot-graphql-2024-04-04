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
