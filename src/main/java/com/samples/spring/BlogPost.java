package com.samples.spring;

import java.time.LocalDateTime;
import java.util.UUID;

public record BlogPost(

    UUID id,
    String title,
    String content,
    LocalDateTime created
    
) {

}
