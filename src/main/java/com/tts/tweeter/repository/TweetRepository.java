package com.tts.tweeter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tts.tweeter.model.Tweet;
import com.tts.tweeter.model.User;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
  public List<Tweet> findAllByOrderByCreatedAtDesc();
  public List<Tweet> findAllByUserOrderByCreatedAtDesc(User user);
  public List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<User> users);
}
