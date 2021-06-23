package com.candidatetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController
{
  @GetMapping("/login")
  public String showLoginPage()
  {
    return "login";
  }

  @GetMapping("/access-denied")
  public String showAccessDeniedPage()
  {
    return "access-denied";
  }
}
