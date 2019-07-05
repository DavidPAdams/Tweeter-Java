package com.tts.tweeter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.tweeter.model.User;
import com.tts.tweeter.service.UserService;

@Controller
public class AuthorizationController {
  @Autowired
  private UserService userService;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String signup(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    return "signup";
  }

  @PostMapping("/signup")
  public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
    User userExists = userService.findByUsername(user.getUsername());
    if (userExists != null) {
      bindingResult.rejectValue("username", "error.user", "Username is already taken!");
      model.addAttribute("failSignup", "Sign up was unsuccessful, NO ACCOUNT FOR YOU");
    }
    if (!bindingResult.hasErrors()) {
      userService.saveNewUser(user);
      model.addAttribute("successSignup", "Sign up successful! You may login now.");
    }
    return "login";
  }

}
