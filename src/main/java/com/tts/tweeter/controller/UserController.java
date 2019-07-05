package com.tts.tweeter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tts.tweeter.model.Tweet;
import com.tts.tweeter.model.User;
import com.tts.tweeter.service.TweetService;
import com.tts.tweeter.service.UserService;

@Controller
public class UserController {
  @Autowired
  private UserService userService;
  
  @Autowired
  private TweetService tweetService;
  
  @GetMapping("/users/{username}")
  public String getUser(@PathVariable("username") String username, Model model) {
    User user = userService.findByUsername(username);
    List<Tweet> tweets = tweetService.findAllByUser(user);
    model.addAttribute("tweetList", tweets);
    model.addAttribute("user", user);
    return "user";
  }
  
}
