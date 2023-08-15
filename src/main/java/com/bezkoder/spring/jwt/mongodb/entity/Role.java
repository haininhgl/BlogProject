package com.bezkoder.spring.jwt.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

  @Id
  private String id;

  private RoleType name;

  private boolean isSystemRole = false;

  public Role() {

  }

  public Role(RoleType name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RoleType getName() {
    return name;
  }

  public void setName(RoleType name) {
    this.name = name;
  }

  public boolean isSystemRole() {
    return isSystemRole;
  }

  public void setSystemRole(boolean systemRole) {
    isSystemRole = systemRole;
  }
}
