package com.uma.mestrado.persuasive_profiles.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewHomeController
{

  Logger logger = LoggerFactory.getLogger(ViewHomeController.class);

  // @Autowired
  // private BackendAPIServices backendApiService;a

  @GetMapping(
  {
    "home",
    "home.html"
  })
  private String getHome(Model aModel)
  {
    return "home";
  }
}
