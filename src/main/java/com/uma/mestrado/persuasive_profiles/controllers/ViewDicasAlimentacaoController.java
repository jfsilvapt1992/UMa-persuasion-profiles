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
    String redirect = "";

    InfluencePrincipleEnum ipEnum = InfluencePrincipleEnum.getByName(getInfluencePrincipleFromSession(aSession));
    switch (ipEnum)
    {
      case AUTHORITY:
        redirect = "dicas-alimentacao-authority";
        break;
      case CONSENSUS:
        redirect = "dicas-alimentacao-consensus";
        break;
      case LIKING:
        redirect = "dicas-alimentacao-liking";
        break;
      case SCARCITY:
        redirect = "dicas-alimentacao-scarcity";
        break;
      default:
        break;

    }
    return redirect;
  }

  private String getInfluencePrincipleFromSession(HttpSession aSession)
  {
    GetLoginResponse loginData = (GetLoginResponse) aSession.getAttribute("loginResponse");
    ResponseEntity<GetInfluencePrincipleResponse> ipSelected = backendApiService.getPersonInfluencePrinciple(loginData.getPersonId());
    GetInfluencePrincipleResponse ipData = ipSelected.getBody();
    return ipData.getName();
  }
}
