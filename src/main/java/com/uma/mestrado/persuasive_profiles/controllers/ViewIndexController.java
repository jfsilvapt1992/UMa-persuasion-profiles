package com.uma.mestrado.persuasive_profiles.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ViewIndexController
{

  @RequestMapping(
  {
    "",
    "/",
    "index",
    "index.html"
  })
  public String home(Model model)
  {
    return "index";
  }

}
