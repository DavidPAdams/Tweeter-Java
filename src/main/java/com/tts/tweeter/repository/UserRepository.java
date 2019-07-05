package com.tts.tweeter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tts.tweeter.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public User findByUsername(String username);
  public List<User> findAllUsers();
}
