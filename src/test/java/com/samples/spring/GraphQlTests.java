package com.samples.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ResponseError;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
class GraphQlTests {

  private static final String ISO8601_REGEX 
    = "^\\d{4}-\\d{2}-\\d{2}" +  // Date: YYYY-MM-DD
      "T\\d{2}:\\d{2}:\\d{2}" +  // Time: THH:mm:ss
      "(\\.\\d{1,9})?" +         // Optional: .SSS... (fractional seconds)
      "(Z|[+-]\\d{2}:\\d{2})?$"; 
  
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
              content,
              created
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
        .path("createBlogPost.created")
        .entity(String.class)
        .matches(field -> field.matches(ISO8601_REGEX))
      .path("createBlogPost.id")
        .entity(String.class)
        .get();
    
    graphQlTester
      .document("""
              query findBlogPostById($id: UUID!) { 
                findBlogPostById(id: $id) { 
                  title,
                  content
                }
              }
              """
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
  
  
  private static Stream<Arguments> provideInvalidBlogPostInputs() {
    return Stream.of(
      Arguments.of("", "Test"),
      Arguments.of("T", "Test"),
      Arguments.of("Test", ""),
      Arguments.of("T".repeat(101), "Test"),
      Arguments.of("Test", "T".repeat(1001))
    );
}
  
  @ParameterizedTest
  @MethodSource("provideInvalidBlogPostInputs")
  void shouldNotCreateInvalidBlogPost(String title, String content) {
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
    .variable("title", title)
    .variable("content", content)
    .execute()
    .errors().satisfy(errors -> 
      assertThat(errors)
        .isNotEmpty()
        .hasSize(1)
        .first()
        .extracting(ResponseError::getErrorType)
        .isEqualTo(ErrorType.BAD_REQUEST)
        
    );
  }
  
  @Test
  void shouldDeleteAndNotReturnBlogPost() {

    // first, create a blog post
    var id = graphQlTester
      .document("""
          mutation {
            createBlogPost(input: {
                title: "Test-Title", 
                content: "Test-Content"
            }) {
              id
            }
          }
          """)
      .execute()
      .errors()
        .verify()
      .path("createBlogPost.id")
        .entity(String.class)
        .get();
    
    // now, delete it (expect true)
    var result = graphQlTester
    .document("""
        mutation deleteBlogPost($id: UUID!) {
          deleteBlogPost(id: $id)
        }
        """)
    .variable("id", id)
    .execute()
    .errors()
      .verify()
    .path("deleteBlogPost")
      .entity(Boolean.class)
      .get();
    assertThat(result).isTrue();

    // now, delete it again (expect false)
    var repeatResult = graphQlTester
    .document("""
        mutation deleteBlogPost($id: UUID!) {
          deleteBlogPost(id: $id)
        }
        """)
    .variable("id", id)
    .execute()
    .errors()
      .verify()
    .path("deleteBlogPost")
      .entity(Boolean.class)
      .get();
    assertThat(repeatResult).isFalse();

    // query the non-existing document
    graphQlTester
      .document("""
              query findBlogPostById($id: UUID!) { 
                findBlogPostById(id: $id) { 
                  title,
                  content
                }
              }
              """
          )
      .variable("id", id)
      .execute()
      .errors()
        .verify()
      .path("findBlogPostById")
        .valueIsNull();

  }

}
