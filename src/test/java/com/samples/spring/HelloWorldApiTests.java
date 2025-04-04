package com.samples.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldApiTests {

  @Autowired
  MockMvc mvc;
  
  @Test
  void shouldReturnHelloMessage() throws Exception {
    
    mvc
      .perform(
        get("/hello")
          .param("name", "Tom")
      )
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
      .andExpect(content().string("Hello Tom"));
  }

  @Test
  void shouldReturnHelloWorldMessage() throws Exception {
    mvc
    .perform(
      get("/hello")
    )
    .andExpect(status().isOk())
    .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
    .andExpect(content().string("Hello World"));
  }
  
  @Test
  void shouldReturnHelloWorldJson() throws Exception {
    mvc
      .perform(
        get("/hello/json")
      )
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.message").value("Hello World"));
  }
  
  
}
