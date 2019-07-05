package com.tts.tweeter.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

@Entity
public class Tweet {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="tweet_id")
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY, optional=false)
  @JoinColumn(name="user_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;
  
  @NotEmpty(message = "Tweet must have content")
  @Length(max = 240, message = "Tweet is no more than 240 characters")
  private String message;
  
  @CreationTimestamp
  private Date createdAt;
  
  public Tweet() {}

  public Tweet(User user, String message, Date createdAt) {
    this.user = user;
    this.message = message;
    this.createdAt = createdAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "Tweet [id=" + id + ", user=" + user + ", message=" + message + ", createdAt=" + createdAt + "]";
  }
  
}
