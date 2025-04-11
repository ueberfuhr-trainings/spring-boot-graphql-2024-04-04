package com.samples.spring.boundary;

import java.util.UUID;

public record QlAuthorDto(
    
    UUID id,
    String firstName,
    String lastName
    
) {

}
