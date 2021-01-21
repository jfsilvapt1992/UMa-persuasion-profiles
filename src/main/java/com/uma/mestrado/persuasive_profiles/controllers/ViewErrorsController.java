package com.uma.mestrado.persuasive_profiles.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewErrorsController
{

  @GetMapping(
  {
    "error500",
    "error500.html"
  })
  public String home(Model aModel)
  {
    return "error500";
  }

}
