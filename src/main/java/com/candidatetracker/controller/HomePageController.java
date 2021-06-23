package com.candidatetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController
{
  @RequestMapping("/")
  public String redirectPage()
  {
    return "index";
  }
}
