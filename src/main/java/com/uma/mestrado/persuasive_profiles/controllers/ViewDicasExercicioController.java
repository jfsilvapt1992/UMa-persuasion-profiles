package com.uma.mestrado.persuasive_profiles.controllers;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uma.mestrado.persuasive_profiles.enums.InfluencePrincipleEnum;
import com.uma.mestrado.persuasive_profiles.models.PersuasionProfileDto;

import swaggerCodegen.models.GetInfluencePrincipleResponse;
import swaggerCodegen.models.GetLoginResponse;


@Controller
public class ViewDicasExercicioController
{

  Logger logger = LoggerFactory.getLogger(ViewDicasExercicioController.class);

  @Autowired
  private BackendAPIServices backendApiService;

  @GetMapping(
  {
    "dicas-exercicio",
    "dicas-exercicio.html"
  })
  private String getDicasAlimentacao(Model aModel, HttpSession aSession)
  {
    try
    {
      logger.info("----------------- DICAS EXCERCICIO -----------------");
      GetLoginResponse loginData = (GetLoginResponse) aSession.getAttribute("loginResponse");

      GetInfluencePrincipleResponse selectedPrinciple = getInfluencePrincipleFromSession(loginData);

      backendApiService.postHistoric(loginData.getPersonId(), selectedPrinciple.getId(), false);

      aSession.setAttribute("activePrinciple", new PersuasionProfileDto(selectedPrinciple.getId(), selectedPrinciple.getName()));

      logger.info("----------------------------------------------------");
      return getPageToRedirect(selectedPrinciple);
    }
    catch (Exception e)
    {
      logger.error("", e);
      return "redirect:/error500";
    }

  }

  private GetInfluencePrincipleResponse getInfluencePrincipleFromSession(GetLoginResponse aLoginData)
  {
    ResponseEntity<GetInfluencePrincipleResponse> ipSelected = backendApiService.getPersonInfluencePrinciple(aLoginData.getPersonId());
    return ipSelected.getBody();
  }

  private String getPageToRedirect(GetInfluencePrincipleResponse aSelectedPrinciple)
  {
    String redirect = "";
    InfluencePrincipleEnum ipEnum = InfluencePrincipleEnum.getByName(aSelectedPrinciple.getName());
    switch (ipEnum)
    {
      case AUTHORITY:
        redirect = "dicas-exercicio-authority";
        break;
      case CONSENSUS:
        redirect = "dicas-exercicio-consensus";
        break;
      case RECIPROCITY:
        redirect = "dicas-exercicio-reciprocity";
        break;
      case SCARCITY:
        redirect = "dicas-exercicio-scarcity";
        break;
      default:
        break;
    }
    return redirect;
  }
}
