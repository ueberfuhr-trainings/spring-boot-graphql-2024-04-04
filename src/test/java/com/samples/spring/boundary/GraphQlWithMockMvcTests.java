package com.samples.spring.boundary;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class GraphQlWithMockMvcTests {

  @Autowired
  MockMvc mvc;
  
  @Test
  void shouldReturnAllBlogPosts() throws Exception {
    
    mvc
      .perform(
          post("/graphql")
          .contentType(MediaType.APPLICATION_JSON)
          .content("""
              {
                "query": "query { findAllBlogPosts { id, title } }"
              }
              """)
      )
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.errors").doesNotExist())
      .andExpect(jsonPath("$.data").isNotEmpty());
    
  }
  
}
