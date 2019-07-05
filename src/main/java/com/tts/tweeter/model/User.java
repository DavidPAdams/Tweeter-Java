package com.tts.tweeter.model;

import java.sql.Date;
import java.util.HashSet;
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

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="user-id")
  private Long id;
  
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String password;
  private Integer active;
  
  @CreationTimestamp
  private Date createdAt;
  
  @ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
  private Set<Role> roles;
  
  public User() {};

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

  @Override
  public String toString() {
    return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
        + ", email=" + email + ", password=" + password + ", active=" + active + ", createdAt=" + createdAt + "]";
  }

  public void setRoles(HashSet<Role> hashSet) {
    
  }
  
}
