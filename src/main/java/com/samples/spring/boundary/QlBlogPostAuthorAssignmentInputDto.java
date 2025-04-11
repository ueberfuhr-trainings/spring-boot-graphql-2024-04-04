package com.samples.spring.boundary;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record QlBlogPostAuthorAssignmentInputDto(

    @NotNull
    UUID blogPostId,
    @NotNull
    UUID authorId
    
) {

}
