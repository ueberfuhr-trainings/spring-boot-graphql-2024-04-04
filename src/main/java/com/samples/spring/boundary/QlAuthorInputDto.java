package com.samples.spring.boundary;

import jakarta.validation.constraints.Size;

public record QlAuthorInputDto(
    
    @Size(min = 1, max = 50)
    String firstName,
    @Size(min = 1, max = 50)
    String lastName
    
) {

}
