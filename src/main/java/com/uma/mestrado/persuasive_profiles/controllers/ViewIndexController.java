package com.uma.mestrado.persuasive_profiles.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewIndexController
{

  @GetMapping(
  {
    "",
    "/",
    "index",
    "index.html"
  })
  public String home(Model aModel)
  {
    return "index";
  }

}
