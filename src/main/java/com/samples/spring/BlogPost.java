package com.samples.spring;

import java.util.UUID;

public record BlogPost(

    UUID id,
    String title,
    String content
    
) {

}
