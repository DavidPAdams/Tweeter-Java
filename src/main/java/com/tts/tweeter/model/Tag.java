package com.tts.tweeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="tag_id")
  private Long id;
  
  private String phrase;
  
  public Tag() {};

  public Tag(String phrase) {
    this.phrase = phrase;
  }

  public String getPhrase() {
    return phrase;
  }

  public void setPhrase(String phrase) {
    this.phrase = phrase;
  }

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Tag [id=" + id + ", phrase=" + phrase + "]";
  }
  
  
}
