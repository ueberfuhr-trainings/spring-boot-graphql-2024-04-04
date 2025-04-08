package com.samples.spring.boundary;

import java.time.LocalDateTime;
import java.util.UUID;

public record QlBlogPostDto(

    UUID id,
    String title,
    String content,
    LocalDateTime created
    
) {

}
