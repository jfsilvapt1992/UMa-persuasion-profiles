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
    String redirect = "";
    GetInfluencePrincipleResponse selectedPrinciple = getInfluencePrincipleFromSession(aSession);
    InfluencePrincipleEnum ipEnum = InfluencePrincipleEnum.getByName(selectedPrinciple.getName());
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
    aSession.setAttribute("activePrinciple", new PersuasionProfileDto(selectedPrinciple.getId(), selectedPrinciple.getName()));
    return redirect;
  }

  private GetInfluencePrincipleResponse getInfluencePrincipleFromSession(HttpSession aSession)
  {
    GetLoginResponse loginData = (GetLoginResponse) aSession.getAttribute("loginResponse");
    ResponseEntity<GetInfluencePrincipleResponse> ipSelected = backendApiService.getPersonInfluencePrinciple(loginData.getPersonId());
    return ipSelected.getBody();
  }
}
