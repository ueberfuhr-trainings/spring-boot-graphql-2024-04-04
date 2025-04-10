package com.samples.spring.domain;

import java.util.UUID;

public record BlogPostDeletedEvent(
    UUID id
) {

}
