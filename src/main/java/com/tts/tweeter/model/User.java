package com.tts.tweeter.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;


@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user-id")
  private Long id;

  @NotEmpty(message = "Please provide your first name")
  private String firstName;
  
  @NotEmpty(message = "Please provide your last name")
  private String lastName;
  
  @Length(min = 3, message = "Username must have at least 3 characters")
  @Length(max = 15, message = "Username cannot have more than 15 characters")
  @Pattern(regexp="[^\\s]+", message = "Username cannot contain spaces")
  private String username;
  
  @Email(message = "Please provide a valid email")
  @NotEmpty(message = "Please provide an email")
  private String email;
  
  @Length(min = 5, message = "Password must have at least 5 characters")
  @Length(max = 15, message = "Password cannot have more than 15 characters")
  private String password;
  
  private Integer active;

  @CreationTimestamp
  private Date createdAt;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public User() {
  };

  public User(String firstName, String lastName, String username, String email, String password, Integer active,
      Date createdAt) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.active = active;
    this.createdAt = createdAt;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getActive() {
    return active;
  }

  public void setActive(Integer active) {
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }
  
  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
  
  @Override
  public String toString() {
    return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
        + ", email=" + email + ", password=" + password + ", active=" + active + ", createdAt=" + createdAt + "]";
  }

}
