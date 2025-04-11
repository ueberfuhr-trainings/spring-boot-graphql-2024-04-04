package com.samples.spring.domain;

import java.util.UUID;

import jakarta.validation.constraints.Size;

public class Author {

  private UUID id;
  @Size(min = 1, max = 50)
  private String firstName;
  @Size(min = 1, max = 50)
  private String lastName;

  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  
  
}
