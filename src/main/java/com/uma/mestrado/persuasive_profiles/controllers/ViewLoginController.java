package com.uma.mestrado.persuasive_profiles.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ViewLoginController
{

  Logger logger = LoggerFactory.getLogger(ViewRegistrationController.class);

  @RequestMapping(
  {
    "/",
    "login",
    "login.html"
  })
  private String getLogin(Model model)
  {
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password)
  {

    return "redirect:/index";
  }
}
