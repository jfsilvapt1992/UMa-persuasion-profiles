package com.uma.mestrado.persuasive_profiles.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uma.mestrado.persuasive_profiles.services.PersonService;
import com.uma.mestrado.persuasive_profiles.services.UtilsServices;

import swaggerCodegen.models.GetLoginResponse;


@Controller
public class ViewLoginController
{

  Logger logger = LoggerFactory.getLogger(ViewLoginController.class);

  @Autowired
  private UtilsServices utilsService;
  @Autowired
  private PersonService personService;

  @RequestMapping(
  {
    "/",
    "login",
    "login.html"
  })
  private String getLogin(Model aModel)
  {
    aModel.addAttribute("loginError", false);
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam(name = "username") String aUsername, @RequestParam(name = "password") String aPassword, Model aModel)
  {
    try
    {
      ResponseEntity<GetLoginResponse> loginResponse = personService.getV1Login(aUsername, aPassword);
      if (loginResponse.getStatusCode().is2xxSuccessful())
      {
        return "redirect:/index";
      }
      else
      {
        aModel.addAttribute("loginError", true);
        return "redirect:/login";
      }
    }
    catch (Exception e)
    {
      logger.error("", e);
      return "redirect:/error500";
    }
  }
}
