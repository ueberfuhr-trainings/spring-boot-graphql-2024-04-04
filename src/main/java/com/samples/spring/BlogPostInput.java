package com.samples.spring;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BlogPostInput(

    @NotNull
    @Size(min = 4, max = 100)
    String title,
    @NotNull
    @Size(min = 4, max = 1000)
    String content
    
) {

}
