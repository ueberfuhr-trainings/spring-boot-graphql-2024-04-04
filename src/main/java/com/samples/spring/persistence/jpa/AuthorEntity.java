package com.samples.spring.persistence.jpa;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "AUTHORS")
@Entity(name = "Author")
public class AuthorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Size(min = 1, max = 50)
  private String firstName;
  @Size(min = 1, max = 50)
  private String lastName;
  
}
