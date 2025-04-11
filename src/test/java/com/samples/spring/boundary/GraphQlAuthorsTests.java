package com.samples.spring.boundary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
@AutoConfigureTestDatabase
class GraphQlAuthorsTests {

  @Autowired
  GraphQlTester graphQlTester;

  /*
   * Test Scenario
   * - we create an author
   * - we should be able to find the author by its id
   */
  @Test
  void shouldCreateAndFindAuthorById() {
    var newId = graphQlTester
        .document("""
            mutation {
              createAuthor(input: {
                  firstName: "Tom",
                  lastName: "Mayer"
              }) {
                id,
                firstName,
                lastName
              }
            }
            """)
        .execute()
        .errors()
          .verify()
        .path("createAuthor.firstName")
          .entity(String.class)
          .isEqualTo("Tom")
        .path("createAuthor.lastName")
          .entity(String.class)
          .isEqualTo("Mayer")
        .path("createAuthor.id")
          .entity(String.class)
          .get();

    graphQlTester
        .document("""
            query findAuthorById($id: UUID!) {
              findAuthorById(id: $id) {
                firstName,
                lastName
              }
            }
            """)
        .variable("id", newId)
        .execute()
        .errors()
          .verify()
        .path("findAuthorById.firstName")
          .entity(String.class)
          .isEqualTo("Tom")
        .path("findAuthorById.lastName")
          .entity(String.class)
          .isEqualTo("Mayer");
  
  }

  /*
   * Test Scenario
   * - we create an author
   * - we should be able to find the author in the all-authors list
   */
  @Test
  void shouldCreateAndFindAuthorInList() {
    var newId = graphQlTester
        .document("""
            mutation {
              createAuthor(input: {
                  firstName: "Tom",
                  lastName: "Mayer"
              }) {
                id,
                firstName,
                lastName
              }
            }
            """)
        .execute()
        .errors()
          .verify()
        .path("createAuthor.firstName")
          .entity(String.class)
          .isEqualTo("Tom")
        .path("createAuthor.lastName")
          .entity(String.class)
          .isEqualTo("Mayer")
        .path("createAuthor.id")
          .entity(String.class)
          .get();

    graphQlTester
        .document("""
            query {
              findAllAuthors {
                id,
                firstName,
                lastName
              }
            }
            """)
        .execute()
        .errors()
          .verify()
        .path("findAllAuthors[?(@.id == '" + newId + "')]")
          .hasValue();
  
  }
  
  /*
   * Test Scenario
   * - we create an author
   * - we create a blog post
   * - we assign the author to the blog post 
   * - we should be able to request the blog post by its id with author
   */
  @Test
  void shouldCreateAndAssignAuthorAndFindItInBlogPostById() {
    // we create an author
    var newAuthorId = graphQlTester
        .document("""
            mutation {
              createAuthor(input: {
                  firstName: "Tom",
                  lastName: "Mayer"
              }) {
                id
              }
            }
            """)
        .execute()
        .errors()
          .verify()
        .path("createAuthor.id")
          .entity(String.class)
          .get();
    // we create a blog post
    var newBlogPostId = graphQlTester
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
    // we assign the author to the blog post
    graphQlTester
        .document("""
            mutation assignAuthorToBlogPost(
              $blogPostId: UUID!,
              $authorId: UUID!
            ) {
              assignAuthorToBlogPost(input: {
                  blogPostId: $blogPostId, 
                  authorId: $authorId
              }) {
                author {
                  firstName,
                  lastName
                }
              }
            }
            """)
        .variable("blogPostId", newBlogPostId)
        .variable("authorId", newAuthorId)
        .execute()
        .errors()
          .verify()
        .path("assignAuthorToBlogPost.author.firstName")
          .entity(String.class)
          .isEqualTo("Tom")
        .path("assignAuthorToBlogPost.author.lastName")
          .entity(String.class)
          .isEqualTo("Mayer");
    // we should be able to request the blog post by its id with author
    graphQlTester
        .document("""
            query findBlogPostById($id: UUID!) {
              findBlogPostById(id: $id) {
                author {
                  firstName,
                  lastName
                }
              }
            }
            """)
        .variable("id", newBlogPostId)
        .execute()
        .errors()
          .verify()
        .path("findBlogPostById.author.firstName")
          .entity(String.class)
          .isEqualTo("Tom")
        .path("findBlogPostById.author.lastName")
          .entity(String.class)
          .isEqualTo("Mayer");
  }
}
