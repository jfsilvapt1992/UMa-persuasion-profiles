package com.uma.mestrado.persuasive_profiles.controllers;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.uma.mestrado.persuasive_profiles.models.CountryDto;
import com.uma.mestrado.persuasive_profiles.models.GenderDto;
import com.uma.mestrado.persuasive_profiles.models.POSTRegisterRequest;
import com.uma.mestrado.persuasive_profiles.services.BackendAPIServices;
import com.uma.mestrado.persuasive_profiles.services.UtilsServices;


@Controller
public class ViewRegistrationController
{

  Logger logger = LoggerFactory.getLogger(ViewRegistrationController.class);

  @Autowired
  private UtilsServices utilsService;
  @Autowired
  private BackendAPIServices backendApiService;

  @GetMapping("/registration")
  public String showRegistForm(@ModelAttribute(name = "registerReq") POSTRegisterRequest aRegisterRequest, Model aModel)
  {
    POSTRegisterRequest registerRequest = new POSTRegisterRequest();
    List<CountryDto> countries = null;
    List<GenderDto> genders = null;

    try
    {
      countries = utilsService.listAllCountries();
      genders = utilsService.listAllGenders();

      aModel.addAttribute("countryList", countries);
      aModel.addAttribute("genderList", genders);
      aModel.addAttribute("registerReq", registerRequest);
    }
    catch (Exception e)
    {
      logger.error("", e);
      return "redirect:/error500";
    }

    return "registration";
  }

  @PostMapping("/registration")
  public String saveRegist(@Valid @ModelAttribute(name = "registerReq") POSTRegisterRequest aRequest, BindingResult aBindingResult)
  {
    try
    {
      if (aBindingResult.hasErrors())
      {
        return "registration";
      }

      ResponseEntity<Void> register =
      backendApiService.postRegister(aRequest.getName(), Integer.parseInt(aRequest.getAge()), aRequest.getGenderId(), aRequest.getCountryId(),
      Integer.parseInt(aRequest.getWeight()), Integer.parseInt(aRequest.getHeight()), aRequest.isHadNutricionalConsult(), aRequest.getUsername(),
      aRequest.getPwd());

      if (!register.getStatusCode().is2xxSuccessful())
      {
        return "redirect:/error500";
      }
      return "redirect:/login";
    }
    catch (Exception e)
    {
      logger.error("", e);
      return "redirect:/error500";
    }
  }

}
