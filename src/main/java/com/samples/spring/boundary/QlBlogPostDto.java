package com.samples.spring.boundary;

import java.time.ZonedDateTime;
import java.util.UUID;

public record QlBlogPostDto(

    UUID id,
    String title,
    String content,
    ZonedDateTime created,
    QlAuthorDto author
    
) {

}
