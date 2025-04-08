package com.samples.spring.boundary;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.samples.spring.domain.AuthorsService;
import com.samples.spring.domain.BlogPostsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QlAuthorBlogPostAssignmentController {

  // TODO create assignment service in domain
  
  private final BlogPostsService blogPostsService;
  private final AuthorsService authorsService;
  private final QlBlogPostDtoMapper mapper;
  
  @MutationMapping("assignAuthorToBlogPost")
  public QlBlogPostDto assignAuthorToBlogPost(
      @Valid
      @Argument("input")
      QlBlogPostAuthorAssignmentInputDto input
  ) {
    var author = authorsService
        .findById(input.authorId())
        .get(); // else throw exception that leads to GraphQlError
    var blogPost = blogPostsService
        .findById(input.blogPostId())
        .get(); // else throw exception that leads to GraphQlError
    blogPost.setAuthor(author);
    blogPostsService.update(blogPost);
    return mapper.map(blogPost);
  }
  
}
