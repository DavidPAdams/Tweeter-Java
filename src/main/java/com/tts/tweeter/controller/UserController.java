package com.tts.tweeter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.tweeter.model.TweetDisplay;
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
    User loggedInUser = userService.getLoggedInUser();
    List<User> following = loggedInUser.getFollowing();
    List<TweetDisplay> tweets = tweetService.findAllByUser(user);
    boolean isFollowing = false;
    for (User followedUser : following) {
      if (followedUser.getUsername().equalsIgnoreCase(username)) {
        isFollowing = true;
      }
    }
    boolean isSelfPage = loggedInUser.getUsername().equalsIgnoreCase(username);
    model.addAttribute("isSelfPage", isSelfPage);
    model.addAttribute("following", isFollowing);
    model.addAttribute("tweetList", tweets);
    model.addAttribute("user", user);
    return "user";
  }
  
  @GetMapping("/users")
  public String getUsers(@RequestParam(value="filter", required=false) String filter, Model model) {
    List<User> users = new ArrayList<>();
    User loggedInUser = userService.getLoggedInUser();
    List<User> usersFollowing = loggedInUser.getFollowing();
    List<User> usersFollowers = loggedInUser.getFollowers();
    if(filter == null) {
      filter = "all";
    }
    if(filter.equalsIgnoreCase("followers")) {
      users = usersFollowers;
      model.addAttribute("filter", "followers");
    } else if(filter.equalsIgnoreCase("following")){
      users = usersFollowing;
      model.addAttribute("filter", "following");
    } else {
      users = userService.findAllUsers();
      model.addAttribute("filter", "all");
    }
    SetFollowingStatus(users, usersFollowing, model);
    model.addAttribute("users", users);
    SetTweetCounts(users, model);
    return "users";
  }
  
  private void SetTweetCounts(List<User> users, Model model) {
    HashMap<String, Integer> tweetCounts = new HashMap<>();
    for (User user : users) {
      List<TweetDisplay> tweets = tweetService.findAllByUser(user);
      tweetCounts.put(user.getUsername(), tweets.size());
    }
    model.addAttribute("tweetCounts", tweetCounts);
  }
  
  private void SetFollowingStatus(List<User> users, List<User> usersFollowing, Model model) {
    HashMap<String, Boolean> followingStatus = new HashMap<>();
    String username = userService.getLoggedInUser().getUsername();
    for (User user : users) {
      if (usersFollowing.contains(user)) {
        followingStatus.put(user.getUsername(), true);
      } else if (!user.getUsername().equalsIgnoreCase(username)) {
        followingStatus.put(user.getUsername(), false);
      }
    }
    model.addAttribute("followingStatus", followingStatus);
  }
}
