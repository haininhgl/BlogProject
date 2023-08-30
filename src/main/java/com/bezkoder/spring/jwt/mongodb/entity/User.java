package com.bezkoder.spring.jwt.mongodb.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.index.Indexed;

import static com.bezkoder.spring.jwt.mongodb.constants.Constants.*;


@Document(collection = "users")
public class User {

  @Id
  private String id;

  @Size(min = MIN_USERNAME_LENGTH, message = "Username must be at least " + MIN_USERNAME_LENGTH + " characters long")
  @NotEmpty(message = "Please enter username")
  @Indexed(unique = true)
  private String username;

  @JsonIgnore
  @Size(min = MIN_PASSWORD_LENGTH, message = "Password must be at least " + MIN_PASSWORD_LENGTH + " characters long")
  @NotEmpty(message = "Please enter the password")
  private String password;

  @NotBlank
  @Size(max = STRING_MAX_LENGTH)
  @Email
  @Indexed(unique = true)
  private String email;

  @DBRef
  private Set<Role> roles = new HashSet<>();

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public User() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public Set<Role> getRoles() {
    return this.roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
//                ", posts=" + post +
//                ", authorities=" + authorities +
            "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    return id != null && id.equals(((User) o).id);
  }
}
