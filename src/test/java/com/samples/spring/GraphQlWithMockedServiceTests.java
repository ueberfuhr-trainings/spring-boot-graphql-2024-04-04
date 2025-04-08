package com.samples.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ResponseError;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@AutoConfigureGraphQlTester
public class GraphQlWithMockedServiceTests {

  @Autowired
  GraphQlTester graphQlTester;
  @MockitoBean
  BlogPostsService service;
  
  @Test
  void shouldReturnTrueOnSuccessfulDelete() {
    var id = UUID.randomUUID();
    
    when(service.delete(id))
      .thenReturn(true);
    
    var result = graphQlTester
        .document("""
            mutation deleteBlogPost($id: UUID!) {
              deleteBlogPost(id: $id)
            }
            """)
        .variable("id", id.toString())
        .execute()
        .errors()
          .verify()
        .path("deleteBlogPost")
          .entity(Boolean.class)
          .get();
        assertThat(result).isTrue();
  }
  
  @Test
  void shouldNotCreateInvalidBlogPost() {
    graphQlTester
    .document("""
        mutation createBlogPost($title: String!, $content: String!) {
          createBlogPost(input: {
              title: $title, 
              content: $content
          }) {
            id
          }
        }
        """)
    .variable("title", "T")
    .variable("content", "Test-Content")
    .execute()
    .errors().satisfy(errors -> 
      assertThat(errors)
        .isNotEmpty()
        .hasSize(1)
        .first()
        .extracting(ResponseError::getErrorType)
        .isEqualTo(ErrorType.BAD_REQUEST)   
    );
    
    // Mockito.verify(service, Mockito.never())
    //  .create(ArgumentMatchers.any());
    verify(service, never())
      .create(any());
    
  }
  
}
