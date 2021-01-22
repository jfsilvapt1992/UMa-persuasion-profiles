package com.uma.mestrado.persuasive_profiles.controllers;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import swaggerCodegen.models.GetLoginResponse;


@Controller
public class ViewLoginController
{

  Logger logger = LoggerFactory.getLogger(ViewLoginController.class);

  @Autowired
  private BackendAPIServices backendApiService;

  @GetMapping(
  {
    "",
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
  public String doLogin(@RequestParam(name = "username") String aUsername, @RequestParam(name = "password") String aPassword, Model aModel, HttpSession session)
  {
    try
    {
      ResponseEntity<GetLoginResponse> loginResponse = backendApiService.getV1Login(aUsername, aPassword);
      if (loginResponse.getStatusCode().is2xxSuccessful())
      {
        session.setAttribute("loginResponse", loginResponse.getBody());
        return "redirect:/home";
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
