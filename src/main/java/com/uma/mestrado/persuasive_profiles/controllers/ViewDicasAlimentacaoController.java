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
public class ViewDicasAlimentacaoController
{

  Logger logger = LoggerFactory.getLogger(ViewDicasAlimentacaoController.class);

  @Autowired
  private BackendAPIServices backendApiService;

  @GetMapping(
  {
    "dicas-alimentacao",
    "dicas-alimentacao.html"
  })
  private String getDicasAlimentacao(Model aModel, HttpSession aSession)
  {
    try
    {

      GetLoginResponse loginData = (GetLoginResponse) aSession.getAttribute("loginResponse");

      GetInfluencePrincipleResponse selectedPrinciple = getInfluencePrincipleFromSession(loginData);

      backendApiService.postHistoric(loginData.getPersonId(), selectedPrinciple.getId(), false);

      aSession.setAttribute("activePrinciple", new PersuasionProfileDto(selectedPrinciple.getId(), selectedPrinciple.getName()));

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
        redirect = "dicas-alimentacao-authority";
        break;
      case CONSENSUS:
        redirect = "dicas-alimentacao-consensus";
        break;
      case RECIPROCITY:
        redirect = "dicas-alimentacao-reciprocity";
        break;
      case SCARCITY:
        redirect = "dicas-alimentacao-scarcity";
        break;
      default:
        break;
    }
    return redirect;
  }
}
