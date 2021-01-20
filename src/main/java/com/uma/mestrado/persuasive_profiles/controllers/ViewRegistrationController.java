package com.uma.mestrado.persuasive_profiles.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.uma.mestrado.persuasive_profiles.models.CountryDto;
import com.uma.mestrado.persuasive_profiles.models.GenderDto;
import com.uma.mestrado.persuasive_profiles.models.POSTRegisterRequest;
import com.uma.mestrado.persuasive_profiles.services.PersonService;
import com.uma.mestrado.persuasive_profiles.services.UtilsServices;


@Controller
public class ViewRegistrationController
{

  Logger logger = LoggerFactory.getLogger(ViewRegistrationController.class);

  @Autowired
  private UtilsServices utilsService;
  @Autowired
  private PersonService personService;

  @GetMapping("/registration")
  public String showRegistForm(Model aModel)
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
    }

    return "registration";
  }

  @PostMapping("/registration")
  public String saveRegist(@ModelAttribute POSTRegisterRequest aRequest, Model aModel)
  {
    personService.postRegister(aRequest.getName(), aRequest.getAge(), aRequest.getGenderId(), aRequest.getCountryId(), aRequest.getWeight(),
    aRequest.getHeight(), aRequest.isHadNutricionalConsult(), aRequest.getUsername(), aRequest.getPwd());
    return "index";
  }
}
