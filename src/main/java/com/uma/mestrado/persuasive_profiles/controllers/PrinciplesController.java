package com.uma.mestrado.persuasive_profiles.controllers;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;

import swaggerCodegen.models.GetLoginResponse;


@Controller
public class PrinciplesController
{

  Logger logger = LoggerFactory.getLogger(PrinciplesController.class);

  @Autowired
  private BackendAPIServices backendApiServices;

  @PostMapping("/historic/successfull")
  public String saveSuccessfullHistoric(Model aModel, HttpSession aSession)
  {
    try
    {
      GetLoginResponse sessionData = (GetLoginResponse) aSession.getAttribute("loginResponse");
      PersuasionProfileDto currentPersuasionProfile = (PersuasionProfileDto) aSession.getAttribute("activePrinciple");

      ResponseEntity<Void> putHistoric = backendApiServices.putHistoric(sessionData.getPersonId(), currentPersuasionProfile.getId(), true);
      if (!putHistoric.getStatusCode().is2xxSuccessful())
      {
        return "redirect:/error500";
      }
      return "marcar-consulta";
    }
    catch (Exception e)
    {
      logger.error("", e);
      return "redirect:/error500";
    }
  }
}
