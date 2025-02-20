package com.tts.tweeter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.tweeter.model.User;
import com.tts.tweeter.service.UserService;

@Controller
public class FollowController {
  @Autowired
  private UserService userService;
  
  @PostMapping("/follow/{username}")
  public String follow(@PathVariable("username") String username, HttpServletRequest request) {
    User loggedInUser = userService.getLoggedInUser();
    User userToFollow = userService.findByUsername(username);
    List<User> followers = userToFollow.getFollowers();
    followers.add(loggedInUser);
    userToFollow.setFollowers(followers);
    userService.saveUser(userToFollow);
    return "redirect:" + request.getHeader("Referer");
  }
  
  @PostMapping("/unfollow/{username}")
  public String unfollow(@PathVariable("username") String username, HttpServletRequest request) {
    User loggedInUser = userService.getLoggedInUser();
    User userToUnfollow = userService.findByUsername(username);
    List<User> followers = userToUnfollow.getFollowers();
    followers.remove(loggedInUser);
    userToUnfollow.setFollowers(followers);
    userService.saveUser(userToUnfollow);
    return "redirect:" + request.getHeader("Referer");
  }
  
}
